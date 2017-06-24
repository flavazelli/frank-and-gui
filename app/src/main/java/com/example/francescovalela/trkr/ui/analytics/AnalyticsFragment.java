package com.example.francescovalela.trkr.ui.analytics;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.example.francescovalela.trkr.logExpense.models.Expense;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;
import com.example.francescovalela.trkr.ui.addExpense.AddExpenseActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

//TODO refactor class

/**
 * Created by francescovalela on 2017-02-28.
 */

public class AnalyticsFragment extends Fragment implements AnalyticsContract.View, AdapterView.OnItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AnalyticsActivity mAnalyticsActivity;
    private AnalyticsContract.Presenter mAnalyticsPresenter;
    private List<Expense> mExpenses;
    private List<Category> mCategories;
    private List<MethodOfPayment> mMethodOfPayments;
    private View RootView;
    private static String TAG = "ANALYTICSACTIVITY";
    private float[] yData;
    private String[] xData;
    private PieChart mPieChart;
    private String whatChartIsDisplaying;

    private RelativeLayout analyticsMainLayout;
    private LineChart mLineChart;
    private List<Entry> entries;
    private LineDataSet dataSet;
    private LineData lineData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment Manager
        fragmentManager = getChildFragmentManager();

        fragmentTransaction = getChildFragmentManager().beginTransaction();

        mAnalyticsActivity = (AnalyticsActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_analytics, container, false);

        //Initialize LineChart
        mLineChart = (LineChart) RootView.findViewById(R.id.linechart);
        mLineChart.setNoDataText("");

        //Initialize PieChart
        mPieChart = (PieChart) RootView.findViewById(R.id.piechart);
        mPieChart.setNoDataText("");

        //load data into cache
        mAnalyticsPresenter.loadExpenses();

        loadSpinnerData();

        createLineChart();

        //Button: view analytics activity
        Button buttonViewAnalytics = (Button) RootView.findViewById(R.id.view_addExpense);
        buttonViewAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAddExpenseActivity = new Intent(getActivity(), AddExpenseActivity.class);

                startActivity(viewAddExpenseActivity);
            }
        });

        return RootView;

    }

    public void loadSpinnerData() {

        List<String> differentDataToChart = new ArrayList<>();
        differentDataToChart.add("Categories");
        differentDataToChart.add("Methods of Payment");
        differentDataToChart.add("Total Expenses");

        //Populate spinner for different chart data
        Spinner spinner = (Spinner) RootView.findViewById(R.id.analytics_chart_spinner);

        spinner.setOnItemSelectedListener(this);

        //Load categories
        ArrayAdapter<String> chartAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, differentDataToChart);
        chartAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(chartAdapter);
    }

    //called when a chart is chosen from the spinner
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView titleTV = (TextView) RootView.findViewById(R.id.analytics_subtitle_textView);

        Spinner spinnerSelected = (Spinner) parent;

        if (spinnerSelected.getItemAtPosition(position).equals("Methods of Payment")) {
            titleTV.setText("By Methods of Payment");
            mLineChart.setVisibility(View.GONE);
            createPieChartMethodOfPayment(mapOfMethodOfPaymentCosts());
            whatChartIsDisplaying = "Method of Payment";
        } else if (spinnerSelected.getItemAtPosition(position).equals("Categories")) {
            titleTV.setText("Categories");
            mLineChart.setVisibility(View.GONE);
            createPieChartCategory(mapOfCategoryCosts());
            whatChartIsDisplaying = "Category";
        } else if (spinnerSelected.getItemAtPosition(position).equals("Total Expenses")) {
            titleTV.setText("Total Expenses");
            mPieChart.setVisibility(View.GONE);
            createLineChart();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing needs to be done
    }

    @Override
    public void setPresenter(AnalyticsContract.Presenter presenter) {
        mAnalyticsPresenter = checkNotNull(presenter);
    }

    private void createLineChart() {
        mLineChart.setVisibility(View.VISIBLE);
        entries = new ArrayList<Entry>();

        List<String> xDateList = new ArrayList<>();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        for (int i = 0; i < mExpenses.size(); i++) {
            if (!xDateList.contains(mExpenses.get(i).getDate()))
                xDateList.add(df.format(mExpenses.get(i).getDate()));
        }

        String[] xDateValues = new String[xDateList.size()];
        xDateValues = xDateList.toArray(xDateValues);

        float tempFloat;
        for (int i = 0; i < mExpenses.size(); i++) {
            tempFloat = (float) mExpenses.get(i).getCost();
            entries.add(new Entry(i, tempFloat));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Total Cost");
        //styling
        lineChartStyling(dataSet);
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setDrawLimitLinesBehindData(true);  // limit lines are drawn behind data (and not on top)
        leftAxis.enableGridDashedLine(10f, 10f, 0f); // enables dashed lines for grid
        final String[] finalXDateValues = xDateValues;
        mLineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return finalXDateValues[(int) value]; // xVal is a string array
            }

            public int getDecimalDigits() {
                return 0;
            }
        });
        mLineChart.getXAxis().enableGridDashedLine(10f, 10f, 0f); // enables dashed lines for grid
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getXAxis().setLabelRotationAngle(-45);
        mLineChart.getXAxis().setGranularity(1f); // restrict interval to 1 (minimum)
        mLineChart.getAxisRight().setEnabled(false); //disables right axis labels
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setDrawGridBackground(true); // enables background color
        mLineChart.setDrawBorders(true); // enables border around chart
        mLineChart.animateY(1250, Easing.EasingOption.EaseOutBounce);
        mLineChart.getLegend().setEnabled(false);


        //styling and adding entries to chart object
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // used to refresh
    }

    public void createPieChartCategory(Map<String, Double> dataToShow) {
        mPieChart.setVisibility(View.VISIBLE);
        mPieChart.getDescription().setText("Category Cost in Percent");
        mPieChart.getDescription().setTextSize(14f);
        mPieChart.getDescription().setPosition(1350f, 1150f);
        mPieChart.setRotationEnabled(true);
        mPieChart.setDrawEntryLabels(false);
        mPieChart.getLegend().setEnabled(false);
        mPieChart.setHoleRadius(60f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setUsePercentValues(true);
        mPieChart.animateY(1000);
        mPieChart.spin(1400, 0, 180, Easing.EasingOption.EaseInOutQuad);
        mPieChart.setNoDataText("");
        addPieDataSet(dataToShow);
    }

    public void createPieChartMethodOfPayment(Map<String, Double> dataToShow) {
        mPieChart.setVisibility(View.VISIBLE);
        mPieChart.getDescription().setText("Method Of Payment Cost in Percent");
        mPieChart.getDescription().setTextSize(14f);
        mPieChart.setRotationEnabled(true);
        mPieChart.setDrawEntryLabels(false);
        mPieChart.getLegend().setEnabled(false);
        mPieChart.setHoleRadius(60f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setUsePercentValues(true);
        mPieChart.animateY(1000);
        mPieChart.spin(1400, 0, 180, Easing.EasingOption.EaseInOutQuad);
        mPieChart.setNoDataText("");
        addPieDataSet(dataToShow);
    }

    //populates list with loaded categories
    public void getCategoriesData(List<Category> categories) {
        mCategories = new ArrayList<>(categories);
    }

    //populates list with loaded methods of payment
    public void getMethodOfPaymentsData(List<MethodOfPayment> methodOfPayments) {
        mMethodOfPayments = new ArrayList<>(methodOfPayments);
    }

    @Override
    public void getExpensesData(List<Expense> expensesToShow) {

        mAnalyticsPresenter.loadCategories();
        mAnalyticsPresenter.loadMethodOfPayments();

        mExpenses = new ArrayList<>(expensesToShow);
    }

    //maps categories to their total cost
    public Map<String, Double> mapOfCategoryCosts() {
        Map<String, Double> categoryCosts = new ArrayMap<>();   //key = category name, value = cost
        Map<Integer, String> categoriesMap = new ArrayMap<>();  //makes categories ID map to the name

        for (Category category : mCategories) {
            categoriesMap.put(category.getId(), category.getName());
            categoryCosts.put(category.getName(), (double) 0);
        }

        for (Expense expense : mExpenses)
            categoryCosts.put(categoriesMap.get(expense.getCategory()), categoryCosts.get(categoriesMap.get(expense.getCategory())) + expense.getCost());

        for (Category category : mCategories) {
            if (categoryCosts.get(category.getName()) == 0)
                categoryCosts.remove(category.getName());
        }

        return categoryCosts;
    }

    //maps method of payments to their total cost
    public Map<String, Double> mapOfMethodOfPaymentCosts() {
        Map<String, Double> methodOfPaymentCosts = new ArrayMap<>();   //key = method of payment name, value = cost
        Map<Integer, String> methodOfPaymentsMap = new ArrayMap<>();  //makes method of payment ID map to the name

        for (MethodOfPayment methodOfPayment : mMethodOfPayments) {
            methodOfPaymentsMap.put(methodOfPayment.getId(), methodOfPayment.getNickname());
            methodOfPaymentCosts.put(methodOfPayment.getNickname(), (double) 0);
        }

        for (Expense expense : mExpenses)
            methodOfPaymentCosts.put(methodOfPaymentsMap.get(expense.getMethodOfPayment()), methodOfPaymentCosts.get(methodOfPaymentsMap.get(expense.getMethodOfPayment())) + expense.getCost());

        for (MethodOfPayment methodOfPayment : mMethodOfPayments) {
            if (methodOfPaymentCosts.get(methodOfPayment.getNickname()) == 0)
                methodOfPaymentCosts.remove(methodOfPayment.getNickname());
        }

        return methodOfPaymentCosts;
    }

    private void addPieDataSet(Map<String, Double> dataToShow) {

        Set<String> dataKey = dataToShow.keySet();
        Collection<Double> dataValue = dataToShow.values();

        xData = new String[dataValue.size()];
        yData = new float[dataToShow.size()];
        ArrayList<PieEntry> yEntrys = new ArrayList<>();

        double temp = 0;
        int count = 0;

        for (String name : dataKey) {
            temp = dataToShow.get(name);
            xData[count] = name;
            yData[count] = (float) temp;
            count++;
        }

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, null);
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueTextSize(15);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.argb(255, 250, 205, 205));
        colors.add(Color.argb(255, 248, 250, 205));
        colors.add(Color.argb(255, 210, 250, 205));
        colors.add(Color.argb(255, 205, 250, 236));
        colors.add(Color.argb(255, 236, 205, 250));
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter()); //for there to be a percent sign
        mPieChart.setData(pieData);
        mPieChart.invalidate();

        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());
                int pos1 = e.toString().indexOf("y:");
                String totalExpenses = e.toString().substring(pos1 + 2);

                for (int i = 0; i < yData.length; i++) {
                    if (yData[i] == Float.parseFloat(totalExpenses)) {
                        String test = "test: " + String.valueOf(yData[i]) + "\n" + xData[i];
                        Log.d(TAG, test);
                        pos1 = i;
                        break;
                    }
                }
                String categoryName = xData[pos1];
                Snackbar.make(RootView, whatChartIsDisplaying + ": " + categoryName + " - $" + totalExpenses, Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void lineChartStyling(LineDataSet dataSetPassed) {
        dataSetPassed.setDrawFilled(true);
        dataSetPassed.setCircleColorHole(Color.DKGRAY);
        dataSetPassed.setColor(Color.CYAN);
        dataSetPassed.setValueTextColor(1);
    }
}

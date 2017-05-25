package com.example.francescovalela.trkr.ui.analytics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francescovalela on 2017-02-28.
 */

public class AnalyticsActivity extends AppCompatActivity {

    private static String TAG = "ANALYTICSACTIVITY";
    private float[] yData = {23.3f, 10.6f, 66.76f, 44.32f};
    private String[] xData = {"CreditCard", "DebitCard","Cash","Other"};
    private PieChart mPieChart;

    private RelativeLayout analyticsMainLayout;
    private LineChart mLineChart;
    private float[] yAxisLabels = {5,10,15,20,25};
    private String[] xAxisLabels = {"05-17-17", "05-18-17", "05-19-17", "05-20-17", "05-21-17", "05-22-17", "05-23-17"};
    private List<Entry> entries;
    private LineDataSet dataSet;
    private LineData lineData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        //Initialize LineChart
        mLineChart = (LineChart) findViewById(R.id.linechart);

        //Initialize PieChart
        mPieChart = (PieChart) findViewById(R.id.piechart);


        createLineChart();

    }

    private void createLineChart() {
        entries = new ArrayList<Entry>();

        //adding data to entries object
        entries.add(new Entry(1, 15));
        entries.add(new Entry(2, 10));
        entries.add(new Entry(3, 25));
        entries.add(new Entry(4, 30));
        entries.add(new Entry(5, 10));
        entries.add(new Entry(6, 20));
        entries.add(new Entry(7, 15));

        LineDataSet dataSet = new LineDataSet(entries, "Expenses");

        //styling
        lineChartStyling(dataSet);
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setDrawLimitLinesBehindData(true);  // limit lines are drawn behind data (and not on top)
        leftAxis.enableGridDashedLine(10f, 10f, 0f); // enables dashed lines for grid
        mLineChart.getXAxis().enableGridDashedLine(10f, 10f, 0f); // enables dashed lines for grid
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.getAxisRight().setEnabled(false); //disables right axis labels
        mLineChart.setDrawGridBackground(true); // enables background color
        mLineChart.setDrawBorders(true); // enables border around chart
        mLineChart.setNoDataText("There isn't any data to display");
        mLineChart.animateY(1250, Easing.EasingOption.EaseOutBounce);


        //styling and adding entries to chart object
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // used to refresh //TODO will need to use when hitting new button along with notifyDataSetChanged()
    }

    public void createPieChart() {
        mPieChart.getDescription().setText("Categories in Percent");
        mPieChart.getDescription().setTextSize(14f);
        mPieChart.setRotationEnabled(true);
        mPieChart.setHoleRadius(60f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setUsePercentValues(true);
        mPieChart.animateY(1000);
        mPieChart.spin(1400, 0, 180, Easing.EasingOption.EaseInOutQuad);
        addDataSet();
    }

    private List getCategoriesData() {
        List<Category> mCategories = new ArrayList<>();

        return mCategories;
    }
    private List getMethodOfPaymentsData() {
        List<Method> mMethodOfPayments = new ArrayList<>();

        return mMethodOfPayments;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i = 0; i < yData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, null);
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueTextSize(15);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.argb(255, 250,205,205));
        colors.add(Color.argb(255, 248,250,205));
        colors.add(Color.argb(255, 210,250,205));
        colors.add(Color.argb(255, 205,250,236));
        colors.add(Color.argb(255, 236,205,250));
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = mPieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter()); //for there to be a percent sign
        mPieChart.setData(pieData);
        mPieChart.invalidate();
    }

    private void updateDataLineChart() {

    }

    private void updateDataPieChart() {

    }

    public void showPieChart(final View view) {
        mLineChart.setVisibility(View.GONE);
        createPieChart();
//        entries.add(new Entry(8, 115));
//        dataSet = new LineDataSet(entries, "Expenses");
//        lineChartStyling(dataSet);
//        lineData = new LineData(dataSet);
//        mLineChart.setData(lineData);
//        mLineChart.invalidate();

        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());
                int pos1 = e.toString().indexOf("y:");
                String sales = e.toString().substring(pos1 + 2);

                for (int i = 0; i<yData.length; i++) {
                    if(yData[i] == Float.parseFloat(sales)) {
                        String test = "test: " + String.valueOf(yData[i]) + "\n" + xData[i];
                        Log.d(TAG, test);
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1];
                Snackbar.make(view,  "Category: " + employee + " // Sales: $" + sales + "K", Snackbar.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void lineChartStyling(LineDataSet dataSetPassed) {
        dataSetPassed.setCircleColorHole(Color.LTGRAY);
        dataSetPassed.setColor(Color.BLUE);
        dataSetPassed.setValueTextColor(1);
    }
}

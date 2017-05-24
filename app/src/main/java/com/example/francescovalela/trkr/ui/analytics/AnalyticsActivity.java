package com.example.francescovalela.trkr.ui.analytics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.francescovalela.trkr.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francescovalela on 2017-02-28.
 */

public class AnalyticsActivity extends AppCompatActivity {

    private RelativeLayout analyticsMainLayout;
    private LineChart mLineChart;
    private float[] yAxisLabels = {5,10,15,20,25};
    private String[] xAxisLabels = {"05-17-17", "05-18-17", "05-19-17", "05-20-17", "05-21-17", "05-22-17", "05-23-17"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        mLineChart = (LineChart) findViewById(R.id.linechart);

        List<Entry> entries = new ArrayList<Entry>();

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
        dataSet.setCircleColorHole(Color.LTGRAY);
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(1);
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
        mLineChart.animateY(2500, Easing.EasingOption.EaseInQuad);


        //styling and adding entried to chart object
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // used to refresh //TODO will need to use when hitting new button along with notifyDataSetChanged()
    }
}

package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {
  private TextView textView;
  BarChart barChart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
   // textView = findViewById(R.id.textView0);

    barChart =  findViewById(R.id.chart);
//    Database database = new Database();
//    database.execute();

    createChart();
//    try {
//      textView.setText(database.get().toString());
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    } catch (ExecutionException e) {
//      e.printStackTrace();
//    }

  }

  private BarChart createChart() {
    // sets data points
    JSONArray jsonArray = null;
    Database database = new Database();
    database.execute();
    try {
       jsonArray = (JSONArray) database.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    float[] group1 = new float[jsonArray.length()];
    float[] group2 = new float[jsonArray.length()];

    try{
      for (int i = 0; i < jsonArray.length(); i++){
        JSONObject jsonObject  = jsonArray.getJSONObject(i);
        group1[i] = (float) Float.parseFloat(jsonObject.get("temperature").toString());
        group2[i] = (float) Float.parseFloat(jsonObject.get("humidity").toString());

      }
    } catch (Exception e){
      e.printStackTrace();
    }

// sets x axis label
    final String[] time = new String[jsonArray.length()];
    try{
      for (int i = 0; i < jsonArray.length(); i++){
        JSONObject jsonObject  = jsonArray.getJSONObject(i);
        time[i] = jsonObject.get("date").toString().substring(11,16);
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    IAxisValueFormatter formatter = new IAxisValueFormatter() {

      @Override
      public String getFormattedValue(float value, AxisBase axis) {
        return time[(int) value];
      }

    };
    System.out.println(Arrays.toString(time));
    List<BarEntry> entriesGroup1 = new ArrayList<>();
    List<BarEntry> entriesGroup2 = new ArrayList<>();

// fill the lists
    for (int i = 0; i < group1.length; i++) {
      entriesGroup1.add(new BarEntry(i, group1[i]));
      entriesGroup2.add(new BarEntry(i, group2[i]));
    }
    BarDataSet set1 = new BarDataSet(entriesGroup1, "Temperature");
    BarDataSet set2 = new BarDataSet(entriesGroup2, "Humidity");

    set1.setColor(Color.RED);
    set2.setColor(Color.BLUE);
    float groupSpace = 0.0f;
    float barSpace = 0.1f; // x2 dataset
    float barWidth = .34f; // x2 dataset
// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"


    BarData data = new BarData(set1, set2);
    data.setBarWidth(barWidth); // set the width of each bar
    XAxis xAxis = barChart.getXAxis();
    xAxis.setAxisMinimum(.5f);
    xAxis.setLabelRotationAngle(45);
    xAxis.setValueFormatter(formatter);
    xAxis.setLabelCount(jsonArray.length());
    barChart.setData(data);
    barChart.groupBars(0.5f, groupSpace, barSpace); // perform the "explicit" grouping
    barChart.setTouchEnabled(false);
    barChart.getDescription().setEnabled(false);
    barChart.setPinchZoom(false);
    barChart.setDoubleTapToZoomEnabled(false);
    barChart.invalidate(); // refresh

    System.out.println(set1);
    System.out.println(set2);
    return barChart;
  }

}


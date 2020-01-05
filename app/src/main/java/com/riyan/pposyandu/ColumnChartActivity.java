package com.riyan.pposyandu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class ColumnChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_common);


        getSupportActionBar().setTitle("Grafik Berat Badan");

        AnyChartView anyChartView = findViewById(R.id.ani_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> dataEntries = new ArrayList<>();
        dataEntries.add(new ValueDataEntry("Jan",0));
        dataEntries.add(new ValueDataEntry("Feb",0));
        dataEntries.add(new ValueDataEntry("Mar",102610));
        dataEntries.add(new ValueDataEntry("Apr",110430));
        dataEntries.add(new ValueDataEntry("May",128000));

        Column column = cartesian.column(dataEntries);

        column.tooltip()
                .titleFormat("{%X}")
        .position(Position.CENTER_BOTTOM)
        .anchor(Anchor.CENTER_BOTTOM)
        .offsetX(0d)
        .offsetY(5d)
        .format("{Berat }Kg");

        cartesian.animation(true);
        cartesian.title("Berat Badan Posyandu");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("10 Kg");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Bulan");
        cartesian.yAxis(0).title("Berat");

        anyChartView.setChart(cartesian);


    }
}

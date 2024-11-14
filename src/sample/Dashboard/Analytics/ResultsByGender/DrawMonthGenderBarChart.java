package sample.Dashboard.Analytics.ResultsByGender;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DrawMonthGenderBarChart {
    FetchMonthResultsByGender fetchMonthResultsByGender = new FetchMonthResultsByGender();

    public void drawGraph(BarChart<String, Number> chart, NumberAxis yAxis, CategoryAxis xAxis){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchMonthResultsByGender.getMonthlyCountMales();
                fetchMonthResultsByGender.getMonthlyCountFemales();

                Integer males = fetchMonthResultsByGender.getTOTAL_MALES();
                Integer females = fetchMonthResultsByGender.getTOTAL_FEMALES();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        xAxis.setCategories(FXCollections.observableArrayList("Males", "Females"));

                        XYChart.Series series1 = new XYChart.Series();
                        series1.getData().add(new XYChart.Data<>("Males", males));
                        series1.getData().add(new XYChart.Data<>("Females", females));

                        chart.getData().addAll(series1);
                    }
                });
            }
        });
        thread.start();
    }
}

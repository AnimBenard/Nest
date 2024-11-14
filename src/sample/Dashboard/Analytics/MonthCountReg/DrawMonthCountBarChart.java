package sample.Dashboard.Analytics.MonthCountReg;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DrawMonthCountBarChart {
    FetchMonthCountRegister fetchMonthCountRegister = new FetchMonthCountRegister();

    public void drawGraph(BarChart<String, Number> chart, NumberAxis yAxis, CategoryAxis xAxis){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchMonthCountRegister.getMonthlyCountHub();
                fetchMonthCountRegister.getMonthlyCountSpokes();

                Integer hub = fetchMonthCountRegister.getTOTAL_HUB();
                Integer spokes = fetchMonthCountRegister.getTOTAL_SPOKES();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        xAxis.setCategories(FXCollections.observableArrayList("Hub", "Spokes"));

                        XYChart.Series series1 = new XYChart.Series();
                        series1.getData().add(new XYChart.Data<>("Hub", hub));
                        series1.getData().add(new XYChart.Data<>("Spokes", spokes));

                        chart.getData().addAll(series1);
                    }
                });
            }
        });
        thread.start();
    }
}

package sample.Dashboard.Analytics.RegisterCount;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class DrawRegCountLineChart {
    FetchRegisterCount fetchRegisterCount = new FetchRegisterCount();

    public void drawRegChart(LineChart<String, Number> lineChart){
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchRegisterCount.fetchTotalCurMonday();
                fetchRegisterCount.fetchTotalCurTuesday();
                fetchRegisterCount.fetchTotalCurWednesday();
                fetchRegisterCount.fetchTotalCurThursday();
                fetchRegisterCount.fetchTotalCurFriday();
                fetchRegisterCount.fetchTotalCurSaturday();
                fetchRegisterCount.fetchTotalCurSunday();
                fetchRegisterCount.fetchTotalPrevSunday();
                fetchRegisterCount.fetchTotalPreMonday();
                fetchRegisterCount.fetchTotalPreTuesday();
                fetchRegisterCount.fetchTotalPreWednesday();
                fetchRegisterCount.fetchTotalPreThursday();
                fetchRegisterCount.fetchTotalPreFriday();
                fetchRegisterCount.fetchTotalPreSaturday();
                XYChart.Series curSeries = new XYChart.Series();

                curSeries.getData().add(new XYChart.Data<>("Mon", fetchRegisterCount.getCUR_MONDAY()));
                curSeries.getData().add(new XYChart.Data<>("Tue", fetchRegisterCount.getCUR_TUESDAY()));
                curSeries.getData().add(new XYChart.Data<>("Wed", fetchRegisterCount.getCUR_WEDNESDAY()));
                curSeries.getData().add(new XYChart.Data<>("Thur", fetchRegisterCount.getCUR_THURSDAY()));
                curSeries.getData().add(new XYChart.Data<>("Fri", fetchRegisterCount.getCUR_FRIDAY()));
                curSeries.getData().add(new XYChart.Data<>("Sat", fetchRegisterCount.getCUR_SATURDAY()));
                curSeries.getData().add(new XYChart.Data<>("Sun", fetchRegisterCount.getCUR_SUNDAY()));
                //prev series
                XYChart.Series preSeries = new XYChart.Series();
                preSeries.getData().add(new XYChart.Data<>("Mon", fetchRegisterCount.getPRE_MONDAY()));
                preSeries.getData().add(new XYChart.Data<>("Tue", fetchRegisterCount.getPRE_TUESDAY()));
                preSeries.getData().add(new XYChart.Data<>("Wed", fetchRegisterCount.getPRE_WEDNESDAY()));
                preSeries.getData().add(new XYChart.Data<>("Thur", fetchRegisterCount.getPRE_THURSDAY()));
                preSeries.getData().add(new XYChart.Data<>("Fri", fetchRegisterCount.getPRE_FRIDAY()));
                preSeries.getData().add(new XYChart.Data<>("Sat", fetchRegisterCount.getPRE_SATURDAY()));
                preSeries.getData().add(new XYChart.Data<>("Sun", fetchRegisterCount.getPRE_SUNDAY()));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lineChart.getData().addAll(curSeries, preSeries);
                    }
                });
            }
        }).start();
    }
}

package sample.Dashboard.Analytics.PieChartTestCount;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class DrawPieChartTestCount {

    FetchResultsCount fetchResultsCount = new FetchResultsCount();

    public void drawPieChart(PieChart pieChart){
        fetchResultsCount.fetchTotalResultsCount();
        fetchResultsCount.fetchTotalNegResultsCount();
        fetchResultsCount.fetchTotalPosResultsCount();
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                new PieChart.Data("Neg", fetchResultsCount.getTOTAL_NEG()),
                new PieChart.Data("Pos", fetchResultsCount.getTOTAL_POS())
        );
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pieChart.setData(chartData);
                    }
                });
            }
        }).start();

    }
}

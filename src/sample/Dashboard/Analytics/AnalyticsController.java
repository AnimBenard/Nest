package sample.Dashboard.Analytics;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Dashboard.Analytics.FacilitySampleCount.FacCountDataClass;
import sample.Dashboard.Analytics.FacilitySampleCount.RetrieveExtReQCount;
import sample.Dashboard.Analytics.FacilitySampleCount.RetrieveFacSampleCount;
import sample.Dashboard.Analytics.Items.FetchStock;
import sample.Dashboard.Analytics.Items.FetchUsed;
import sample.Dashboard.Analytics.MonthCountReg.DrawMonthCountBarChart;
import sample.Dashboard.Analytics.MonthCountReg.FetchMonthCountRegister;
import sample.Dashboard.Analytics.PieChartTestCount.DrawPieChartTestCount;
import sample.Dashboard.Analytics.PieChartTestCount.FetchResultsCount;
import sample.Dashboard.Analytics.RegisterCount.DrawRegCountLineChart;
import sample.Dashboard.Analytics.ResultsByGender.DrawMonthGenderBarChart;
import sample.Dashboard.Analytics.ResultsByGender.FetchMonthResultsByGender;
import sample.Dashboard.Analytics.SearchByAge.RetrieveCountByAge;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {
    @FXML
    PieChart pc_testPieChart;
    @FXML
    LineChart<String, Number> lc_registerCount;
    @FXML
    CategoryAxis xAxisRegister, bc_xAxisRegCountMonth, bc_xAxisGenderPos;
    @FXML
    NumberAxis yAxisRegister, y_bc_AxisRegCountMonth, y_bc_AxisGenderPos;
    @FXML
    Label lb_name, lb_userNo, lb_totalTestCount, lb_posCount, lb_negCount, lb_totalHub, lb_totalSpokes, lb_totalSuccessful, lb_totalInv, lb_totalErr, lb_totalFollowUps, lb_totalNewCases, lb_totalMale, lb_totalFemale;
    @FXML
    Label lb_posMales, lb_posFemales, lb_totalChildren, lb_totalAdult, lb_posChildren, lb_posAdult, lb_negChild, lb_negAdult, lb_cartStock, lb_cartReceived, lb_cartExpired, lb_borrowed, lb_used, lb_left;
    @FXML
    Label lb_totalExtReq, lb_totalAcceptReq, lb_totalRejReq;
    @FXML
    BarChart<String, Number> bc_monthCount, bc_gennderPositive;
    @FXML
    TableView<FacCountDataClass> tv_sampleCount;
    @FXML
    TableColumn<FacCountDataClass,String> tc_facility, tc_samples, tc_position;
    @FXML
    AnchorPane ach_analyticsHolder;


    DrawRegCountLineChart drawRegCountLineChart = new DrawRegCountLineChart();
    DrawPieChartTestCount drawPieChartTestCount = new DrawPieChartTestCount();
    FetchResultsCount fetchResultsCount = new FetchResultsCount();
    DrawMonthCountBarChart drawMonthCountBarChart = new DrawMonthCountBarChart();
    FetchMonthCountRegister fetchMonthCountRegister = new FetchMonthCountRegister();
    RetrieveFacSampleCount retrieveFacSampleCount = new RetrieveFacSampleCount();
    ObservableList<FacCountDataClass> facCountDataClass = FXCollections.observableArrayList();
    DrawMonthGenderBarChart drawMonthGenderBarChart = new DrawMonthGenderBarChart();
    FetchMonthResultsByGender fetchMonthResultsByGender = new FetchMonthResultsByGender();
    RetrieveCountByAge retrieveCountByAge = new RetrieveCountByAge();
    RetrieveExtReQCount retrieveExtReQCount = new RetrieveExtReQCount();
    FetchStock fetchStock = new FetchStock();
    FetchUsed fetchUsed = new FetchUsed();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAnalyticsHolder();
        scheduledManager();
        getCountsToLabels();
        addCountDataToTable();
    }
    public void scheduledManager(){
        ScheduledService<Void> scheduledService = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        drawRegCountLineChart.drawRegChart(lc_registerCount);
                        drawPieChartTestCount.drawPieChart(pc_testPieChart);
                        drawMonthCountBarChart.drawGraph(bc_monthCount, y_bc_AxisRegCountMonth, bc_xAxisRegCountMonth);
                        drawMonthGenderBarChart.drawGraph(bc_gennderPositive, y_bc_AxisGenderPos, bc_xAxisGenderPos);
                        Thread.sleep(Long.MAX_VALUE);
                        return null;
                    }
                };
            }
        };
        scheduledService.setDelay(Duration.seconds(5));
        scheduledService.start();

    }
    public void setAnalyticsHolder(){
        //ach_analyticsHolder.setVisible(false);
        ach_analyticsHolder.setVisible(true);
        /*PauseTransition pauseTransition = new PauseTransition(Duration.seconds(4));
        pauseTransition.setOnFinished(e -> {

        });
        pauseTransition.play();*/
    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void getCountsToLabels(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchResultsCount.fetchTotalPosResultsCount();
                fetchResultsCount.fetchTotalNegResultsCount();
                fetchResultsCount.fetchTotalResultsCount();
                //fetch total hubs and spokes within the month
                fetchMonthCountRegister.getMonthlyCountSpokes();
                fetchMonthCountRegister.getMonthlyCountHub();
                fetchResultsCount.fetchTotalSuccessfulResultsCount();
                fetchResultsCount.fetchTotalInvResultsCount();
                fetchResultsCount.fetchTotalErrResultsCount();
                //fetch new cases and follow ups
                fetchResultsCount.fetchTotalNewCasesCount();
                fetchResultsCount.fetchTotalFollowUpsCount();
                fetchResultsCount.fetchTotalMalesCount();
                fetchResultsCount.fetchTotalFemalesCount();
                //add gender count to DB
                fetchMonthResultsByGender.getMonthlyCountMales();
                fetchMonthResultsByGender.getMonthlyCountFemales();
                retrieveCountByAge.fetchTotalChild();
                retrieveCountByAge.fetchTotalPosChild();
                retrieveCountByAge.fetchTotalNegChild();
                retrieveCountByAge.fetchTotalAdult();
                retrieveCountByAge.fetchPosAdult();
                retrieveCountByAge.fetchNegAdult();
                fetchStock.retrieveXpertCartridgesStock();

                //fetch cart received
                ObservableList<Integer> receivedData = FXCollections.observableArrayList();
                fetchStock.retrieveXpertCartridgesReceived(receivedData);
                Integer sumReceived  = receivedData.stream().mapToInt(Integer::intValue).sum();

                //fetch expired
                ObservableList<Integer> expiredData = FXCollections.observableArrayList();
                fetchStock.retrieveXpertCartridgesExpired(expiredData);
                Integer sumExpired  = expiredData.stream().mapToInt(Integer::intValue).sum();

                ObservableList<Integer> borrowedData = FXCollections.observableArrayList();
                fetchStock.retrieveXpertCartridgesBorrowed(borrowedData);
                Integer sumBorrowed  = borrowedData.stream().mapToInt(Integer::intValue).sum();

                fetchUsed.retrieveUsedCart();
                retrieveExtReQCount.fetchTotalReqCount();
                retrieveExtReQCount.fetchTotalAcceptCount();
                retrieveExtReQCount.fetchTotalRejectCount();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lb_totalTestCount.setText(String.valueOf(fetchResultsCount.getTOTAL_RESULTS()));
                        lb_posCount.setText(String.valueOf(fetchResultsCount.getTOTAL_POS()));
                        lb_negCount.setText(String.valueOf(fetchResultsCount.getTOTAL_NEG()));

                        lb_totalSpokes.setText(String.valueOf(fetchMonthCountRegister.getTOTAL_SPOKES()));
                        lb_totalHub.setText(String.valueOf(fetchMonthCountRegister.getTOTAL_HUB()));


                        lb_totalSuccessful.setText(String.valueOf(fetchResultsCount.getTOTAL_SUCCESSFUL()));
                        lb_totalErr.setText(String.valueOf(fetchResultsCount.getTOTAL_ERR()));
                        lb_totalInv.setText(String.valueOf(fetchResultsCount.getTOTAL_INV()));

                        lb_totalFollowUps.setText(String.valueOf(fetchResultsCount.getTOTAL_FOLLOW_UPS()));
                        lb_totalNewCases.setText(String.valueOf(fetchResultsCount.getTOTAL_NEW_CASES()));
                        //add gender count

                        lb_totalMale.setText(String.valueOf(fetchResultsCount.getTOTAL_MALES()));
                        lb_totalFemale.setText(String.valueOf(fetchResultsCount.getTOTAL_FEMALES()));

                        lb_posMales.setText(String.valueOf(fetchMonthResultsByGender.getTOTAL_MALES()));
                        lb_posFemales.setText(String.valueOf(fetchMonthResultsByGender.getTOTAL_FEMALES()));
                        //add count by age to label

                        lb_totalChildren.setText(String.valueOf(retrieveCountByAge.getTOTAL_CHILD()));
                        lb_posChildren.setText(String.valueOf(retrieveCountByAge.getPOS_CHILD()));
                        lb_negChild.setText(String.valueOf(retrieveCountByAge.getNEG_CHILD()));
                        lb_totalAdult.setText(String.valueOf(retrieveCountByAge.getTOTAL_ADULT()));
                        lb_negAdult.setText(String.valueOf(retrieveCountByAge.getNEG_CHILD()));
                        lb_posAdult.setText(String.valueOf(retrieveCountByAge.getPOS_ADULT()));
                        //fetch items stock

                        lb_cartStock.setText(fetchStock.getSTOCK_CARTRIDGE());

                        lb_cartReceived.setText(String.valueOf(sumReceived));

                        lb_cartExpired.setText(String.valueOf(sumExpired));
                        //fetch borrowed

                        lb_borrowed.setText(String.valueOf(sumBorrowed));
                        //fetch used

                        lb_used.setText(String.valueOf(fetchUsed.getUSED_CART()));
                        //fetch left
                        // Integer left = Integer.valueOf(lb_cartStock.getText()) - Integer.valueOf(lb_used.getText());
                        //lb_left.setText(String.valueOf(left));

                        //fetch external request count

                        lb_totalExtReq.setText(retrieveExtReQCount.getTOTAL_COUNT());
                        lb_totalAcceptReq.setText(retrieveExtReQCount.getACCEPT_COUNT());
                        lb_totalRejReq.setText(retrieveExtReQCount.getREJECT_COUNT());
                    }
                });
            }
        }).start();
    }
    public void addCountDataToTable(){
        facCountDataClass.clear();
        retrieveFacSampleCount.retrieveSampleCount(facCountDataClass);
        tc_facility.setCellValueFactory(new PropertyValueFactory<>("FACILITY"));
        tc_samples.setCellValueFactory(new PropertyValueFactory<>("COUNT"));
        tc_position.setCellValueFactory(new PropertyValueFactory<>("POSITION"));
        tv_sampleCount.setItems(null);
        tv_sampleCount.setItems(facCountDataClass);
    }
}

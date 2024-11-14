package sample.Dashboard;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Dashboard.Analytics.AnalyticsController;
import sample.Dashboard.Hub.HubController;
import sample.Dashboard.MonthlyStockNotification.FetchCartridgesLeft;
import sample.Dashboard.MonthlyStockNotification.StockNotificationController;
import sample.Dashboard.Report.ReportController;
import sample.Dashboard.Results.AddResultsController;
import sample.Dashboard.SetUser.SetUserController;
import sample.Dashboard.Spokes.SpokesController;
import sample.Dashboard.Store.StoreController;
import sample.Dashboard.Users.UsersController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    AnchorPane ach_analytics, ach_users, ach_paneLoader, ach_hub, ach_spokes, ach_results, ach_reports, ach_store, ach_settings, ach_loadReport;
    @FXML
    Label lb_userNo, lb_name, lb_signIn, lb_signOut;

    FetchCartridgesLeft fetchCartridgesLeft = new FetchCartridgesLeft();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        autoSetAnalyticsScene();
        delayShowNotification();
    }
    public void getUserNo(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    @FXML
    public void openUserScene(MouseEvent event){
        ach_users.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Users/user.fxml"));
                Parent root = loader.load();
                UsersController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }
    @FXML
    public void openHub(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Hub/hub.fxml"));
                Parent root = loader.load();
                HubController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }
    @FXML
    public void openSpokes(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Spokes/spokes.fxml"));
                Parent root = loader.load();
                SpokesController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }
    @FXML
    public void openResults(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Results/addResults.fxml"));
                Parent root = loader.load();
                AddResultsController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }
    @FXML
    public void openAnalyticsScene(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Analytics/analytics.fxml"));
                Parent root = loader.load();
                AnalyticsController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }
    @FXML
    public void openReportScene(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Report/report.fxml"));
                Parent root = loader.load();
                ReportController controller = loader.getController();
                controller.getUserNo(lb_name.getText(), lb_userNo.getText());
                ach_paneLoader.getChildren().setAll(root);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

    }

    public void autoSetAnalyticsScene(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Analytics/analytics.fxml"));
            Parent root = loader.load();
            AnalyticsController controller = loader.getController();
            controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
            ach_paneLoader.getChildren().setAll(root);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    @FXML
    public void openStoreScene(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        ach_settings.setStyle(null);
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {
            try{
                Image icon = new Image("/sample/images/etblogo.png");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Store/store.fxml"));
                DialogPane pane = loader.load();
                StoreController controller = loader.getController();
                controller.getUserNo(lb_name.getText(), lb_userNo.getText());
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                dialog.setTitle("");
                stage.setOnCloseRequest(e -> stage.hide());
                dialog.showAndWait();

            }catch (IOException ioException){

            }
        }
    }
    public void delayShowNotification(){
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
        pauseTransition.setOnFinished(e -> {
            showNotification();
        });
        pauseTransition.play();
    }
    public void showNotification(){
        //this method will show the stock notification if new month stock not found for cartridges
        boolean added = fetchCartridgesLeft.checkIfStockAvailableThisMonth();
        if (!added){
            stockNotification();
        }

    }
    public void stockNotification(){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/MonthlyStockNotification/stockNotification.fxml"));
            DialogPane pane = loader.load();
            StockNotificationController controller = loader.getController();
            controller.setUsername(lb_name.getText(), lb_userNo.getText());
            controller.addNewMonthStockToDb();
            controller.addCountToFields();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(icon);
            dialog.setTitle("");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
            controller.handleCurrentAction(stage);
        }catch (IOException ioException){

        }
    }
    @FXML
    public void signOut(MouseEvent event){
        lb_name.setText(null);
    }
    public void autoOpenSetUserDialog(){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/SetUser/setUser.fxml"));
            DialogPane pane = loader.load();
            SetUserController controller = loader.getController();
            controller.addUsersToCB();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            controller.setUser(stage, lb_name);
            stage.getIcons().add(icon);
            stage.setOnCloseRequest(e -> stage.hide());
            dialog.showAndWait();

        }catch (IOException ioException){

        }
    }
    @FXML
    public void openSetUserDialog(MouseEvent event){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/SetUser/setUser.fxml"));
            DialogPane pane = loader.load();
            SetUserController controller = loader.getController();
            controller.addUsersToCB();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            controller.setUser(stage, lb_name);
            stage.getIcons().add(icon);
            stage.setOnCloseRequest(e -> stage.hide());
            dialog.showAndWait();
        }catch (IOException ioException){

        }
    }
    @FXML
    public void openLoadReportScene(MouseEvent event){
        ach_users.setStyle(null);
        ach_analytics.setStyle(null);
        ach_hub.setStyle(null);
        ach_spokes.setStyle(null);
        ach_results.setStyle(null);
        ach_reports.setStyle(null);
        ach_store.setStyle(null);
        ach_settings.setStyle(null);
        ach_loadReport.setStyle("-fx-background-color: linear-gradient(to right,  #002746 40%, #005293 90%)");
        if(lb_name.getText() == null || lb_name.getText().isEmpty()){
            autoOpenSetUserDialog();
        }else {

        }
    }
}

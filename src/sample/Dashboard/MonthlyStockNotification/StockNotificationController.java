package sample.Dashboard.MonthlyStockNotification;

import animatefx.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Alert.SetRedNode;

import java.net.URL;
import java.util.ResourceBundle;

public class StockNotificationController implements Initializable {
    @FXML
    AnchorPane ach_loader, ach_prevMonth, ach_curMonth;
    @FXML
    TextField tf_curCount, tf_prevCount;
    @FXML
    Button btn_curCount, btn_prevCount;
    @FXML
    Label lb_name, lb_userNo;
    @FXML
    ImageView img_previous;

    FetchCartridgesLeft fetchCartridgesLeft = new FetchCartridgesLeft();
    SetRedNode setRedNode = new SetRedNode();
    AddNewMonthStockToDB addNewMonthStockToDB = new AddNewMonthStockToDB();
    UpdateNewMonthStockWithQtyAvailable updateNewMonthStockWithQtyAvailable = new UpdateNewMonthStockWithQtyAvailable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRedNode.setImageViewHoverStyles(img_previous);
        setRedNode.checkIntegerNumberInFields(tf_prevCount);
        setRedNode.checkIntegerNumberInFields(tf_curCount);
        addCountToFields();
        handleACHBack();
        handlePrevAction();
    }
    public void addNewMonthStockToDb(){
        boolean added = fetchCartridgesLeft.checkIfStockAvailableThisMonth();
        if(!added){
            addNewMonthStockToDB.addNewMonthStock();
        }
    }
    public void setUsername(String username, String userNo){
        lb_name.setText(username);
        lb_userNo.setText(userNo);
    }
    public void addCountToFields(){
        fetchCartridgesLeft.retrieveCartridgeCount();
        tf_curCount.setText(String.valueOf(fetchCartridgesLeft.getQTY_AVAILABLE()));
        tf_prevCount.setText(String.valueOf(fetchCartridgesLeft.getQTY_LEFT()));
    }
    public void handlePrevAction(){
        btn_prevCount.setOnMouseClicked(e -> {
            ach_curMonth.setVisible(true);
            ach_prevMonth.setVisible(false);
            if(tf_prevCount.getText() == null || tf_prevCount.getText().isEmpty()){
                setRedNode.setRedTextField(tf_prevCount);
                new Shake(tf_prevCount).play();
            }else {

            }
        });
    }
    public void handleCurrentAction(Stage stage){
        //confirm and close
        btn_curCount.setOnMouseClicked(e -> {
            if(tf_curCount.getText() == null || tf_curCount.getText().isEmpty()){
                setRedNode.setRedTextField(tf_curCount);
                new Shake(tf_curCount).play();
            }else {
                String prevCount = tf_prevCount.getText();
                String curCount = tf_curCount.getText();

                Image icon = new Image("/sample/images/etblogo.png");
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Action");
                dialog.setContentText("Please confirm the number of cartridges at the beginning of the month");
                ButtonType btnYes = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
                dialog.getDialogPane().getButtonTypes().addAll(btnNo, btnYes);
                Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage1.setOnCloseRequest(e1 -> stage1.close());
                stage1.getIcons().add(icon);
                dialog.showAndWait().ifPresent(response -> {
                    if(response == btnNo ){
                        stage1.close();
                    }else if(response == btnYes){
                        updateNewMonthStockWithQtyAvailable.updateStockAvailable_Left(prevCount, curCount);
                        stage.hide();
                    }
                });
            }
        });
    }
    public void handleACHBack(){
        img_previous.setOnMouseClicked(e -> {
            ach_curMonth.setVisible(false);
            ach_prevMonth.setVisible(true);
        });
    }
    //the next is to update the qty with button click
}

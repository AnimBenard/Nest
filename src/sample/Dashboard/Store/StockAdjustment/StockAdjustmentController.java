package sample.Dashboard.Store.StockAdjustment;

import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Alert.AutoCompleteCB;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;
import sample.Dashboard.Store.AddStock.RetrieveConsumableStock;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StockAdjustmentController implements Initializable {
    @FXML
    TextField tf_batchNo, tf_qtyAvailable, tf_adjValue, tf_user, tf_search, tf_qtyLeft;
    @FXML
    ComboBox<String> cb_item, cb_reason;
    @FXML
    Label lb_remove, lb_removeError, lb_ID, lb_name, lb_userNo;
    @FXML
    TableView<StockAdjustmentDataClass> tv_adjReqList;
    @FXML
    TableColumn<StockAdjustmentDataClass,String> tc_item, tc_batchNo, tc_status, tc_requestId, tc_qtyAvailable, tc_adjValue, tc_qtyLeft, tc_reason, tc_time, tc_date, tc_user;
    @FXML
    Button btn_add;
    @FXML
    DatePicker dp_proDate, dp_expDate;

    SetRedNode setRedNode = new SetRedNode();
    DialogBox dialogBox = new DialogBox();
    RetrieveConsumableStock retrieveConsumableStock = new RetrieveConsumableStock();
    ObservableList<String> stockList = FXCollections.observableArrayList();
    AutoCompleteCB autoCompleteCB = new AutoCompleteCB();
    FetchBatchNoQtyAvailable fetchBatchNoQtyAvailable = new FetchBatchNoQtyAvailable();
    AddStockAdjReqToDB addStockAdjReqToDB = new AddStockAdjReqToDB();
    UpdateSockAfterAdjustment updateSockAfterAdjustment = new UpdateSockAfterAdjustment();
    RetrieveStockAdjustmentReqData retrieveStockAdjustmentReqData = new RetrieveStockAdjustmentReqData();
    ObservableList<StockAdjustmentDataClass> stockAdjData = FXCollections.observableArrayList();
    RemoveFromStockAdjReq removeFromStockAdjReq = new RemoveFromStockAdjReq();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addStockItemsToCB();
        addOptionsToCB();
        addBatchNoQtyAvailableToFields();
        calQtyLeft();
        insertStockAdjReqToDB();
        addDataToTAble();
        filterDStockAdjReqForm();
        addSARTableItemsToFields();
    }
    public void setUsername(String username, String userNo){
        lb_name.setText(username);
        lb_userNo.setText(userNo);
    }
    public void addStockItemsToCB(){
        retrieveConsumableStock.fetchConsItems(stockList);
        cb_item.setItems(stockList);
        autoCompleteCB.autoCompleteCB(stockList, cb_item);
    }
    public void addOptionsToCB(){
        ObservableList<String> reasonData = FXCollections.observableArrayList();
        reasonData.addAll("Theft", "Breakages", "Expired", "Bad Condition", "Borrowing", "Recall", "Shortage");
        cb_reason.setItems(reasonData);
    }
    public void addBatchNoQtyAvailableToFields(){
        cb_item.valueProperty().addListener((observableValue, s, value) -> {
            fetchBatchNoQtyAvailable.fetchBatchQtyAvailable(value);
            tf_batchNo.setText(fetchBatchNoQtyAvailable.BATCH_NO);
            tf_qtyAvailable.setText(fetchBatchNoQtyAvailable.getTOTAL_AVAILABLE());
            dp_proDate.setValue(LocalDate.parse(fetchBatchNoQtyAvailable.getPRO_DATE()));
            dp_expDate.setValue(LocalDate.parse(fetchBatchNoQtyAvailable.getEXP_DATE()));
        });
    }
    public void insertStockAdjReqToDB(){
        btn_add.setOnMouseClicked(e -> {
            if(cb_item.getValue() == null || cb_item.getValue().isEmpty()){
                setRedNode.setRedCB(cb_item);
                new Shake(cb_item).play();
            }else if(tf_adjValue.getText() == null || tf_adjValue.getText().isEmpty()){
                setRedNode.setRedTextField(tf_adjValue);
                new Shake(tf_adjValue).play();
            }else if(cb_reason.getValue() == null || cb_reason.getValue().isEmpty()){
                setRedNode.setRedCB(cb_reason);
                new Shake(cb_reason).play();
            }else {
                if(lb_ID.getText() == null || lb_ID.getText().isEmpty()){
                    getFieldData();
                }else {
                    Image icon = new Image("/sample/vibrio.png");
                    String contentText = "Data exist already";
                    String dtitle = "Error!!!";
                    ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
                }
            }
        });
    }
    public void getFieldData(){
        String item = cb_item.getValue();
        String batchNo = tf_batchNo.getText();
        String qtyAvailable = tf_qtyAvailable.getText();
        String adjValue = tf_adjValue.getText();
        String reason = cb_reason.getValue();
        String user = lb_name.getText();
        String qtyLeft = tf_qtyLeft.getText();
        String appStatus = "NOT APPROVED";
        String proDate = String.valueOf(dp_proDate.getValue());
        String expDate = String.valueOf(dp_expDate.getValue());
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());

        //creates requestCode
        java.util.Date date1 = new java.util.Date();
        java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
        Calendar cal = Calendar.getInstance();
        DateFormat tCodeTimeFormat = new SimpleDateFormat("HHmmss");
        DateFormat tCodeDateFormat = new SimpleDateFormat("yyyyMMdd");
        String tcTime = tCodeTimeFormat.format(cal.getTime());
        String tcDate = tCodeDateFormat.format(sqlDate1);
        String firstChars = cb_item.getValue().substring(0, 1);
        String reqId = firstChars + tcDate + tcTime;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addStockAdjReqToDB.addStockAdjReq(item, batchNo, qtyAvailable, adjValue, "", reason, time, sqlDate, user, reqId, appStatus, qtyLeft, proDate, expDate);
                        updateSockAfterAdjustment.updateStock(qtyLeft, item);
                        addDataToTAble();
                        clearStockAdjFields();
                    }
                });
            }
        }).start();
    }
    public void addDataToTAble(){
        stockAdjData.clear();
        retrieveStockAdjustmentReqData.retrieveStockAdjData(stockAdjData);
        tc_item.setCellValueFactory(new PropertyValueFactory<>("ITEM"));
        tc_batchNo.setCellValueFactory(new PropertyValueFactory<>("BATCH_NO"));
        tc_requestId.setCellValueFactory(new PropertyValueFactory<>("REQUEST_ID"));
        tc_qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("QTY_AVAILABLE"));
        tc_adjValue.setCellValueFactory(new PropertyValueFactory<>("ADJUSTMENT_VALUE"));
        tc_qtyLeft.setCellValueFactory(new PropertyValueFactory<>("QTY_LEFT"));
        tc_reason.setCellValueFactory(new PropertyValueFactory<>("REASON"));
        tc_status.setCellValueFactory(new PropertyValueFactory<>("APPROVAL_STATUS"));
        tc_time.setCellValueFactory(new PropertyValueFactory<>("TIME"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tc_user.setCellValueFactory(new PropertyValueFactory<>("PERSONNEL"));
        tv_adjReqList.setItems(null);
        tv_adjReqList.setItems(stockAdjData);
        filterDStockAdjReqForm();
    }
    public void clearStockAdjFields(){
        lb_ID.setText(null);
        cb_item.setValue(null);
        tf_batchNo.clear();
        tf_qtyAvailable.clear();
        tf_adjValue.clear();
        cb_reason.setValue(null);
        tf_qtyLeft.clear();
        tf_user.clear();
        dp_proDate.setValue(null);
        dp_expDate.setValue(null);
    }
    public void filterDStockAdjReqForm(){
        FilteredList<StockAdjustmentDataClass> filteredList = new FilteredList<>(stockAdjData, b -> true);
        tf_search.textProperty().addListener((observableValue, s, t1) -> {
            filteredList.setPredicate(stockAdjustmentDataClass -> {
                if(t1 == null || t1.isEmpty()){
                    return true;
                }
                String searchTerm = t1.toLowerCase();
                if(stockAdjustmentDataClass.getITEM().toLowerCase().contains(searchTerm)){
                    return true;
                }
                return false;
            });
        });
        SortedList<StockAdjustmentDataClass> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tv_adjReqList.comparatorProperty());
        tv_adjReqList.setItems(sortedList);
    }
    @FXML
    public void removeStockAdjustmentData(MouseEvent event) {
        if (lb_ID.getText() == null || lb_ID.getText().isEmpty()) {
            lb_removeError.setText("Select item from list");
            new Shake(lb_removeError).play();
        } else {
            lb_removeError.setText("");
            Image icon = new Image("sample/vibrio.png");
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setContentText("Do you want to delete this item..");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            dialog.getDialogPane().getButtonTypes().addAll(btnNo, btnYes);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.setOnCloseRequest(e1 -> stage.close());
            stage.getIcons().add(icon);
            dialog.showAndWait().ifPresent(response -> {
                if (response == btnNo) {
                    lb_ID.setText(null);
                    stage.close();
                } else if (response == btnYes) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Integer id = Integer.valueOf(lb_ID.getText());
                                    removeFromStockAdjReq.removeData(id);
                                    addDataToTAble();
                                    clearStockAdjFields();
                                }
                            });
                        }
                    }).start();
                }
            });
        }
    }
    public void addSARTableItemsToFields(){
        tv_adjReqList.setOnMouseClicked(e -> {
            tv_adjReqList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            StockAdjustmentDataClass item = tv_adjReqList.getSelectionModel().getSelectedItem();
            lb_ID.setText(String.valueOf(item.getID()));
        });
    }
    public void calQtyLeft(){
        tf_adjValue.textProperty().addListener((observableValue, s, value) -> {
            Integer adjustmentVal = Integer.valueOf(tf_adjValue.getText());
            Integer qtyAvailable = Integer.valueOf(tf_qtyAvailable.getText());
            Integer qtyLeft = adjustmentVal + qtyAvailable;
            tf_qtyLeft.setText(String.valueOf(qtyLeft));
        });
    }
}

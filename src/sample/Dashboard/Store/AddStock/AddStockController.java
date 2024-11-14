package sample.Dashboard.Store.AddStock;

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
import javafx.scene.input.MouseEvent;
import sample.Alert.SetRedNode;
import sample.Dashboard.Store.StockAdjustment.AddStockAdjReqToDB;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddStockController implements Initializable {
    @FXML
    TextField tf_supplierName, tf_telephone, tf_minStock, tf_packQtyReceived, tf_qtyInPack, tf_totalUnitQty, tf_batchNo, tf_packPrice, tf_unitPrice, tf_amount, tf_search;
    @FXML
    ComboBox<String> cb_item;
    @FXML
    DatePicker dp_prodDate, dp_expiryDate;
    @FXML
    Label lb_ID, lb_name, lb_userNo, lb_remove, lb_removeError, lb_requestId, lb_clearFields;
    @FXML
    Button btn_createRequest;
    @FXML
    TableColumn<ConsumableStockDataClass,String> tc_supplierName, tc_supplierTel, tc_item, tc_packQtyReceived, tc_qtyInPack, tc_totalUnitQty, tc_minStock, tc_batchNo, tc_unitPackPrice, tc_unitItemPrice, tc_amount, tc_prodDate, tc_expDate, tc_time, tc_date, tc_user, tc_qtyLeft;
    @FXML
    TableView<ConsumableStockDataClass> tv_consumableStock;

    SetRedNode setRedNode = new SetRedNode();
    CheckIfCSItemExist checkIfCSItemExist = new CheckIfCSItemExist();
    AddConsumableStockToDB addConsumableStockToDB = new AddConsumableStockToDB();
    AddConsumableStockLogToDB addConsumableStockLogToDB = new AddConsumableStockLogToDB();
    RetrieveConsumableStock retrieveConsumableStock = new RetrieveConsumableStock();
    ObservableList<ConsumableStockDataClass> consData = FXCollections.observableArrayList();
    AddStockAdjReqToDB addStockAdjReqToDB = new AddStockAdjReqToDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRedNode.formatTFieldToUpper(tf_supplierName);
        setRedNode.formatTFieldToUpper(tf_batchNo);
        setRedNode.checkIntegerNumberInFields(tf_minStock);
        setRedNode.checkIntegerNumberInFields(tf_packQtyReceived);
        setRedNode.checkIntegerNumberInFields(tf_totalUnitQty);
        setRedNode.checkIntegerNumberInFields(tf_qtyInPack);
        setRedNode.checkDecimalNumberInFields(tf_packPrice);
        setRedNode.checkDecimalNumberInFields(tf_amount);
        setRedNode.checkDecimalNumberInFields(tf_unitPrice);
        handleTotalUnitQty();
        handleTotalAmount();
        addConsumableToDb();
        addRetrieveConsStockToTable();
        addItemsToCb();
    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void addItemsToCb(){
        ObservableList<String> itemsData = FXCollections.observableArrayList();
        itemsData.addAll("GeneXpert Cartridge", "Covid Cartridge");
        cb_item.setItems(itemsData);
    }
    public void handleTotalUnitQty(){
        //handle total unit qty by pack quantity received
        tf_packQtyReceived.textProperty().addListener((observableValue, s, value) -> {
            if(tf_qtyInPack.getText() != null || !tf_qtyInPack.getText().isEmpty()){
                Integer packQtyReceived = Integer.valueOf(value);
                Integer qtyInAPack = Integer.valueOf(tf_qtyInPack.getText());
                Integer totalItemsInPack = packQtyReceived * qtyInAPack;
                tf_totalUnitQty.setText(String.valueOf(totalItemsInPack));
            }
        });
        //handle total unit qty by quantity in a pack
        tf_qtyInPack.textProperty().addListener((observableValue, s, value) -> {
            if(tf_packQtyReceived.getText() != null || !tf_packQtyReceived.getText().isEmpty()){
                Integer qtyInAPack = Integer.valueOf(value);
                Integer packsReceived = Integer.valueOf(tf_packQtyReceived.getText());
                Integer totalItemsInPack = packsReceived * qtyInAPack;
                tf_totalUnitQty.setText(String.valueOf(totalItemsInPack));
            }
        });
    }
    public void handleTotalAmount(){
        //this will handle the total pack amount
        tf_packPrice.textProperty().addListener((observableValue, s, value) -> {
            if(tf_packQtyReceived.getText() != null || !tf_packQtyReceived.getText().isEmpty()){
                Double unitPackPrice = Double.valueOf(value);
                Integer packsReceived = Integer.valueOf(tf_packQtyReceived.getText());
                Double totalPacksAmount = packsReceived * unitPackPrice;
                tf_amount.setText(String.valueOf(totalPacksAmount));

            }
        });
        //handle unit item price
        tf_packPrice.textProperty().addListener((observableValue, s, value) -> {
            if(tf_qtyInPack.getText() != null || !tf_qtyInPack.getText().isEmpty()){
                Double unitPackPrice = Double.valueOf(value);
                Integer qtyInAPack = Integer.valueOf(tf_qtyInPack.getText());
                Double unitItemPrice = unitPackPrice / qtyInAPack;
                tf_unitPrice.setText(String.valueOf(unitItemPrice));
            }
        });
    }
    public void addConsumableToDb(){
        btn_createRequest.setOnMouseClicked(e -> {
            if(tf_supplierName.getText() == null || tf_supplierName.getText().isEmpty()){
                setRedNode.setRedTextField(tf_supplierName);
                new Shake(tf_supplierName).play();
            }else if(tf_telephone.getText() == null || tf_telephone.getText().isEmpty()){
                setRedNode.setRedTextField(tf_telephone);
                new Shake(tf_telephone).play();
            }else if(cb_item.getValue() == null || cb_item.getValue().isEmpty()){
                setRedNode.setRedCB(cb_item);
                new Shake(cb_item).play();
            }else if(tf_packQtyReceived.getText() == null || tf_packQtyReceived.getText().isEmpty()){
                setRedNode.setRedTextField(tf_packQtyReceived);
                new Shake(tf_packQtyReceived).play();
            }else if(tf_qtyInPack.getText() == null || tf_qtyInPack.getText().isEmpty()){
                setRedNode.setRedTextField(tf_qtyInPack);
                new Shake(tf_qtyInPack).play();
            }else if(tf_totalUnitQty.getText() == null || tf_totalUnitQty.getText().isEmpty()){
                setRedNode.setRedTextField(tf_totalUnitQty);
                new Shake(tf_totalUnitQty).play();
            }else if(tf_batchNo.getText() == null || tf_batchNo.getText().isEmpty()){
                setRedNode.setRedTextField(tf_batchNo);
                new Shake(tf_batchNo).play();
            }else if(tf_packPrice.getText() == null || tf_packPrice.getText().isEmpty()){
                setRedNode.setRedTextField(tf_packPrice);
                new Shake(tf_packPrice).play();
            }else if(tf_unitPrice.getText() == null || tf_unitPrice.getText().isEmpty()){
                setRedNode.setRedTextField(tf_unitPrice);
                new Shake(tf_unitPrice).play();
            }else if(tf_amount.getText() == null || tf_amount.getText().isEmpty()){
                setRedNode.setRedTextField(tf_amount);
                new Shake(tf_amount).play();
            }else if(dp_prodDate.getValue() == null){
                setRedNode.setRedDP(dp_prodDate);
                new Shake(dp_prodDate).play();
            }else if(dp_expiryDate.getValue() == null){
                setRedNode.setRedDP(dp_expiryDate);
                new Shake(dp_expiryDate).play();
            }else if(tf_minStock.getText() == null || tf_minStock.getText().isEmpty()){
                setRedNode.setRedTextField(tf_minStock);
                new Shake(tf_minStock).play();
            }else {
                boolean itemExist = checkIfCSItemExist.checkIfExist(cb_item.getValue());
                if(itemExist){
                    lb_removeError.setText("Item already exist. Use 'Stock Adjustment' to update");
                    new Shake(lb_removeError).play();
                }else {
                    addConsumableStockValues();
                    addToStockAdjustment();
                }
            }

        });
    }
    public void addToStockAdjustment(){
        String item = cb_item.getValue();
        String batchNo = tf_batchNo.getText();
        String qtyAvailable = tf_totalUnitQty.getText();
        String adjValue = tf_totalUnitQty.getText();
        String reason = "Shortage";
        String user = lb_name.getText();
        String qtyLeft = tf_totalUnitQty.getText();
        String appStatus = "APPROVED";
        String proDate = String.valueOf(dp_prodDate.getValue());
        String expDate = String.valueOf(dp_expiryDate.getValue());
        java.util.Date date = new java.util.Date();
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
                        addStockAdjReqToDB.addStockAdjReqNoDialog(item, batchNo, qtyAvailable, adjValue, "", reason, time, sqlDate, user, reqId, appStatus, qtyLeft, proDate, expDate);
                    }
                });
            }
        }).start();
    }
    public void addConsumableStockValues(){
        String supplier = tf_supplierName.getText();
        String tel = tf_telephone.getText();
        String minStock = tf_minStock.getText();
        String item = cb_item.getValue();
        String packQtyRec = tf_packQtyReceived.getText();
        String qtyInPack = tf_qtyInPack.getText();
        String totalUnitQty = tf_totalUnitQty.getText();
        String batchNo = tf_batchNo.getText();
        String packPrice = tf_packPrice.getText();
        String unitItemPrice = tf_unitPrice.getText();
        String amount = tf_amount.getText();
        String proDate = String.valueOf(dp_prodDate.getValue());
        String expDate = String.valueOf(dp_expiryDate.getValue());
        setRedNode.timeAndDate();
        String time = setRedNode.getTIME();
        Date date = setRedNode.getDATE();
        String user = lb_name.getText();
        setRedNode.createRequestId(item);
        String reqId = setRedNode.getREQUEST_ID();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addConsumableStockToDB.addConsumableStockToDB(supplier, tel, item, batchNo, packQtyRec, qtyInPack, packPrice,
                                unitItemPrice, minStock, totalUnitQty, amount, proDate, expDate, time, date, user, reqId);
                        addConsumableStockLogToDB.addConsumableStockLogToDB(supplier, tel, item, batchNo, packQtyRec, qtyInPack, packPrice,
                                unitItemPrice, minStock, totalUnitQty, amount, proDate, expDate, time, date, user, reqId);

                        addRetrieveConsStockToTable();
                    }
                });
            }
        }).start();
    }
    public void addRetrieveConsStockToTable(){
        consData.clear();
        retrieveConsumableStock.consumableStockData(consData);
        tc_supplierName.setCellValueFactory(new PropertyValueFactory<>("SUPPLIER_NAME"));
        tc_supplierTel.setCellValueFactory(new PropertyValueFactory<>("SUPPLIER_TEL"));
        tc_item.setCellValueFactory(new PropertyValueFactory<>("ITEM"));
        tc_packQtyReceived.setCellValueFactory(new PropertyValueFactory<>("PACKS_RECEIVED"));
        tc_qtyInPack.setCellValueFactory(new PropertyValueFactory<>("TOTAL_QTY_IN_PACK"));
        tc_totalUnitQty.setCellValueFactory(new PropertyValueFactory<>("TOTAL_UNIT_QTY"));
        tc_minStock.setCellValueFactory(new PropertyValueFactory<>("MINIMUM_STOCK"));
        tc_batchNo.setCellValueFactory(new PropertyValueFactory<>("BATCH_NO"));
        tc_unitPackPrice.setCellValueFactory(new PropertyValueFactory<>("UNIT_PACK_PRICE"));
        tc_unitItemPrice.setCellValueFactory(new PropertyValueFactory<>("UNIT_ITEM_PRICE"));
        tc_amount.setCellValueFactory(new PropertyValueFactory<>("TOTAL_PACKS_AMOUNT"));
        tc_prodDate.setCellValueFactory(new PropertyValueFactory<>("PRODUCTION_DATE"));
        tc_expDate.setCellValueFactory(new PropertyValueFactory<>("EXPIRY_DATE"));
        tc_time.setCellValueFactory(new PropertyValueFactory<>("TIME"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tc_user.setCellValueFactory(new PropertyValueFactory<>("PERSONNEL"));
        tc_qtyLeft.setCellValueFactory(new PropertyValueFactory<>("QTY_LEFT"));
        tv_consumableStock.setItems(null);
        tv_consumableStock.setItems(consData);
        filterConsumableStockForm();
    }
    public void filterConsumableStockForm(){
        FilteredList<ConsumableStockDataClass> filteredList = new FilteredList<>(consData, b -> true);
        tf_search.textProperty().addListener((observableValue, s, t1) -> {
            filteredList.setPredicate(consumableStockDataClass -> {
                if(t1 == null || t1.isEmpty()){
                    return true;
                }
                String searchTerm = t1.toLowerCase();
                if(consumableStockDataClass.getITEM().toLowerCase().contains(searchTerm)){
                    return true;
                }
                return false;
            });
        });
        SortedList<ConsumableStockDataClass> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tv_consumableStock.comparatorProperty());
        tv_consumableStock.setItems(sortedList);
    }
    @FXML
    public void clearCSFields(MouseEvent event){
        tf_supplierName.clear();
        tf_telephone.clear();
        tf_minStock.clear();
        cb_item.setValue(null);
        tf_packQtyReceived.clear();
        tf_qtyInPack.clear();
        tf_totalUnitQty.clear();
        tf_packPrice.clear();
        tf_unitPrice.clear();
        tf_amount.clear();
        dp_prodDate.setValue(null);
        dp_expiryDate.setValue(null);
        tf_batchNo.setText(null);
        lb_ID.setText(null);
        lb_requestId.setText(null);
    }
}

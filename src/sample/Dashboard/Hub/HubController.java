package sample.Dashboard.Hub;

import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;
import sample.Dashboard.Hub.ExternalRequest.ExtReqTable.ExtRequestTableController;
import sample.Dashboard.Hub.ExternalRequest.ExternalRequestController;
import sample.Dashboard.Hub.FullRequestTable.SearchHubRequestController;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HubController implements Initializable {
    @FXML
    Label lb_name, lb_ID, lb_userNo, lb_reason, lb_error, lb_remove, lb_reset, lb_searchRequest, lb_refresh, lb_createExtReq, lb_extReqList;
    @FXML
    TextField tf_surname, tf_othernames, tf_age, tf_address, tf_distTBNo, tf_search;
    @FXML
    ComboBox<String> cb_refFacility, cb_typeOfSpecimen, cb_sampleAppearance, cb_month, cb_sex;
    @FXML
    Button btn_createRequest;
    @FXML
    CheckBox chk_diagnosis, chk_resistant, chk_followUp;
    @FXML
    TableColumn<RequestListDataClass,String> tc_sampleId, tc_surname, tc_othername, tc_age, tc_sex, tc_address, tc_disTBNo, tc_refFacility, tc_specType, tc_appearance, tc_reasonForExam, tc_time, tc_date, tc_user;
    @FXML
    TableView<RequestListDataClass> tv_requestList;
    @FXML
    AnchorPane ach_indicateMonths;

    DialogBox dialogBox = new DialogBox();
    SetRedNode setRedNode = new SetRedNode();
    FetchRefFacility fetchRefFacility = new FetchRefFacility();
    ObservableList<String> refFacData = FXCollections.observableArrayList();
    AddRequestToDB addRequestToDB = new AddRequestToDB();
    FetchLastSampleId fetchLastSampleId = new FetchLastSampleId();
    RetrieveRequestListData retrieveRequestListData = new RetrieveRequestListData();
    ObservableList<RequestListDataClass> requestListData = FXCollections.observableArrayList();
    CheckIfResultsAdded checkIfResultsAdded = new CheckIfResultsAdded();
    DeleteRequest deleteRequest = new DeleteRequest();
    CopyDeletedRow copyDeletedRow = new CopyDeletedRow();

    String SAMPLE_ID;
    Integer PATH_NUMBER;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRedNode.formatTFieldToUpper(tf_surname);
        setRedNode.formatTFieldToUpper(tf_othernames);
        setRedNode.formatTFieldToUpper(tf_address);
        setRedNode.formatTFieldToUpper(tf_distTBNo);
        setRedNode.formatCBToUpper(cb_refFacility);
        setRedNode.formatCBToUpper(cb_typeOfSpecimen);
        setRedNode.formatCBToUpper(cb_sampleAppearance);
        addRefFacToCB();
        addTypOfSpecimen();
        addSpecAppearance();
        addMonthToCB();
        handleReasonForExamination();
        addRequestInfoToDb();
        addRequestToTable();
        tableDataToFields();
    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void addRefFacToCB(){
        refFacData.clear();
        fetchRefFacility.retrieveRefFac(refFacData);
        cb_refFacility.setItems(refFacData);
    }
    public void addTypOfSpecimen(){
        ObservableList<String> specTypeData = FXCollections.observableArrayList();
        specTypeData.addAll("SPUTUM", "");
        cb_typeOfSpecimen.setItems(specTypeData);
    }
    public void addSpecAppearance(){
        ObservableList<String> specTypeData = FXCollections.observableArrayList();
        specTypeData.addAll("Mucopurulent", "Blood Stained", "Muco Salivary", "Saliva");
        cb_sampleAppearance.setItems(specTypeData);
    }
    public void addMonthToCB(){
        ObservableList<String> monthsData = FXCollections.observableArrayList();
        monthsData.addAll("2", "5", "6", "8");
        cb_month.setItems(monthsData);

        ObservableList<String> sexData = FXCollections.observableArrayList();
        sexData.addAll("M", "F");
        cb_sex.setItems(sexData);
    }
    public void handleReasonForExamination(){
        chk_diagnosis.setSelected(false);
        chk_followUp.setSelected(false);
        chk_resistant.setSelected(false);
        ach_indicateMonths.setVisible(false);
        lb_reason.setText("");
        chk_diagnosis.selectedProperty().addListener((observableValue, aBoolean, sel) -> {
            boolean isSelected = sel.booleanValue();
            if(isSelected){
                chk_diagnosis.setSelected(true);
                chk_followUp.setSelected(false);
                chk_resistant.setSelected(false);
                lb_reason.setText("TB Diagnosis");
                ach_indicateMonths.setVisible(false);
            }
        });
        chk_resistant.selectedProperty().addListener((observableValue, aBoolean, sel) -> {
            boolean isSelected = sel.booleanValue();
            if(isSelected){
                chk_diagnosis.setSelected(false);
                chk_followUp.setSelected(false);
                chk_resistant.setSelected(true);
                lb_reason.setText("Drug Resistant");
                ach_indicateMonths.setVisible(false);
            }
        });
        chk_followUp.selectedProperty().addListener((observableValue, aBoolean, sel) -> {
            boolean isSelected = sel.booleanValue();
            if(isSelected){
                chk_diagnosis.setSelected(false);
                chk_followUp.setSelected(true);
                chk_resistant.setSelected(false);
                lb_reason.setText("Follow Up");
                ach_indicateMonths.setVisible(true);
            }
        });
    }
    public void addRequestInfoToDb(){
        btn_createRequest.setOnMouseClicked(e -> {
            if(tf_surname.getText() == null || tf_surname.getText().isEmpty()){
                lb_error.setText("Surname cannot be null");
                new Shake(lb_error).play();
            }else if(tf_othernames.getText() == null || tf_othernames.getText().isEmpty()){
                lb_error.setText("Othername cannot be null");
                new Shake(lb_error).play();
            }else if(tf_age.getText() == null || tf_age.getText().isEmpty()){
                lb_error.setText("Age cannot be null");
                new Shake(lb_error).play();
            }else if(cb_sex.getValue() == null || cb_sex.getValue().isEmpty()){
                lb_error.setText("Gender cannot be null");
                new Shake(lb_error).play();
            }else if(tf_address.getText() == null || tf_address.getText().isEmpty()){
                lb_error.setText("Address cannot be null");
                new Shake(lb_error).play();
            }else if(cb_refFacility.getValue() == null || cb_refFacility.getValue().isEmpty()){
                lb_error.setText("Select referring facility");
                new Shake(lb_error).play();
            }else if(cb_typeOfSpecimen.getValue() == null || cb_typeOfSpecimen.getValue().isEmpty()){
                lb_error.setText("Select specimen type");
                new Shake(lb_error).play();
            }else if(cb_sampleAppearance.getValue() == null || cb_sampleAppearance.getValue().isEmpty()){
                lb_error.setText("Select sample appearance");
                new Shake(lb_error).play();
            }else if(!chk_followUp.isSelected() && !chk_diagnosis.isSelected() && !chk_resistant.isSelected()){
                lb_error.setText("Select reason for examination");
                new Shake(lb_error).play();
            }else {
                lb_error.setText("");
                if(lb_ID.getText() == null || lb_ID.getText().isEmpty()){
                    requestInfo();
                }else {
                    Image icon = new Image("/sample/images/etblogo.png");
                    String contentText = "Request Exists already. Reset fields to continue";
                    String dtitle = "Error!";
                    ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
                }

            }
        });
    }
    public void requestInfo(){
        createSampleId();
        setRedNode.timeAndDate();
        setRedNode.createRequestId("TB");
        String surname = tf_surname.getText();
        String othernames = tf_othernames.getText();
        String age = tf_age.getText();
        String ageUnits = "YEARS";
        String sex = cb_sex.getValue();
        String address = tf_address.getText();
        String disTBNo = tf_distTBNo.getText();
        String refFac = cb_refFacility.getValue();
        String specType = cb_typeOfSpecimen.getValue();
        String samAppearance = cb_sampleAppearance.getValue();
        String reason = lb_reason.getText();
        String reqType = "HUB";
        String time = setRedNode.getTIME();
        Date date = setRedNode.getDATE();
        String user = lb_name.getText();
        String reqId = setRedNode.getREQUEST_ID();
        addRequestToDB.createRequest(String.valueOf(PATH_NUMBER), surname, othernames, age, sex, address, disTBNo, refFac, specType, samAppearance, reason, reqType, time, date, user, SAMPLE_ID, reqId, ageUnits);
        addRequestToTable();
        clearFields();
    }
    public void createSampleId(){
        Integer last2Digit = LocalDate.now().getYear() % 100;
        fetchLastSampleId.fetchLastSampleIdHub();
        System.out.println("The last sample id retrieved: "+ fetchLastSampleId.getLAST_SERIAL());
        PATH_NUMBER = fetchLastSampleId.getLAST_SERIAL() + 1;
        String format = String.format("%03d", PATH_NUMBER);
        System.out.println("New sample id : "+ format);
        SAMPLE_ID = "H-"+ format + "-"+last2Digit;
    }
    public void clearFields(){
        tf_surname.clear();
        tf_othernames.clear();
        tf_age.clear();
        cb_sex.setValue(null);
        tf_address.clear();
        tf_distTBNo.clear();
        cb_refFacility.setValue(null);
        cb_typeOfSpecimen.setValue(null);
        cb_sampleAppearance.setValue(null);
        chk_diagnosis.setSelected(false);
        chk_followUp.setSelected(false);
        chk_resistant.setSelected(false);
        cb_month.setValue(null);
        lb_error.setText("");
    }
    @FXML
    public void resetFields(MouseEvent event){
        clearFields();
    }
    public void addRequestToTable(){
        requestListData.clear();
        retrieveRequestListData.retrieveRequestList(requestListData);
        tc_sampleId.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_ID"));
        tc_surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
        tc_othername.setCellValueFactory(new PropertyValueFactory<>("OTHERNAME"));
        tc_age.setCellValueFactory(new PropertyValueFactory<>("FULL_AGE"));
        tc_sex.setCellValueFactory(new PropertyValueFactory<>("SEX"));
        tc_address.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
        tc_disTBNo.setCellValueFactory(new PropertyValueFactory<>("DIST_TB_NUMBER"));
        tc_refFacility.setCellValueFactory(new PropertyValueFactory<>("REF_FACILITY"));
        tc_specType.setCellValueFactory(new PropertyValueFactory<>("SPECIMEN_TYPE"));
        tc_appearance.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_APPEARANCE"));
        tc_reasonForExam.setCellValueFactory(new PropertyValueFactory<>("REASON_FOR_EXAMINATION"));
        tc_time.setCellValueFactory(new PropertyValueFactory<>("REQUEST_TIME"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("REQUEST_DATE"));
        tc_user.setCellValueFactory(new PropertyValueFactory<>("REQUEST_USER"));
        tv_requestList.setItems(null);
        tv_requestList.setItems(requestListData);
        filterRequestList();
    }
    @FXML
    public void refreshRequestTable(MouseEvent event){
        addRequestToTable();
    }
    public void tableDataToFields(){
        tv_requestList.setOnMouseClicked(e -> {
            tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            RequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
            tf_surname.setText(item.getSURNAME());
            tf_othernames.setText(item.getOTHERNAME());
            tf_age.setText(item.getAGE());
            cb_sex.setValue(item.getSEX());
            tf_address.setText(item.getADDRESS());
            tf_distTBNo.setText(item.getDIST_TB_NUMBER());
            cb_refFacility.setValue(item.getREF_FACILITY());
            cb_typeOfSpecimen.setValue(item.getSPECIMEN_TYPE());
            cb_sampleAppearance.setValue(item.getSAMPLE_APPEARANCE());
            cb_month.setValue(item.getFOLLOW_UP_MONTHS());
            lb_reason.setText(item.getREASON_FOR_EXAMINATION());
            lb_ID.setText(String.valueOf(item.getID()));
            if(lb_reason.getText().toLowerCase().equals("TB Diagnosis".toLowerCase())){
                chk_diagnosis.setSelected(true);
                chk_followUp.setSelected(false);
                chk_resistant.setSelected(false);
            }
            if(lb_reason.getText().toLowerCase().equals("Drug Resistant".toLowerCase())){
                chk_diagnosis.setSelected(false);
                chk_followUp.setSelected(false);
                chk_resistant.setSelected(true);
            }
            if(lb_reason.getText().toLowerCase().equals("Follow Up".toLowerCase())){
                chk_diagnosis.setSelected(false);
                chk_followUp.setSelected(true);
                chk_resistant.setSelected(false);
            }
        });
    }
    @FXML
    public void removeRequest(MouseEvent event) {
        if(lb_ID.getText() == null || lb_ID.getText().isEmpty()){
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Select an item from table";
            String dtitle = "Error!";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
        }else {
            Integer id = Integer.valueOf(lb_ID.getText());
            boolean resultsAdded = checkIfResultsAdded.checkResults(id);
            if(resultsAdded){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Can't delete this request. Results added already";
                String dtitle = "Error!";
                ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
                lb_ID.setText(null);
            }else {
                Image icon = new Image("/sample/images/etblogo.png");
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Confirm");
                dialog.setContentText("Do you want to delete this request..");
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
                                        copyDeletedRow.copyRow(id);
                                        deleteRequest.deleteRequest(id);
                                        addRequestToTable();
                                        clearFields();
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        }

    }
    public void filterRequestList(){
        FilteredList<RequestListDataClass> filteredList = new FilteredList<>(requestListData, b -> true);
        tf_search.textProperty().addListener((observableValue, s, t1) -> {
            filteredList.setPredicate(consultingRegDataClass -> {
                if(t1 == null || t1.isEmpty()){
                    return true;
                }
                String searchTerm = t1.toLowerCase();
                if(consultingRegDataClass.getSAMPLE_ID().toLowerCase().contains(searchTerm)){
                    return true;
                }else if(consultingRegDataClass.getFULL_NAME().toLowerCase().contains(searchTerm)){
                    return true;
                }else if(consultingRegDataClass.getREF_FACILITY().toLowerCase().contains(searchTerm)){
                    return true;
                }
                return false;
            });
        });
        SortedList<RequestListDataClass> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tv_requestList.comparatorProperty());
        tv_requestList.setItems(sortedList);
    }
    @FXML
    public void openSearchRequestDialog(MouseEvent event){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Hub/FullRequestTable/searchRequest.fxml"));
            DialogPane pane = loader.load();
            SearchHubRequestController controller = loader.getController();
            controller.setUsername(lb_name.getText(), lb_userNo.getText());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            stage.setOnCloseRequest(e1 -> stage.hide());
            dialog.showAndWait();
        }catch (IOException ioException){

        }
    }
    @FXML
    public void createExternalRequest(MouseEvent event){
        if(lb_ID.getText() == null || lb_ID.getText().isEmpty()){
            lb_error.setText("Select request from table");
            new Shake(lb_error).play();
        }else {
            try{
                lb_error.setText("");
                tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                RequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
                String samId = item.getSAMPLE_ID();
                String name = item.getFULL_NAME();
                String age = item.getFULL_AGE();
                String sex = item.getSEX();
                String specimen = item.getSPECIMEN_TYPE();
                String address = item.getADDRESS();
                String reason = item.getREASON_FOR_EXAMINATION();
                String reqId = item.getREQUEST_ID();
                String id = String.valueOf(item.getID());
                Image icon = new Image("/sample/images/etblogo.png");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Hub/ExternalRequest/externalRequest.fxml"));
                DialogPane pane = loader.load();
                ExternalRequestController controller = loader.getController();
                controller.setClientInfo(id, samId, name, age, sex, specimen, address, reason, reqId);
                controller.getUserNo(lb_name.getText(), lb_userNo.getText());
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                stage.setOnCloseRequest(e1 -> stage.hide());
                controller.addExternalRequestToDb(stage);
                dialog.showAndWait();
            }catch (IOException ioException){

            }
        }

    }
    @FXML
    public void openExtRequestDialog(MouseEvent event){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Hub/ExternalRequest/ExtReqTable/extReq.fxml"));
            DialogPane pane = loader.load();
            ExtRequestTableController controller = loader.getController();
            controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            stage.setOnCloseRequest(e1 -> stage.hide());
            dialog.showAndWait();
        }catch (IOException ioException){

        }
    }

}

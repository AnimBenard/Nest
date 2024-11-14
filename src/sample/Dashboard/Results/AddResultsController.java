package sample.Dashboard.Results;

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
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;
import sample.Dashboard.Hub.RequestListDataClass;
import sample.Dashboard.Hub.RetrieveRequestListData;
import sample.Dashboard.Results.FollowUp.FollowUpResultsController;
import sample.Dashboard.Results.GeneExpert.GeneExpertResultsController;
import sample.Dashboard.Results.PreviewResults.PreviewResultsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddResultsController implements Initializable {
    @FXML
    ComboBox<String> cb_sampleID;
    @FXML
    TextField tf_surname, tf_othernames, tf_age, tf_sex, tf_address, tf_distTBNo, tf_refFacility, tf_search, tf_searchResults;
    @FXML
    CheckBox chk_diagnosis, chk_resistant, chk_followUp;
    @FXML
    Label lb_ID, lb_name, lb_userNo, lb_refresh;
    @FXML
    Button btn_search;
    @FXML
    TableView<RequestListDataClass> tv_requestList;
    @FXML
    TableColumn<RequestListDataClass,String> tc_req_sampleID, tc_req_name, tc_req_time, tc_req_date, tc_req_user;
    @FXML
    TableView<RequestExaminationDataClass> tv_reqExam;
    @FXML
    TableColumn<RequestExaminationDataClass, String> tc_sampleID, tc_reqExam, tc_reqType;
    @FXML
    TableColumn<RequestListDataClass,String> tc_res_sampleID, tc_res_name, tc_res_age, tc_res_sex, tc_res_address, tc_res_results, tc_res_time, tc_res_date, tc_res_user;
    @FXML
    TableView<RequestListDataClass> tv_res_resultsList;

    SetRedNode setRedNode = new SetRedNode();
    RetrieveSampleIDSNoResult retrieveSampleIDSNoResult = new RetrieveSampleIDSNoResult();
    ObservableList<String> sampleIDData = FXCollections.observableArrayList();
    RetrievePatientIfoBySampleID retrievePatientIfoBySampleID = new RetrievePatientIfoBySampleID();
    ObservableList<RequestListDataClass> requestListData = FXCollections.observableArrayList();
    RetrieveRequestListData retrieveRequestListData = new RetrieveRequestListData();
    RetrieveRequestExamination retrieveRequestExamination = new RetrieveRequestExamination();
    ObservableList<RequestExaminationDataClass> requestExamData = FXCollections.observableArrayList();
    ObservableList<RequestListDataClass> resultsListData = FXCollections.observableArrayList();
    DialogBox dBox = new DialogBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addSapleIDToCB();
        patientsInfo();
        addRequestToTable();
        handleRequestExaminationToTable();
        openResultsDialog();
        addResultsToTable();
        openResultsPreviewDialog();
    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void addSapleIDToCB(){
        sampleIDData.clear();
        retrieveSampleIDSNoResult.retrieveSampleID(sampleIDData);
        setRedNode.autoCompleteCB(sampleIDData, cb_sampleID);
    }
    public void patientsInfo(){
        btn_search.setOnMouseClicked(e -> {
            if(cb_sampleID.getValue() == null || cb_sampleID.getValue().isEmpty()){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Select sample ID";
                String dtitle = "Error!";
                ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dBox.showDialogBox(btnType, contentText, dtitle, icon);
            }else {
                String sampleID = cb_sampleID.getValue();
                retrievePatientIfoBySampleID.retrievePatInfo(sampleID);
                tf_surname.setText(retrievePatientIfoBySampleID.getSURNAME());
                tf_othernames.setText(retrievePatientIfoBySampleID.getOTHERNAMES());
                tf_age.setText(retrievePatientIfoBySampleID.getAGE());
                tf_sex.setText(retrievePatientIfoBySampleID.getSEX());
                tf_address.setText(retrievePatientIfoBySampleID.getADDRESS());
                tf_distTBNo.setText(retrievePatientIfoBySampleID.getDIS_TB_NO());
                tf_refFacility.setText(retrievePatientIfoBySampleID.getREF_FAC());
                if(retrievePatientIfoBySampleID.getREASON_FOR_EXAM().equals("TB Diagnosis")){
                    chk_diagnosis.setSelected(true);
                    chk_followUp.setSelected(false);
                    chk_resistant.setSelected(false);
                }
                if(retrievePatientIfoBySampleID.getREASON_FOR_EXAM().equals("Drug Resistant")){
                    chk_diagnosis.setSelected(false);
                    chk_followUp.setSelected(false);
                    chk_resistant.setSelected(true);
                }
                if(retrievePatientIfoBySampleID.getREASON_FOR_EXAM().equals("Follow Up")){
                    chk_diagnosis.setSelected(false);
                    chk_followUp.setSelected(true);
                    chk_resistant.setSelected(false);
                }
                //add examination data to table
                addRequestExaminationToTable(sampleID);
            }
        });
    }
    public void addRequestToTable(){
        requestListData.clear();
        retrieveRequestListData.retrieveRequestListHubSpokes(requestListData);
        tc_req_sampleID.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_ID"));
        tc_req_name.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));
        tc_req_time.setCellValueFactory(new PropertyValueFactory<>("REQUEST_TIME"));
        tc_req_date.setCellValueFactory(new PropertyValueFactory<>("REQUEST_DATE"));
        tc_req_user.setCellValueFactory(new PropertyValueFactory<>("REQUEST_USER"));
        tv_requestList.setItems(null);
        tv_requestList.setItems(requestListData);
        filterRequestList();
    }
    public void refreshList(){
        addRequestToTable();
    }
    @FXML
    public void handleRefreshRequest(MouseEvent event){
        refreshList();
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
    public void filterResultsList(){
        FilteredList<RequestListDataClass> filteredList = new FilteredList<>(resultsListData, b -> true);
        tf_searchResults.textProperty().addListener((observableValue, s, t1) -> {
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
        sortedList.comparatorProperty().bind(tv_res_resultsList.comparatorProperty());
        tv_res_resultsList.setItems(sortedList);
    }
    public void changeRowColor(){
        tv_res_resultsList.setRowFactory(new Callback<TableView<RequestListDataClass>, TableRow<RequestListDataClass>>() {
            @Override
            public TableRow<RequestListDataClass> call(TableView<RequestListDataClass> requestListDataClassTableView) {
               return new TableRow<RequestListDataClass>(){
                   @Override
                   protected void updateItem(RequestListDataClass requestListDataClass, boolean empty) {
                       super.updateItem(requestListDataClass, empty);
                       if(requestListDataClass == null || empty){
                           setStyle("");
                       }else if(requestListDataClass.getRESULTS().toLowerCase().contains("Pos".toLowerCase()) ||
                               requestListDataClass.getRESULTS().toLowerCase().contains("+".toLowerCase()) ||
                               requestListDataClass.getRESULTS().toLowerCase().contains("Scanty".toLowerCase())){
                           setStyle("-fx-background-color: #ffacbc");
                       }
                       else {
                           setStyle("");
                       }
                   }
               };
            }
        });
    }
    public void handleRequestExaminationToTable(){
        tv_requestList.setOnMouseClicked(e -> {
            tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            RequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
            String samId = item.getSAMPLE_ID();
            addRequestExaminationToTable(samId);
        });
    }
    public void addRequestExaminationToTable(String sampleID){
        requestExamData.clear();
        retrieveRequestExamination.retrieveExamination(requestExamData, sampleID);
        tc_sampleID.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_NO"));
        tc_reqExam.setCellValueFactory(new PropertyValueFactory<>("EXAMINATION"));
        tc_reqType.setCellValueFactory(new PropertyValueFactory<>("TYPE"));
        tv_reqExam.setItems(null);
        tv_reqExam.setItems(requestExamData);
    }
    public void addResultsToTable(){
        //add results to results table. results method has bee created already to fetch the results
        resultsListData.clear();
        retrieveRequestListData.retrieveResultsList(resultsListData);
        tc_res_sampleID.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_ID"));
        tc_res_name.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));
        tc_res_age.setCellValueFactory(new PropertyValueFactory<>("AGE"));
        tc_res_sex.setCellValueFactory(new PropertyValueFactory<>("SEX"));
        tc_res_address.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
        tc_res_results.setCellValueFactory(new PropertyValueFactory<>("RESULTS"));
        tc_res_time.setCellValueFactory(new PropertyValueFactory<>("EXAMINED_TIME"));
        tc_res_date.setCellValueFactory(new PropertyValueFactory<>("EXAMINED_DATE"));
        tc_res_user.setCellValueFactory(new PropertyValueFactory<>("EXAMINED_BY"));
        tv_res_resultsList.setItems(null);
        tv_res_resultsList.setItems(resultsListData);
        changeRowColor();
        filterResultsList();
    }
    @FXML
    public void refreshResultsToTable(MouseEvent event){
        addResultsToTable();
    }
    public void openResultsDialog(){
        tv_reqExam.setOnMouseClicked(e -> {
            tv_reqExam.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            RequestExaminationDataClass item = tv_reqExam.getSelectionModel().getSelectedItem();
            String patName = item.getPATIENT_NAME();
            String samId = item.getSAMPLE_NO();
            String date = item.getREQUEST_DATE();
            String exam = item.getEXAMINATION();
            if(exam.equals("TB Diagnosis") || exam.equals("Drug Resistant")){
                try{
                    Image icon = new Image("/sample/images/etblogo.png");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Results/GeneExpert/geneExpertResults.fxml"));
                    DialogPane pane = loader.load();
                    GeneExpertResultsController controller = loader.getController();
                    controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                    controller.getPatientInfo(patName, samId, date);
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setDialogPane(pane);
                    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    stage.setOnCloseRequest(e1 -> stage.hide());
                    dialog.showAndWait();
                    addResultsToTable();//refreshes the table
                }catch (IOException ioException){

                }
            }else if(exam.equals("Follow Up")){
                try{
                    Image icon = new Image("/sample/images/etblogo.png");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Results/FollowUp/followUpResults.fxml"));
                    DialogPane pane = loader.load();
                    FollowUpResultsController controller = loader.getController();
                    controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                    controller.getPatientInfo(patName, samId, date);
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setDialogPane(pane);
                    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(icon);
                    stage.setOnCloseRequest(e1 -> stage.hide());
                    dialog.showAndWait();
                    addResultsToTable();//refreshes the table
                }catch (IOException ioException){

                }
            }

        });
    }
    public void openResultsPreviewDialog(){
        tv_res_resultsList.setOnMouseClicked(e -> {
            tv_res_resultsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            RequestListDataClass item = tv_res_resultsList.getSelectionModel().getSelectedItem();
            String patName = item.getFULL_NAME();
            String sampleID = item.getSAMPLE_ID();
            String age = item.getAGE();
            String sex = item.getSEX();
            String address = item.getADDRESS();
            String type = item.getREQUEST_TYPE();
            String refFacility = item.getREF_FACILITY();
            String reqID = item.getREQUEST_ID();
            String reason = item.getREASON_FOR_EXAMINATION();
            String results = item.getRESULTS();
            String user = item.getREQUEST_USER();
            String date = item.getEXAMINED_DATE();
            try{
                Image icon = new Image("/sample/images/etblogo.png");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Results/PreviewResults/previewResults.fxml"));
                DialogPane pane = loader.load();
                PreviewResultsController controller = loader.getController();
                controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
                controller.getResultsData(patName, sampleID, age, sex, address, type, refFacility, reqID, reason, results, user, date);
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                stage.setOnCloseRequest(e1 -> stage.hide());
                dialog.showAndWait();
            }catch (IOException ioException){

            }
        });
    }
}

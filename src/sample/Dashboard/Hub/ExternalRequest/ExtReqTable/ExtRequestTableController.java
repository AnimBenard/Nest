package sample.Dashboard.Hub.ExternalRequest.ExtReqTable;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Alert.SetRedNode;
import sample.Dashboard.Hub.ExternalRequest.ExtReqTable.PreviewExtResults.PreviewExtResultsController;
import sample.Dashboard.Hub.ExternalRequest.ExtRequestListDataClass;
import sample.Dashboard.Hub.ExternalRequest.RetrieveExtRequestListData;
import sample.Dashboard.Hub.FetchLastSampleId;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ExtRequestTableController implements Initializable {
    @FXML
    TextField tf_sampleID, tf_name, tf_specimen, tf_age, tf_sex, tf_date, tf_address, tf_refFacID, tf_refFac;
    @FXML
    ComboBox<String> cb_status, cb_comment;
    @FXML
    Button btn_save;
    @FXML
    TableView<ExtRequestListDataClass> tv_AL_reqList;
    @FXML
    TableColumn<ExtRequestListDataClass,String> tc_AL_sampleID, tc_AL_name, tc_AL_refFac, tc_AL_refFacId, tc_AL_time, tc_AL_date, tc_AL_user;
    @FXML
    TableView<ExtRequestListDataClass> tv_requestList;
    @FXML
    TableColumn<ExtRequestListDataClass,String> tc_sampleId, tc_surname, tc_othername, tc_age, tc_sex, tc_address, tc_disTBNo, tc_refFacility, tc_specType, tc_appearance, tc_reasonForExam, tc_time, tc_date, tc_user;
    @FXML
    Label lb_ID, lb_user, lb_userNo, lb_refreshALTable, lb_refreshRLTable, lb_previewExtResults, lb_error;

    RetrieveExtRequestListData retrieveExtRequestListData = new RetrieveExtRequestListData();
    ObservableList<ExtRequestListDataClass> pendingRequestData = FXCollections.observableArrayList();
    ObservableList<ExtRequestListDataClass> acceptedRequestData = FXCollections.observableArrayList();
    AcceptRequest acceptRequest = new AcceptRequest();
    SetRedNode setRedNode = new SetRedNode();
    CopyExtReqToSpokes copyExtReqToSpokes = new CopyExtReqToSpokes();
    RetrieveRequestID retrieveRequestID = new RetrieveRequestID();
    UpdateReqWithSampleId updateReqWithSampleId = new UpdateReqWithSampleId();
    FetchLastSampleId fetchLastSampleId = new FetchLastSampleId();

    String SAMPLE_ID;
    Integer PATH_NUMBER;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPendingRequestToTable();
        addItemsToCb();
        addAcceptReqToTable();
        addItemsToCB();
        addPendingReqDataToFields();
        acceptSample();
        addAcceptTableDataToFields();
    }
    public void setUserLabels(String name, String userNo){
        lb_user.setText(name);
        lb_userNo.setText(userNo);
    }
    public void addPendingRequestToTable(){
        pendingRequestData.clear();
        retrieveExtRequestListData.retrieveAwaitingList(pendingRequestData);
        tc_AL_sampleID.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_ID"));
        tc_AL_name.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));
        tc_AL_refFac.setCellValueFactory(new PropertyValueFactory<>("REQUEST_FAC"));
        tc_AL_refFacId.setCellValueFactory(new PropertyValueFactory<>("REQUEST_FAC_CODE"));
        tc_AL_time.setCellValueFactory(new PropertyValueFactory<>("REQUEST_TIME"));
        tc_AL_date.setCellValueFactory(new PropertyValueFactory<>("REQUEST_DATE"));
        tc_AL_user.setCellValueFactory(new PropertyValueFactory<>("REQUEST_USER"));
        tv_AL_reqList.setItems(null);
        tv_AL_reqList.setItems(pendingRequestData);
        clearFields();
    }
    public void addItemsToCb(){
        ObservableList<String> commentData = FXCollections.observableArrayList();
        commentData.addAll("Small sample volume", "Incorrect labelling", "Shortage of cartridges", "Broken analyser");
        cb_comment.setItems(commentData);
    }
    public void addPendingReqDataToFields(){
        tv_AL_reqList.setOnMouseClicked(e -> {
            tv_AL_reqList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ExtRequestListDataClass item = tv_AL_reqList.getSelectionModel().getSelectedItem();
            tf_sampleID.setText(item.getSAMPLE_ID());
            tf_name.setText(item.getFULL_NAME());
            tf_specimen.setText(item.getSPECIMEN_TYPE());
            tf_age.setText(item.getAGE());
            tf_sex.setText(item.getSEX());
            tf_date.setText(item.getREQUEST_DATE());
            tf_address.setText(item.getADDRESS());
            tf_refFacID.setText(item.getREQUEST_FAC_CODE());
            tf_refFac.setText(item.getREF_FACILITY());
            lb_ID.setText(String.valueOf(item.getID()));
        });
    }
    public void addItemsToCB(){
        ObservableList<String> statusData = FXCollections.observableArrayList();
        statusData.addAll("Accepted", "Rejected");
        cb_status.setItems(statusData);
    }
    public void acceptSample(){
        btn_save.setOnMouseClicked(e -> {
            if(tf_sampleID.getText() == null || tf_sampleID.getText().isEmpty()){
                setRedNode.setRedTextField(tf_sampleID);
                new Shake(tf_sampleID).play();
            }else if(cb_status.getValue() == null || cb_status.getValue().isEmpty()){
                setRedNode.setRedCB(cb_status);
                new Shake(cb_status).play();
            }else {
                createSampleId();
                String id = lb_ID.getText();
                String status = cb_status.getValue();
                String comment = cb_comment.getValue();
                retrieveRequestID.acceptExternalReq(id);
                String reqId = retrieveRequestID.getREQUEST_ID();
                if(cb_status.getValue().equals("Accepted")){
                    acceptRequest.acceptExternalReq(status, comment, id);
                    copyExtReqToSpokes.copyRequest(id);
                    updateReqWithSampleId.acceptExternalReq(String.valueOf(PATH_NUMBER), SAMPLE_ID, reqId);
                }else {
                    if(cb_comment.getValue() == null || cb_comment.getValue().isEmpty()){
                        setRedNode.setRedCB(cb_comment);
                        new Shake(cb_comment).play();
                    }else {
                        String reason = cb_comment.getValue();
                        acceptRequest.acceptExternalReq(status, reason, id);
                    }
                }
                addPendingRequestToTable();
                addAcceptReqToTable();
            }
        });
    }
    public void createSampleId(){
        Integer last2Digit = LocalDate.now().getYear() % 100;
        fetchLastSampleId.fetchLastSampleIdSpokes();
        PATH_NUMBER = fetchLastSampleId.getLAST_SERIAL() + 1;
        String format = String.format("%03d", PATH_NUMBER);
        SAMPLE_ID = "S-"+ format + "-"+last2Digit;
    }

    @FXML
    public void refreshTables(MouseEvent event){
        addPendingRequestToTable();
        addAcceptReqToTable();
    }
    public void clearFields(){
        tf_sampleID.clear();
        tf_name.clear();
        tf_specimen.clear();
        tf_age.clear();
        tf_sex.clear();
        tf_date.clear();
        tf_address.clear();
        tf_refFacID.clear();
        tf_refFac.clear();
        cb_status.setValue(null);
        cb_comment.setValue(null);
        lb_ID.setText(null);
    }
    public void addAcceptReqToTable(){
        acceptedRequestData.clear();
        retrieveExtRequestListData.retrieveReceivedList(acceptedRequestData);
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
        tv_requestList.setItems(acceptedRequestData);
        //changeRowColor();
    }
    public void changeRowColor(){
        tv_requestList.setRowFactory(new Callback<TableView<ExtRequestListDataClass>, TableRow<ExtRequestListDataClass>>() {
            @Override
            public TableRow<ExtRequestListDataClass> call(TableView<ExtRequestListDataClass> requestListDataClassTableView) {
                return new TableRow<ExtRequestListDataClass>(){
                    @Override
                    protected void updateItem(ExtRequestListDataClass extRequestListDataClass, boolean empty) {
                        super.updateItem(extRequestListDataClass, empty);
                        if(extRequestListDataClass == null || empty){
                            setStyle("");
                        }else if(extRequestListDataClass.getACCEPTANCE_STATUS().toLowerCase().contains("Reject".toLowerCase())){
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
    public void addAcceptTableDataToFields(){
        tv_requestList.setOnMouseClicked(e -> {
            tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ExtRequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
            tf_sampleID.setText(item.getSAMPLE_ID());
            tf_name.setText(item.getFULL_NAME());
            tf_specimen.setText(item.getSPECIMEN_TYPE());
            tf_age.setText(item.getFULL_AGE());
            tf_sex.setText(item.getSEX());
            tf_date.setText(item.getREQUEST_DATE());
            tf_address.setText(item.getADDRESS());
            tf_refFacID.setText(item.getREQUEST_FAC_CODE());
            tf_refFac.setText(item.getREF_FACILITY());
            cb_status.setValue(item.getACCEPTANCE_STATUS());
            lb_ID.setText(String.valueOf(item.getID()));
        });
    }
    @FXML
    public void openExtResultsDialog(MouseEvent event){
        if(lb_ID.getText() == null || lb_ID.getText().isEmpty()){
            lb_error.setText("Select item from table");
            new Shake(lb_error).play();
        }else {
            tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ExtRequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
            String name = item.getFULL_NAME();
            String samId = item.getSAMPLE_ID();
            String age = item.getFULL_AGE();
            String sex = item.getSEX();
            String address = item.getADDRESS();
            String reType = item.getREQUEST_TYPE();
            String fac = item.getDESTINATION_FAC();
            String reason = item.getREASON_FOR_EXAMINATION();
            String reqId = item.getREQUEST_ID();
            try{
                Image icon = new Image("/sample/images/etblogo.png");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Hub/ExternalRequest/ExtReqTable/PreviewExtResults/previewExtResults.fxml"));
                DialogPane pane = loader.load();
                PreviewExtResultsController controller = loader.getController();
                controller.setUserLabels(lb_user.getText(), lb_userNo.getText());
                controller.getPatientInfo(name, samId, age, sex, address, reType, fac, reason, reqId);
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
}

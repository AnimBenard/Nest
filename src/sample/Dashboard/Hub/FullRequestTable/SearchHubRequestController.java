package sample.Dashboard.Hub.FullRequestTable;

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
import sample.Alert.DialogBox;
import sample.Dashboard.Hub.CheckIfResultsAdded;
import sample.Dashboard.Hub.CopyDeletedRow;
import sample.Dashboard.Hub.DeleteRequest;
import sample.Dashboard.Hub.RequestListDataClass;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchHubRequestController implements Initializable {
    @FXML
    DatePicker dp_to, dp_from;
    @FXML
    Label lb_ID, lb_name, lb_userNo, lb_remove;
    @FXML
    Button btn_search;
    @FXML
    TextField tf_search;
    @FXML
    TableColumn<RequestListDataClass,String> tc_sampleId, tc_surname, tc_othername, tc_age, tc_sex, tc_address, tc_disTBNo, tc_refFacility, tc_specType, tc_appearance, tc_reasonForExam, tc_time, tc_date, tc_user;
    @FXML
    TableView<RequestListDataClass> tv_requestList;

    DialogBox dialogBox = new DialogBox();
    RetrieveRequestListDataByDate retrieveRequestListDataByDate = new RetrieveRequestListDataByDate();
    ObservableList<RequestListDataClass> requestListData = FXCollections.observableArrayList();
    CheckIfResultsAdded checkIfResultsAdded = new CheckIfResultsAdded();
    DeleteRequest deleteRequest = new DeleteRequest();
    CopyDeletedRow copyDeletedRow = new CopyDeletedRow();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dp_from.setValue(LocalDate.now());
        dp_to.setValue(LocalDate.now());
        searchRequest();
        addTableItemToFields();
    }
    public void setUsername(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void searchRequest(){
        btn_search.setOnMouseClicked(e -> {
            if(dp_from.getValue() == null || dp_to.getValue() == null){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Select date and search";
                String dtitle = "Error!";
                ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
            }else {
                String from = String.valueOf(dp_from.getValue());
                String to = String.valueOf(dp_to.getValue());
                addRequestToTable(from, to);
            }
        });
    }
    public void addRequestToTable(String from, String to){
        requestListData.clear();
        retrieveRequestListDataByDate.retrieveRequestList(requestListData, from, to);
        tc_sampleId.setCellValueFactory(new PropertyValueFactory<>("SAMPLE_ID"));
        tc_surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
        tc_othername.setCellValueFactory(new PropertyValueFactory<>("OTHERNAME"));
        tc_age.setCellValueFactory(new PropertyValueFactory<>("AGE"));
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
                                        lb_ID.setText(null);//clear fields
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        }

    }
    public void addTableItemToFields(){
        tv_requestList.setOnMouseClicked(e -> {
            tv_requestList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            RequestListDataClass item = tv_requestList.getSelectionModel().getSelectedItem();
            lb_ID.setText(String.valueOf(item.getID()));
        });
    }

}

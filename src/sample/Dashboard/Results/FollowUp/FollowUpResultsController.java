package sample.Dashboard.Results.FollowUp;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;
import sample.Dashboard.Results.GeneExpert.AddResultsToDb;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class FollowUpResultsController implements Initializable {
    @FXML
    Label lb_patientName, lb_samleID, lb_date, lb_userName, lb_userNo, lb_ID;
    @FXML
    Button btn_addResult;
    @FXML
    ComboBox<String> cb_results;
    @FXML
    TextArea ta_remarks;
    @FXML
    DialogPane dPane_geneExpertDialog;

    Stage stage;
    SetRedNode setRedNode = new SetRedNode();
    DialogBox dialogBox = new DialogBox();
    AddResultsToDb addResultsToDb = new AddResultsToDb();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addItemsToCB();
        addResultsToDB();
    }
    public void setUserLabels(String name, String userNo){
        lb_userName.setText(name);
        lb_userNo.setText(userNo);

    }
    public void getPatientInfo(String name, String samID, String date){
        lb_patientName.setText(name);
        lb_samleID.setText(samID);
        lb_date.setText(date);
    }
    public void addItemsToCB(){
        ObservableList<String> resultsData = FXCollections.observableArrayList();
        resultsData.addAll("Scanty", "1+", "2+", "3+", "4+","Neg");
        cb_results.setItems(resultsData);
    }
    public void addResultsToDB(){
        btn_addResult.setOnMouseClicked(e -> {
            if(cb_results.getValue() == null || cb_results.getValue().isEmpty()){
                setRedNode.setRedCB(cb_results);
                new Shake(cb_results).play();
            }else {
                resultsData();//adds result to DB
                clearFields();
            }
        });
    }
    public void resultsData(){
        setRedNode.timeAndDate();
        String results = cb_results.getValue();
        String remarks = ta_remarks.getText();
        String time = setRedNode.getTIME();
        Date date = setRedNode.getDATE();
        String personnel = lb_userName.getText();
        String samNo = lb_samleID.getText();
        addResultsToDb.addResults(results, personnel, time, date, remarks, samNo, 0);
    }
    public void clearFields(){
        ta_remarks.clear();
        cb_results.setValue(null);
    }
}

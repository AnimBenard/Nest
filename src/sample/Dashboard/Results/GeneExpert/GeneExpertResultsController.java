package sample.Dashboard.Results.GeneExpert;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class GeneExpertResultsController implements Initializable {
    @FXML
    Label lb_patientName, lb_samleID, lb_date, lb_userName, lb_userNo, lb_ID;
    @FXML
    Button btn_addResult;
    @FXML
    ComboBox<String> cb_results, cb_repeat;
    @FXML
    TextArea ta_remarks;

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
        resultsData.addAll("Pos/RS", "Pos/RR", "Pos/RI", "Neg", "Err", "Inv");
        cb_results.setItems(resultsData);

        ObservableList<String> repeatData = FXCollections.observableArrayList();
        repeatData.addAll("1", "2", "3", "4", "5", "6");
        cb_repeat.setValue("1");
        cb_repeat.setItems(repeatData);
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
        Integer used = Integer.valueOf(cb_repeat.getValue());
        addResultsToDb.addResults(results, personnel, time, date, remarks, samNo, used);

    }
    public void clearFields(){
        ta_remarks.clear();
        cb_results.setValue(null);
    }
}

package sample.Dashboard.Results.PreviewResults;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PreviewResultsController implements Initializable {
    @FXML
    Label lb_patientName, lb_sampleID, lb_age, lb_sex, lb_address, lb_class, lb_refFacility, lb_requestID, lb_reasonForExam, lb_results, lb_user, lb_date;
    @FXML
    Label lb_name, lb_userNo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void getResultsData(String patientName, String sampleID, String age, String sex, String address, String type, String facility, String reqID, String reasonForExam,
                               String results, String user, String date){
        lb_patientName.setText(patientName);
        lb_sampleID.setText(sampleID);
        lb_age.setText(age);
        lb_sex.setText(sex);
        lb_address.setText(address);
        lb_class.setText(type);
        lb_refFacility.setText(facility);
        lb_requestID.setText(reqID);
        lb_reasonForExam.setText(reasonForExam);
        lb_results.setText(results);
        lb_user.setText(user);
        lb_date.setText(date);
        if(results.toLowerCase().equals("Pos/RS".toLowerCase())){
            lb_results.setText("MTB DETECTED RIF RESISTANT NOT DETECTED");
            lb_results.setStyle("-fx-background-color: #ffacbc");
        }else if(results.toLowerCase().equals("Pos/RR".toLowerCase())){
            lb_results.setText(" MTB DETECTED RIF RESISTANT DETECTED");
            lb_results.setStyle("-fx-background-color: #ffacbc");
        }else if(results.toLowerCase().equals("Pos/RI".toLowerCase())){
            lb_results.setText("MTB DETECTED RIF RESISTANT INDETERMINATE");
            lb_results.setStyle("-fx-background-color: #ffacbc");
        }else if(results.toLowerCase().equals("Neg".toLowerCase())){
            lb_results.setText("MTB NOT DETECTED");
            lb_results.setStyle("-fx-background-color: #9df2a1");
        }else if(results.toLowerCase().equals("Err".toLowerCase())){
            lb_results.setStyle("-fx-background-color: #dcdcdc");
        }else if(results.toLowerCase().contains("+".toLowerCase())){
            lb_results.setStyle("-fx-background-color: #ffacbc");
        }else if(results.toLowerCase().contains("SCANTY".toLowerCase())){
            lb_results.setStyle("-fx-background-color: #ffacbc");
        }
        else {
            lb_results.setStyle(null);
        }
    }
}

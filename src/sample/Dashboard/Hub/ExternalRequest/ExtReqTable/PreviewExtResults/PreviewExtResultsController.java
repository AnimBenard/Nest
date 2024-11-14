package sample.Dashboard.Hub.ExternalRequest.ExtReqTable.PreviewExtResults;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.Dashboard.Hub.CheckIfResultsAdded;
import sample.Dashboard.Hub.ExternalRequest.ExtReqTable.AcceptRequest;

import java.net.URL;
import java.util.ResourceBundle;

public class PreviewExtResultsController implements Initializable {
    @FXML
    Label lb_patientName, lb_sampleID, lb_age, lb_sex, lb_address, lb_class, lb_refFacility, lb_requestID, lb_reasonForExam, lb_results, lb_user, lb_date;
    @FXML
    Label lb_name, lb_userNo, lb_status;

    RetrieveExtResults retrieveExtResults = new RetrieveExtResults();
    AcceptRequest acceptRequest = new AcceptRequest();
    CheckIfResultsAdded checkIfResultsAdded = new CheckIfResultsAdded();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setUserLabels(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    public void getPatientInfo(String name, String samId, String age, String sex, String address, String category, String fac,
                               String reasonForExam, String reqId){
        lb_patientName.setText(name);
        lb_sampleID.setText(samId);
        lb_age.setText(age);
        lb_sex.setText(sex);
        lb_address.setText(address);
        lb_class.setText(category);
        lb_refFacility.setText(fac);
        lb_reasonForExam.setText(reasonForExam);
        lb_requestID.setText(reqId);
        checkIfSampleReceived(reqId);
        getResults(reqId);
    }
    public void checkIfSampleReceived(String reqId){
        boolean received = acceptRequest.checkIfRequestAccepted(reqId);
        if(received){
            lb_status.setText("Sample Accepted -" + acceptRequest.getCOMMENT());
        }else {
            lb_status.setText("Sample Rejected - " + acceptRequest.getCOMMENT()) ;
        }
    }
    public void getResults(String reqId){

        boolean isAdded = checkIfResultsAdded.checkResultsAddedByRequestId(reqId);
        if(isAdded){
            retrieveExtResults.retrieveExtRes(reqId);
            String results = retrieveExtResults.getRESULTS();
            System.out.println("this is the final results " + results);
            lb_user.setText(retrieveExtResults.getRES_USER());
            lb_date.setText(retrieveExtResults.getDATE());
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
                lb_results.setText(results);
                lb_results.setStyle("-fx-background-color: #dad9d3");
            }
        }else {
            lb_results.setText("NO RESULTS FOUND");
            lb_results.setStyle("-fx-background-color: ORANGE");
        }
    }
}

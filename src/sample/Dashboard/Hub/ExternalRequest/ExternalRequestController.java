package sample.Dashboard.Hub.ExternalRequest;

import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Alert.SetRedNode;

import java.net.URL;
import java.util.ResourceBundle;

public class ExternalRequestController implements Initializable {
    @FXML
    Label lb_sampleID, lb_name, lb_age, lb_sex, lb_specimen, lb_address, lb_reason, lb_fromCode, lb_toCode, lb_userNo, lb_user, lb_ID, lb_reqId;
    @FXML
    ComboBox<String> cb_from, cb_to;
    @FXML
    Button btn_sendRequest;

    SetRedNode setRedNode = new SetRedNode();
    FetchFacilityAndCode fetchFacilityAndCode = new FetchFacilityAndCode();
    ObservableList<String> refFacData = FXCollections.observableArrayList();
    AddExternalRequestToDb addExternalRequestToDb = new AddExternalRequestToDb();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveFacToCb();
        retrieveCodeToCb();

    }
    public void getUserNo(String name, String userNo) {
        lb_user.setText(name);
        lb_userNo.setText(userNo);
    }
    public void retrieveFacToCb(){
        refFacData.clear();
        fetchFacilityAndCode.fetchRefFac(refFacData);
        cb_from.setItems(refFacData);
        cb_to.setItems(refFacData);
    }
    public void retrieveCodeToCb(){
        cb_from.valueProperty().addListener((observableValue, s, val) -> {
            fetchFacilityAndCode.fetchCode(val);
            lb_fromCode.setText(fetchFacilityAndCode.getFAC_CODE());
        });
        cb_to.valueProperty().addListener((observableValue, s, val) -> {
            fetchFacilityAndCode.fetchCode(val);
            lb_toCode.setText(fetchFacilityAndCode.getFAC_CODE());
        });
    }
    public void setClientInfo(String id, String samId, String clientName, String age, String sex, String sputum, String address, String reason, String reqId){
        lb_ID.setText(id);
        lb_sampleID.setText(samId);
        lb_name.setText(clientName);
        lb_age.setText(age);
        lb_sex.setText(sex);
        lb_specimen.setText(sputum);
        lb_address.setText(address);
        lb_reason.setText(reason);
        lb_reqId.setText(reqId);
    }
    public void addExternalRequestToDb(Stage stage){
        btn_sendRequest.setOnMouseClicked(e -> {
            if(cb_from.getValue() == null || cb_from.getValue().isEmpty()){
                setRedNode.setRedCB(cb_from);
                new Shake(cb_from).play();
            }else if(cb_to.getValue() == null || cb_to.getValue().isEmpty()){
                setRedNode.setRedCB(cb_to);
                new Shake(cb_to).play();
            }else {
                String from = cb_from.getValue();
                String to = cb_to.getValue();
                String fCode = lb_fromCode.getText();
                String tCode = lb_toCode.getText();
                String reqId = lb_reqId.getText();
                String id = lb_ID.getText();
                addExternalRequestToDb.addExternalReq(id);
                addExternalRequestToDb.addExternalReqWithFacility(to, tCode, from, fCode, reqId);
                closeStage(stage);
            }
        });
    }
    public void closeStage(Stage stage){
        stage.close();
    }
    //add retrieve external request to table
    //receive request
}

package sample.Dashboard.Users;

import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Alert.DialogBox;
import sample.Alert.SetRedNode;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    TextField tf_surname, tf_othername, tf_username, tf_verification, tf_time, tf_date, tf_user;
    @FXML
    ComboBox<String> cb_grade;
    @FXML
    Label lb_name, lb_userID, lb_ID;
    @FXML
    TableColumn<UserAccountDataClass,String> tc_av_usrId, tc_av_surname, tc_av_othername, tc_av_verification, tc_av_time, tc_av_date;
    @FXML
    TableView<UserAccountDataClass> tv_awaitingValidation;
    @FXML
    TableColumn<UserAccountDataClass,String> tc_vu_userId, tc_vu_surname, tc_vu_othername, tc_vu_verification, tc_vu_signupTime, tc_vu_signupDate, tc_vu_verificationDate, tc_vu_verificationUser, tc_vu_userRole;
    @FXML
    TableView<UserAccountDataClass> tv_verifiedUsers;
    @FXML
    Button btn_validate, btn_remove;

    RetrievePendingUsers retrievePendingUsers = new RetrievePendingUsers();
    ObservableList<UserAccountDataClass> pendUsersData = FXCollections.observableArrayList();
    ValidateUsers validateUsers = new ValidateUsers();
    SetRedNode setRedNode = new SetRedNode();
    RetrieveValidUsers retrieveValidUsers = new RetrieveValidUsers();
    ObservableList<UserAccountDataClass> validUsersData = FXCollections.observableArrayList();
    RemoveUser removeUser = new RemoveUser();
    DialogBox dialogBox = new DialogBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPendingUsersToTable();
        addPendingUserToFields();
        addValidatedUsersToTable();
        addValUsersDataToFields();
        addAccessLevelToCB();
        validateUserAccount();

    }
    public void setUserLabels(String name, String userID){
        lb_name.setText(name);
        lb_userID.setText(userID);
    }
    public void addPendingUsersToTable(){
        pendUsersData.clear();
        retrievePendingUsers.retrievePendVerificationUsers(pendUsersData);
        tc_av_usrId.setCellValueFactory(new PropertyValueFactory<>("USERNAME"));
        tc_av_surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
        tc_av_othername.setCellValueFactory(new PropertyValueFactory<>("OTHER_NAMES"));
        tc_av_verification.setCellValueFactory(new PropertyValueFactory<>("VERIFICATION"));
        tc_av_time.setCellValueFactory(new PropertyValueFactory<>("FILLED_TIME"));
        tc_av_date.setCellValueFactory(new PropertyValueFactory<>("FILLED_DATE"));
        tv_awaitingValidation.setItems(null);
        tv_awaitingValidation.setItems(pendUsersData);
    }
    public void addPendingUserToFields(){
        tv_awaitingValidation.setOnMouseClicked(e -> {
            tv_awaitingValidation.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            UserAccountDataClass item = tv_awaitingValidation.getSelectionModel().getSelectedItem();
            tf_surname.setText(item.getSURNAME());
            tf_othername.setText(item.getOTHER_NAMES());
            tf_username.setText(item.getUSERNAME());
            tf_verification.setText(item.getVERIFICATION());
            tf_time.setText(item.getFILLED_TIME());
            tf_date.setText(item.getFILLED_DATE());
            tf_user.setText(lb_name.getText());
            lb_ID.setText(String.valueOf(item.getID()));
            btn_validate.setDisable(false);
        });
    }
    public void addAccessLevelToCB(){
        ObservableList<String> accessLevelData = FXCollections.observableArrayList();
        accessLevelData.addAll("ADMINISTRATOR", "USER");
        cb_grade.setItems(accessLevelData);
    }
    public void validateUserAccount(){
        btn_validate.setOnMouseClicked(e -> {
            if(tf_surname.getText() == null || tf_surname.getText().isEmpty()){
                setRedNode.setRedTextField(tf_surname);
                new Shake(tf_surname).play();
            }else if(cb_grade.getValue() == null || cb_grade.getValue().isEmpty()){
                setRedNode.setRedCB(cb_grade);
                new Shake(cb_grade).play();
            }else {
                setRedNode.unSetRedCB(cb_grade);
                setRedNode.unSetRedTextField(tf_surname);
                setRedNode.timeAndDate();
                String verification = "VERIFIED";
                String grade = cb_grade.getValue();
                String time = setRedNode.getTIME();
                Date date = setRedNode.getDATE();
                String user = lb_name.getText();
                Integer id = Integer.valueOf(lb_ID.getText());
                validateUsers.validateUsers(grade, verification, time, date, user, id);
                refreshTableAndClearFields();
            }
        });
    }
    public void refreshTableAndClearFields(){
        addValidatedUsersToTable();//refresh validated users table
        addPendingUsersToTable();//refresh pending users table
        clearFields();
    }
    public void clearFields(){
        tf_surname.clear();
        tf_othername.clear();
        tf_username.clear();
        tf_verification.clear();
        tf_time.clear();
        tf_date.clear();
        cb_grade.setValue(null);
        lb_ID.setText(null);
    }
    public void addValidatedUsersToTable(){
        validUsersData.clear();
        retrieveValidUsers.retrieveValidatedUsers(validUsersData);
        tc_vu_userId.setCellValueFactory(new PropertyValueFactory<>("USERNAME"));
        tc_vu_surname.setCellValueFactory(new PropertyValueFactory<>("SURNAME"));
        tc_vu_othername.setCellValueFactory(new PropertyValueFactory<>("OTHER_NAMES"));
        tc_vu_userRole.setCellValueFactory(new PropertyValueFactory<>("GRADE"));
        tc_vu_verification.setCellValueFactory(new PropertyValueFactory<>("VERIFICATION"));
        tc_vu_signupTime.setCellValueFactory(new PropertyValueFactory<>("FILLED_TIME"));
        tc_vu_signupDate.setCellValueFactory(new PropertyValueFactory<>("FILLED_DATE"));
        tc_vu_verificationDate.setCellValueFactory(new PropertyValueFactory<>("VERIFICATION_DATE"));
        tc_vu_verificationDate.setCellValueFactory(new PropertyValueFactory<>("VERIFICATION_TIME"));
        tc_vu_verificationUser.setCellValueFactory(new PropertyValueFactory<>("USER"));
        tv_verifiedUsers.setItems(null);
        tv_verifiedUsers.setItems(validUsersData);
    }
    public void addValUsersDataToFields(){
        tv_verifiedUsers.setOnMouseClicked(e -> {
            tv_verifiedUsers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            UserAccountDataClass item = tv_verifiedUsers.getSelectionModel().getSelectedItem();
            tf_surname.setText(item.getSURNAME());
            tf_othername.setText(item.getOTHER_NAMES());
            tf_username.setText(item.getUSERNAME());
            tf_verification.setText(item.getVERIFICATION());
            tf_time.setText(item.getFILLED_TIME());
            tf_date.setText(item.getFILLED_DATE());
            tf_user.setText(lb_name.getText());
            cb_grade.setValue(item.getGRADE());
            lb_ID.setText(String.valueOf(item.getID()));
            btn_validate.setDisable(true);
        });
    }

    @FXML
    public void removeFromUserRegister(ActionEvent event) {
        if (lb_ID.getText() == null || lb_ID.getText().isEmpty()) {
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Select user to remove";
            String dtitle = "Error!";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
        } else {
            Image icon = new Image("/sample/images/etblogo.png");
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirm");
            dialog.setContentText("Do you want to remove this user..");
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
                                    Integer id = Integer.valueOf(lb_ID.getText());
                                    removeUser.removeData(id);
                                    refreshTableAndClearFields();
                                }
                            });
                        }
                    }).start();
                }
            });
        }
    }

}

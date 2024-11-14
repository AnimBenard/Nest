package sample.Dashboard.SetUser;

import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sample.Alert.SetRedNode;

import java.net.URL;
import java.util.ResourceBundle;

public class SetUserController implements Initializable {
    @FXML
    Button btn_setUser;
    @FXML
    PasswordField pf_password;
    @FXML
    ComboBox<String> cb_username;

    SetRedNode setRedNode = new SetRedNode();
    FetchUsers fetchUsers = new FetchUsers();
    CheckUserPassword checkPassword = new CheckUserPassword();
    ObservableList<String> usersList = FXCollections.observableArrayList();
    String USER, USER_FULL_NAME;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void addUsersToCB(){
        fetchUsers.fetchUsers(usersList);
        cb_username.setItems(usersList);
    }
    public void setUser(Stage stage, Label label){
        btn_setUser.setOnMouseClicked(e -> {
            if(cb_username.getValue() == null || cb_username.getValue().isEmpty()){
                setRedNode.setRedCB(cb_username);
                new Shake(cb_username).play();
            }else if(pf_password.getText().isEmpty()){
                setRedNode.setRedPF(pf_password);
                new Shake(pf_password).play();
            }else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        USER = cb_username.getValue();
                        String password = pf_password.getText();
                        boolean userFound = checkPassword.checkPassword(USER, password);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(userFound){
                                    fetchUsers.fetchFullNameByUsername(USER);
                                    USER_FULL_NAME = fetchUsers.getNAME();
                                    closeDialog(stage);
                                    setUserNameToLabel(label);
                                }else {
                                    pf_password.setStyle("-fx-border-color: RED; -fx-border-radius: 5; -fx-background-radius: 5");
                                    new Shake(pf_password).play();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
    public void closeDialog(Stage stage){
        stage.close();
    }
    public void setUserNameToLabel(Label label){
        label.setText(USER_FULL_NAME);
    }
}

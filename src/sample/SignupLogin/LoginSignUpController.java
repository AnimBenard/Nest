package sample.SignupLogin;

import animatefx.animation.Shake;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Alert.SetRedNode;
import sample.Dashboard.OpenDashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class LoginSignUpController implements Initializable {
    @FXML
    AnchorPane ach_createAccount, ach_signIn;
    @FXML
    Label lb_login, lb_signUp;
    @FXML
    TextField tf_surname, tf_othernames, tf_user_id, tf_login_username;
    @FXML
    PasswordField pf_sign_password, pf_sign_password_rep, tf_login_password;
    @FXML
    Button btn_sign_up, btn_signin;
    @FXML
    Label lb_createAccError, lb_loginError;
    @FXML
    ProgressIndicator pi_spin;

    CreateNewAccount createNewAccount = new CreateNewAccount();
    RetrieveLastUserId retrieveLastUserId = new RetrieveLastUserId();
    SetRedNode setRedNode = new SetRedNode();
    CheckLoginDetails checkLoginDetails = new CheckLoginDetails();
    OpenLogin openLogin = new OpenLogin();
    OpenDashboard openDashboard = new OpenDashboard();
    RetrieveUsername retrieveUsername = new RetrieveUsername();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRedNode.formatTFieldToUpper(tf_surname);
        setRedNode.formatTFieldToUpper(tf_othernames);
        setRedNode.formatTFieldToUpper(tf_user_id);
        handleCreateAccount();
        handleLogin();
    }
    @FXML
    public void handleCreateAccountLabelClick(MouseEvent event){
        ach_signIn.setVisible(false);
        ach_createAccount.setVisible(true);
        lb_signUp.setVisible(false);
        lb_login.setVisible(true);

        //add user id to field
        generateUserId();
    }
    public void generateUserId(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                retrieveLastUserId.lastUserId();
                Integer num = retrieveLastUserId.getUSER_NO() + 1;
                String format = String.format("%03d", num);
                String userId = "UID-"+ format;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tf_user_id.setText(userId);
                    }
                });
            }
        }).start();
    }
    @FXML
    public void handleSignInLabelClick(MouseEvent event){
        ach_signIn.setVisible(true);
        ach_createAccount.setVisible(false);
        lb_signUp.setVisible(true);
        lb_login.setVisible(false);
    }
    public void handleCreateAccount(){
        btn_sign_up.setOnMouseClicked(e -> {
            if(tf_surname.getText() == null || tf_surname.getText().isEmpty()){
                lb_createAccError.setText("Surname cannot be empty");
                new Shake(lb_createAccError).play();
            }else if(tf_othernames.getText() == null || tf_othernames.getText().isEmpty()){
                lb_createAccError.setText("Othernames cannot be empty");
                new Shake(lb_createAccError).play();
            }else if(tf_user_id.getText() == null || tf_user_id.getText().isEmpty()){
                lb_createAccError.setText("User id cannot be empty");
                new Shake(lb_createAccError).play();
            }else if(pf_sign_password.getText() == null || pf_sign_password.getText().isEmpty() || pf_sign_password_rep.getText() == null || pf_sign_password_rep.getText().isEmpty()){
                lb_createAccError.setText("Enter a valid password");
                new Shake(lb_createAccError).play();
            }else {
                if(pf_sign_password.getText().equals(pf_sign_password_rep.getText())){
                    lb_createAccError.setText("");
                    crateAccountUserDetails();
                    clearFields();
                    generateUserId();
                }else {
                    lb_createAccError.setText("Password does not match");
                    new Shake(lb_createAccError).play();
                }
            }
        });
    }
    public void crateAccountUserDetails(){
        retrieveLastUserId.lastUserId();
        setRedNode.timeAndDate();;
        Integer num = retrieveLastUserId.getUSER_NO() + 1;
        String format = String.format("%03d", num);
        String surname = tf_surname.getText();
        String othernames = tf_othernames.getText();
        String userId = tf_user_id.getText();
        String password = pf_sign_password.getText();
        String time = setRedNode.getTIME();
        Date date = setRedNode.getDATE();
        String verification = "NOT VERIFIED";
        createNewAccount.addUserInfoToFields(surname, othernames, userId, password, format, verification, time, date);
    }
    public void clearFields(){
        tf_surname.clear();
        tf_othernames.clear();
        tf_user_id.clear();
        pf_sign_password.clear();
        pf_sign_password_rep.clear();
    }
    public void handleLogin(){
        btn_signin.setOnMouseClicked(e -> {
            String user = tf_login_username.getText();
            String pass = tf_login_password.getText();
            boolean correct = checkLoginDetails.checkDetails(user, pass);
            if(correct){
                lb_loginError.setText("");
                pi_spin.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                pauseTransition.setOnFinished(e1 -> {
                    openLogin.closeStage();
                    try {
                        retrieveUsername.userInfo(user);
                        String userNo = retrieveUsername.getUSER_NO();
                        String fullName = retrieveUsername.getFULL_NAME();
                        openDashboard.openDashBoardScene(fullName, userNo);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pauseTransition.play();
            }else {
                lb_loginError.setText("Sign in error. Check username or password");
                new Shake(lb_loginError).play();
            }
        });
    }
}

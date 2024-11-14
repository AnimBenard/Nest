package sample.SignupLogin;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class CreateNewAccount {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_USER_INFO = "INSERT INTO user_account (surname, othernames, username, user_password, user_no, verification, account_time, account_date) VALUES (?,?,?,?,?,?,?,?)";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void addUserInfoToFields(String surname, String othername, String userId, String password, String num, String verification, String time, Date date){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_USER_INFO, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, othername);
            preparedStatement.setString(3, userId);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, num);
            preparedStatement.setString(6, verification);
            preparedStatement.setString(7, time);
            preparedStatement.setDate(8, date);
            preparedStatement.executeUpdate();
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Account created successfully";
            String dtitle = "Success!";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

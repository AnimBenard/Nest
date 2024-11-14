package sample.Dashboard.Hub;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class AddRequestToDB {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_REQUEST = "INSERT INTO request_list (serial_id, surname, othernames, age, sex, address, district_tb_number, referring_facility, type_of_specimen, sample_appearance, " +
            "reason_for_examination, request_type, request_time, request_date, request_user, sample_id, request_id, age_units) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void createRequest(String serial, String surname, String othername, String age, String sex, String address, String disTBNo, String refFac, String specType, String sampleApp,
                              String reason, String reqType, String time, Date date, String user, String samId, String reqId, String ageUnits){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_REQUEST, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, serial);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, othername);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, sex);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, disTBNo);
            preparedStatement.setString(8, refFac);
            preparedStatement.setString(9, specType);
            preparedStatement.setString(10, sampleApp);
            preparedStatement.setString(11, reason);
            preparedStatement.setString(12, reqType);
            preparedStatement.setString(13, time);
            preparedStatement.setDate(14, date);
            preparedStatement.setString(15, user);
            preparedStatement.setString(16, samId);
            preparedStatement.setString(17, reqId);
            preparedStatement.setString(18, ageUnits);
            preparedStatement.executeUpdate();
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Request successfully added";
            String dtitle = "Success!";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
        } catch (ClassNotFoundException | SQLException e) {
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

package sample.Dashboard.Hub.ExternalRequest;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class AddExternalRequestToDb {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_REQUEST = "INSERT INTO external_request_list (serial_id, sample_id, surname, othernames, age, age_units, sex, address, district_tb_number, referring_facility, type_of_specimen, sample_appearance, reason_for_examination, request_time, request_date, request_user, request_type, request_id, results, examined_by, examined_time, examined_date, remarks, validation, validation_time, validation_date, validated_by, delete_request, follow_up_months, cartridge_used) " +
            "SELECT serial_id, sample_id, surname, othernames, age, age_units, sex, address, district_tb_number, referring_facility, type_of_specimen, sample_appearance, reason_for_examination, request_time, request_date, request_user, request_type, request_id, results, examined_by, examined_time, examined_date, remarks, validation, validation_time, validation_date, validated_by, delete_request, follow_up_months, cartridge_used FROM request_list WHERE Id = ?";
    private static final String ADD_REQ_UPDATE = "UPDATE external_request_list SET destination_facility = ?, destination_facility_code = ?, request_facility = ?, request_facility_code = ? WHERE request_id = ?";
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void addExternalReq(String id){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_REQUEST, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
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
    public void addExternalReqWithFacility(String desFac, String desFacCode, String reqFac, String reqFacCode, String reqId){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_REQ_UPDATE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, desFac);
            preparedStatement.setString(2, desFacCode);
            preparedStatement.setString(3, reqFac);
            preparedStatement.setString(4, reqFacCode);
            preparedStatement.setString(5, reqId);
            int requested = preparedStatement.executeUpdate();
            if(requested > 0){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Request successfully added";
                String dtitle = "Success!";
                ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
            }
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

package sample.Dashboard.Hub;

import sample.Alert.DialogBox;

import java.sql.*;

public class CopyDeletedRow {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String COPY = "INSERT INTO deleted_request (Id, serial_id, sample_id, surname, othernames, age, sex, address, district_tb_number, referring_facility, type_of_specimen, sample_appearance, reason_for_examination, request_time, request_date, request_user, request_type, request_id, results, examined_by, examined_time, examined_date, remarks, validation, validation_time, validation_date, validated_by, delete_request, follow_up_months) " +
            "SELECT * FROM request_list WHERE Id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void copyRow(Integer id){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(COPY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

package sample.Dashboard.Hub.ExternalRequest.ExtReqTable;

import sample.Alert.DialogBox;

import java.sql.*;

public class CopyExtReqToSpokes {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_QUERY = "INSERT INTO request_list (surname, othernames, age, age_units, sex, address, district_tb_number, referring_facility, type_of_specimen, " +
            "sample_appearance, reason_for_examination, request_time, request_date, request_user, request_type, request_id) " +
            "SELECT surname, othernames, age, age_units, sex, address, district_tb_number, referring_facility, type_of_specimen, " +
            "sample_appearance, reason_for_examination, request_time, request_date, request_user, 'SPOKES', request_id FROM external_request_list WHERE Id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();

    public void copyRequest(String id){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
}

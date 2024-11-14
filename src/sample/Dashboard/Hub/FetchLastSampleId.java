package sample.Dashboard.Hub;

import sample.Alert.DialogBox;

import java.sql.*;

public class FetchLastSampleId {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String LAST_SERIAL_ID = "SELECT IFNULL(serial_id, 0) AS serialID FROM request_list WHERE YEAR(request_date) = YEAR(CURDATE()) AND request_type = 'HUB' ORDER BY Id DESC LIMIT 1";
    private static final String LAST_SERIAL_ID_SPOKES = "SELECT IFNULL(serial_id, 0) AS serialID FROM request_list WHERE YEAR(request_date) = YEAR(CURDATE()) AND request_type = 'SPOKES+' ORDER BY Id DESC LIMIT 1";


    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer LAST_SERIAL;
    public void fetchLastSampleIdHub(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(LAST_SERIAL_ID);
            while (resultSet.next()){
                LAST_SERIAL = resultSet.getInt("serialID");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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

    public void fetchLastSampleIdSpokes(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(LAST_SERIAL_ID_SPOKES);
            while (resultSet.next()){
                LAST_SERIAL = resultSet.getInt("serialID");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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

    public Integer getLAST_SERIAL() {
        return LAST_SERIAL;
    }
}

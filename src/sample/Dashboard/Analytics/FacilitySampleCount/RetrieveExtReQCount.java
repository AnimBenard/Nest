package sample.Dashboard.Analytics.FacilitySampleCount;

import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveExtReQCount {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_TOTAL_COUNT = "SELECT COUNT(Id) AS total FROM external_request_list WHERE acceptance_status IS NOT NULL AND MONTH(request_date) = MONTH(CURDATE())";
    private static final String FETCH_ACCEPT_COUNT = "SELECT COUNT(Id) AS total FROM external_request_list WHERE acceptance_status = 'Accepted' AND MONTH(request_date) = MONTH(CURDATE())";
    private static final String FETCH_REJECT_COUNT = "SELECT COUNT(Id) AS total FROM external_request_list WHERE acceptance_status = 'Rejected' AND MONTH(request_date) = MONTH(CURDATE())";

    String TOTAL_COUNT, ACCEPT_COUNT, REJECT_COUNT;
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void fetchTotalReqCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_COUNT);
            while (resultSet.next()){
                TOTAL_COUNT = resultSet.getString("total");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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

    public void fetchTotalAcceptCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_ACCEPT_COUNT);
            while (resultSet.next()){
                ACCEPT_COUNT = resultSet.getString("total");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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
    public void fetchTotalRejectCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_REJECT_COUNT);
            while (resultSet.next()){
                REJECT_COUNT = resultSet.getString("total");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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

    public String getTOTAL_COUNT() {
        return TOTAL_COUNT;
    }

    public String getACCEPT_COUNT() {
        return ACCEPT_COUNT;
    }

    public String getREJECT_COUNT() {
        return REJECT_COUNT;
    }
}

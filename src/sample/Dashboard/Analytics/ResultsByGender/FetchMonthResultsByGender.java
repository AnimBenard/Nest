package sample.Dashboard.Analytics.ResultsByGender;

import sample.Alert.DialogBox;

import java.sql.*;

public class FetchMonthResultsByGender {
    //fetches the total number of clients registered at the current month
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_TOTAL_POS_MALES = "SELECT COUNT(*) AS total FROM request_list WHERE sex = 'M' AND results REGEXP 'Pos|Scanty|1+|2+|3+|4+' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";
    private static final String FETCH_TOTAL_POS_FEMALES = "SELECT COUNT(*) AS total FROM request_list WHERE sex = 'F' AND results REGEXP 'Pos|Scanty|1+|2+|3+|4+' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer TOTAL_MALES, TOTAL_FEMALES;
    public void getMonthlyCountMales(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_POS_MALES);
            while (resultSet.next()){
                TOTAL_MALES = resultSet.getInt("total");
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
    public void getMonthlyCountFemales(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_POS_FEMALES);
            while (resultSet.next()){
                TOTAL_FEMALES = resultSet.getInt("total");
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

    public Integer getTOTAL_MALES() {
        return TOTAL_MALES;
    }

    public Integer getTOTAL_FEMALES() {
        return TOTAL_FEMALES;
    }
}

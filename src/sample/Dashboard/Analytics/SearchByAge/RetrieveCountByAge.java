package sample.Dashboard.Analytics.SearchByAge;

import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveCountByAge {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_TOTAL_CHILDREN = "SELECT COUNT(*) AS total FROM request_list WHERE age < 13 AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";
    private static final String FETCH_TOTAL_POS_CHILD = "SELECT COUNT(*) AS total FROM request_list WHERE age < 13 AND results REGEXP 'Pos|Scanty|1+|2+|3+|4+' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";
    private static final String FETCH_TOTAL_NEG_CHILD = "SELECT COUNT(*) AS total FROM request_list WHERE age < 13 AND results = 'Neg' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";

    private static final String FETCH_TOTAL_ADULT = "SELECT COUNT(*) AS total FROM request_list WHERE age >= 13 AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";
    private static final String FETCH_TOTAL_POS_ADULT = "SELECT COUNT(*) AS total FROM request_list WHERE age >= 13 AND results REGEXP 'Pos|Scanty|1+|2+|3+|4+' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";
    private static final String FETCH_TOTAL_NEG_ADULT = "SELECT COUNT(*) AS total FROM request_list WHERE age >= 13 AND results = 'Neg' AND MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE())";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer TOTAL_CHILD, POS_CHILD, NEG_CHILD, TOTAL_ADULT, POS_ADULT, NEG_ADULT;

    public void fetchTotalChild(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_CHILDREN);
            while (resultSet.next()){
                TOTAL_CHILD = resultSet.getInt("total");
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
    public void fetchTotalNegChild(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_NEG_CHILD);
            while (resultSet.next()){
                NEG_CHILD = resultSet.getInt("total");
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
    public void fetchTotalPosChild(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_POS_CHILD);
            while (resultSet.next()){
                POS_CHILD = resultSet.getInt("total");
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
    public void fetchTotalAdult(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_ADULT);
            while (resultSet.next()){
                TOTAL_ADULT = resultSet.getInt("total");
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
    public void fetchPosAdult(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_POS_ADULT);
            while (resultSet.next()){
                POS_ADULT = resultSet.getInt("total");
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
    public void fetchNegAdult(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL_NEG_ADULT);
            while (resultSet.next()){
                NEG_ADULT = resultSet.getInt("total");
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

    public Integer getTOTAL_CHILD() {
        return TOTAL_CHILD;
    }

    public Integer getPOS_CHILD() {
        return POS_CHILD;
    }

    public Integer getNEG_CHILD() {
        return NEG_CHILD;
    }

    public Integer getTOTAL_ADULT() {
        return TOTAL_ADULT;
    }

    public Integer getPOS_ADULT() {
        return POS_ADULT;
    }

    public Integer getNEG_ADULT() {
        return NEG_ADULT;
    }
}

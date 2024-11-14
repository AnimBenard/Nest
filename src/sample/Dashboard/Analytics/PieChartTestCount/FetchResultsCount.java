package sample.Dashboard.Analytics.PieChartTestCount;

import sample.Alert.DialogBox;

import java.sql.*;

public class FetchResultsCount {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_TOTAL = "SELECT COUNT(*) AS total FROM request_list WHERE results IS NOT NULL AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_POS = "SELECT COUNT(*) AS total FROM request_list WHERE  results REGEXP 'Pos|Scanty|1+|2+|3+|4+' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_NEG = "SELECT COUNT(*) AS total FROM request_list WHERE results = 'Neg' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_SUCCESSFUL = "SELECT COUNT(*) AS total FROM request_list WHERE results NOT IN('Err','Inv') AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results IS NOT NULL AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_INV = "SELECT COUNT(*) AS total FROM request_list WHERE results = 'Inv' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_ERR = "SELECT COUNT(*) AS total FROM request_list WHERE results = 'Err' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_NEW_CASES = "SELECT COUNT(*) AS total FROM request_list WHERE reason_for_examination = 'TB Diagnosis' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_FOLLOW_UPS = "SELECT COUNT(*) AS total FROM request_list WHERE reason_for_examination = 'Follow Up' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_MALES_COUNT = "SELECT COUNT(*) AS total FROM request_list WHERE sex = 'M' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";
    private static final String FETCH_FEMALES_COUNT = "SELECT COUNT(*) AS total FROM request_list WHERE sex = 'F' AND MONTH(examined_date) = MONTH(CURDATE()) AND YEAR(examined_date) = YEAR(CURDATE())";



    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer TOTAL_RESULTS, TOTAL_POS, TOTAL_NEG, TOTAL_SUCCESSFUL, TOTAL_INV, TOTAL_ERR, TOTAL_NEW_CASES, TOTAL_FOLLOW_UPS, TOTAL_MALES, TOTAL_FEMALES;
    public void fetchTotalResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_TOTAL);
            while (resultSet.next()){
                TOTAL_RESULTS = resultSet.getInt("total");
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
    public void fetchTotalPosResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_POS);
            while (resultSet.next()){
                TOTAL_POS = resultSet.getInt("total");
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
    public void fetchTotalNegResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_NEG);
            while (resultSet.next()){
                TOTAL_NEG = resultSet.getInt("total");
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
    public void fetchTotalSuccessfulResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_SUCCESSFUL);
            while (resultSet.next()){
                TOTAL_SUCCESSFUL = resultSet.getInt("total");
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
    public void fetchTotalInvResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_INV);
            while (resultSet.next()){
                TOTAL_INV = resultSet.getInt("total");
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
    public void fetchTotalErrResultsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_ERR);
            while (resultSet.next()){
                TOTAL_ERR = resultSet.getInt("total");
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
    public void fetchTotalNewCasesCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_NEW_CASES);
            while (resultSet.next()){
                TOTAL_NEW_CASES = resultSet.getInt("total");
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
    public void fetchTotalFollowUpsCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_FOLLOW_UPS);
            while (resultSet.next()){
                TOTAL_FOLLOW_UPS = resultSet.getInt("total");
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
    public void fetchTotalMalesCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_MALES_COUNT);
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
    public void fetchTotalFemalesCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_FEMALES_COUNT);
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

    public Integer getTOTAL_SUCCESSFUL() {
        return TOTAL_SUCCESSFUL;
    }

    public Integer getTOTAL_INV() {
        return TOTAL_INV;
    }

    public Integer getTOTAL_ERR() {
        return TOTAL_ERR;
    }

    public Integer getTOTAL_RESULTS() {
        return TOTAL_RESULTS;
    }

    public Integer getTOTAL_POS() {
        return TOTAL_POS;
    }

    public Integer getTOTAL_NEG() {
        return TOTAL_NEG;
    }

    public Integer getTOTAL_NEW_CASES() {
        return TOTAL_NEW_CASES;
    }

    public Integer getTOTAL_FOLLOW_UPS() {
        return TOTAL_FOLLOW_UPS;
    }
}

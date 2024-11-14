package sample.Dashboard.Analytics.RegisterCount;

import sample.Alert.DialogBox;

import java.sql.*;

public class FetchRegisterCount {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_CUR_SUNDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 1 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_MONDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 2 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_TUESDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 3 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_WEDNESDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 4 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_THURSDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 5 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_FRIDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 6 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    private static final String FETCH_CUR_SATURDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 7 AND request_date >= CURDATE() - INTERVAL(DAYOFWEEK(CURDATE()) -2) DAY AND request_date < CURDATE() + INTERVAL(8-DAYOFWEEK(CURDATE())) DAY";
    //HANDLES PREVIOUS WEEKS
    private static final String FETCH_PREV_SUNDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 1 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_MONDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 2 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_TUESDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 3 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_WEDNESDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 4 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_THUR = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 5 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_FRIDAY = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 6 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";
    private static final String FETCH_PREV_SAT = "SELECT COUNT(*) AS total FROM request_list WHERE DAYOFWEEK(request_date) = 7 AND request_date >= CURDATE() - INTERVAL 1 WEEK AND request_date < CURDATE()";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer CUR_SUNDAY, CUR_MONDAY, CUR_TUESDAY, CUR_WEDNESDAY, CUR_THURSDAY, CUR_FRIDAY, CUR_SATURDAY;
    Integer PRE_SUNDAY, PRE_MONDAY, PRE_TUESDAY, PRE_WEDNESDAY, PRE_THURSDAY, PRE_FRIDAY, PRE_SATURDAY;
    public void fetchTotalCurSunday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_SUNDAY);
            while (resultSet.next()){
                CUR_SUNDAY = resultSet.getInt("total");
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
    public void fetchTotalPrevSunday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_SUNDAY);
            while (resultSet.next()){
                PRE_SUNDAY = resultSet.getInt("total");
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
    public void fetchTotalCurMonday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_MONDAY);
            while (resultSet.next()){
                CUR_MONDAY = resultSet.getInt("total");
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
    public void fetchTotalPreMonday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_MONDAY);
            while (resultSet.next()){
                PRE_MONDAY = resultSet.getInt("total");
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
    public void fetchTotalCurTuesday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_TUESDAY);
            while (resultSet.next()){
                CUR_TUESDAY = resultSet.getInt("total");
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
    public void fetchTotalPreTuesday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_TUESDAY);
            while (resultSet.next()){
                PRE_TUESDAY = resultSet.getInt("total");
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
    public void fetchTotalCurWednesday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_WEDNESDAY);
            while (resultSet.next()){
                CUR_WEDNESDAY = resultSet.getInt("total");
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
    public void fetchTotalPreWednesday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_WEDNESDAY);
            while (resultSet.next()){
                PRE_WEDNESDAY = resultSet.getInt("total");
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
    public void fetchTotalCurThursday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_THURSDAY);
            while (resultSet.next()){
                CUR_THURSDAY = resultSet.getInt("total");
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
    public void fetchTotalPreThursday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_THUR);
            while (resultSet.next()){
                PRE_THURSDAY = resultSet.getInt("total");
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
    public void fetchTotalCurFriday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_FRIDAY);
            while (resultSet.next()){
                CUR_FRIDAY = resultSet.getInt("total");
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
    public void fetchTotalPreFriday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_FRIDAY);
            while (resultSet.next()){
                PRE_FRIDAY = resultSet.getInt("total");
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
    public void fetchTotalCurSaturday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_CUR_SATURDAY);
            while (resultSet.next()){
                CUR_SATURDAY = resultSet.getInt("total");
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
    public void fetchTotalPreSaturday(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_PREV_SAT);
            while (resultSet.next()){
                PRE_SATURDAY = resultSet.getInt("total");
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

    public Integer getPRE_SUNDAY() {
        return PRE_SUNDAY;
    }

    public Integer getPRE_MONDAY() {
        return PRE_MONDAY;
    }

    public Integer getPRE_TUESDAY() {
        return PRE_TUESDAY;
    }

    public Integer getPRE_WEDNESDAY() {
        return PRE_WEDNESDAY;
    }

    public Integer getPRE_THURSDAY() {
        return PRE_THURSDAY;
    }

    public Integer getPRE_FRIDAY() {
        return PRE_FRIDAY;
    }

    public Integer getPRE_SATURDAY() {
        return PRE_SATURDAY;
    }

    public Integer getCUR_SUNDAY() {
        return CUR_SUNDAY;
    }

    public Integer getCUR_MONDAY() {
        return CUR_MONDAY;
    }

    public Integer getCUR_TUESDAY() {
        return CUR_TUESDAY;
    }

    public Integer getCUR_WEDNESDAY() {
        return CUR_WEDNESDAY;
    }

    public Integer getCUR_THURSDAY() {
        return CUR_THURSDAY;
    }

    public Integer getCUR_FRIDAY() {
        return CUR_FRIDAY;
    }

    public Integer getCUR_SATURDAY() {
        return CUR_SATURDAY;
    }
}

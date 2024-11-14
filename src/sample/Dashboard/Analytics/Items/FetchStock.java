package sample.Dashboard.Analytics.Items;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class FetchStock {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String QUERY = "SELECT qty_available FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE()) ORDER BY Id ASC LIMIT 1";
    private static final String QUERY_RECEIVED = "SELECT adjustment_value FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND reason = 'Shortage' AND MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE())";
    private static final String QUERY_EXPIRED = "SELECT adjustment_value FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND reason = 'Expired' AND MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE())";
    private static final String QUERY_BORROWED = "SELECT adjustment_value FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND reason = 'Borrowing' AND MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE())";


    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    String STOCK_CARTRIDGE;
    public void retrieveXpertCartridgesStock(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()){
                STOCK_CARTRIDGE = resultSet.getString("qty_available");
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
    public void retrieveXpertCartridgesReceived(ObservableList<Integer> data){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(QUERY_RECEIVED);
            while (resultSet.next()){
                Integer received = resultSet.getInt("adjustment_value");
                data.add(received);
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
    public void retrieveXpertCartridgesExpired(ObservableList<Integer> data){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(QUERY_EXPIRED);
            while (resultSet.next()){
                Integer expired = resultSet.getInt("adjustment_value");
                data.add(expired);
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
    public void retrieveXpertCartridgesBorrowed(ObservableList<Integer> data){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(QUERY_BORROWED);
            while (resultSet.next()){
                Integer expired = resultSet.getInt("adjustment_value");
                data.add(expired);
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

    public String getSTOCK_CARTRIDGE() {
        return STOCK_CARTRIDGE;
    }
}

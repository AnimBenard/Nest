package sample.Dashboard.MonthlyStockNotification;

import sample.Alert.DialogBox;

import java.sql.*;

public class FetchCartridgesLeft {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String CHECK = "SELECT Id FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND MONTH(filled_date) = MONTH(CURDATE()) ORDER BY Id DESC LIMIT 1";
    private static final String QUERY = "SELECT " +
            "(sar_inner.quantity_left - COALESCE(SUM(rl.cartridge_used))) AS new_qty_available " +
            "FROM " +
            "(SELECT * FROM stock_adjustment_request WHERE MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE()) ORDER BY Id DESC LIMIT 1) AS sar_inner " +
            "LEFT JOIN " +
            "request_list AS rl ON MONTH(rl.examined_date) = MONTH(CURDATE()) AND YEAR(rl.examined_date) = YEAR(CURDATE())";


    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    Integer QTY_AVAILABLE, QTY_LEFT;

    public void retrieveCartridgeCount(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_SENSITIVE);
            resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()){
                QTY_AVAILABLE = resultSet.getInt("new_qty_available");
                QTY_LEFT = resultSet.getInt("new_qty_available");
                System.out.println(QTY_AVAILABLE +"," + QTY_LEFT);
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
    public boolean checkIfStockAvailableThisMonth(){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(CHECK);
            while (resultSet.next()){
                return true;
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

        return false;
    }

    public Integer getQTY_AVAILABLE() {
        return QTY_AVAILABLE;
    }

    public void setQTY_AVAILABLE(Integer QTY_AVAILABLE) {
        this.QTY_AVAILABLE = QTY_AVAILABLE;
    }

    public Integer getQTY_LEFT() {
        return QTY_LEFT;
    }

    public void setQTY_LEFT(Integer QTY_LEFT) {
        this.QTY_LEFT = QTY_LEFT;
    }
}

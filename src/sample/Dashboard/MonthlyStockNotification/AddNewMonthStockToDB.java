package sample.Dashboard.MonthlyStockNotification;

import sample.Alert.DialogBox;

import java.sql.*;

public class AddNewMonthStockToDB {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String QUERY = "INSERT INTO stock_adjustment_request (item_description, batch_number, qty_available, adjustment_value, adjustment_option, reason, requisition_id, approval_status, quantity_approved, approval_personnel, approval_time, approval_date, filled_time, filled_date, personnel, quantity_left, production_date, expiry_date) " +
            "SELECT item_description, batch_number, qty_available, adjustment_value, adjustment_option, reason, requisition_id, approval_status, quantity_approved, approval_personnel, approval_time, approval_date, CURTIME(), CURDATE(), personnel, quantity_left, production_date, expiry_date FROM stock_adjustment_request " +
            "WHERE item_description = 'GeneXpert Cartridge' ORDER BY Id DESC LIMIT 1";
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void addNewMonthStock(){
        //this mwthod will insert the cartridges left into db at the start of the new month
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(QUERY);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
}

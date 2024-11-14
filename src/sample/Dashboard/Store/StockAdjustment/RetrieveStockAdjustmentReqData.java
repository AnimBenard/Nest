package sample.Dashboard.Store.StockAdjustment;

import javafx.collections.ObservableList;

import java.sql.*;

public class RetrieveStockAdjustmentReqData {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String STOCK_ADJ_DATA = "SELECT * FROM stock_adjustment_request ORDER BY Id DESC";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void retrieveStockAdjData(ObservableList<StockAdjustmentDataClass> data){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Class.forName(CLASSPATH);
                   connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                   statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                   resultSet = statement.executeQuery(STOCK_ADJ_DATA);
                   while (resultSet.next()){
                       Integer id = resultSet.getInt("Id");
                       String item = resultSet.getString("item_description");
                       String batchNo = resultSet.getString("batch_number");
                       String qtyAvailable = resultSet.getString("qty_available");
                       String adjValue = resultSet.getString("adjustment_value");
                       String option = resultSet.getString("adjustment_option");
                       String reason = resultSet.getString("reason");
                       String time = resultSet.getString("filled_time");
                       String date = resultSet.getString("filled_date");
                       String personnel = resultSet.getString("personnel");
                       String reqID = resultSet.getString("requisition_id");
                       String appStat = resultSet.getString("approval_status");
                       String qtyLeft = resultSet.getString("quantity_left");
                       String proDate = resultSet.getString("production_date");
                       String expDate = resultSet.getString("expiry_date");
                       data.add(new StockAdjustmentDataClass(id, item, batchNo, qtyAvailable, adjValue, option, reason, time, date, personnel, reqID, appStat, qtyLeft, proDate, expDate));
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
       }).start();
    }
}

package sample.Dashboard.Store.StockAdjustment;

import java.sql.*;

public class FetchBatchNoQtyAvailable {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String CONS_ITEM = "SELECT qty_left, batch_number, production_date, expiry_date FROM consumable_item_stock WHERE item_description = ?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String TOTAL_AVAILABLE, BATCH_NO, PRO_DATE, EXP_DATE;
    public void fetchBatchQtyAvailable(String item){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(CONS_ITEM, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, item);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TOTAL_AVAILABLE = resultSet.getString("qty_left");
                BATCH_NO = resultSet.getString("batch_number");
                PRO_DATE = resultSet.getString("production_date");
                EXP_DATE = resultSet.getString("expiry_date");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }  if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
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

    public String getPRO_DATE() {
        return PRO_DATE;
    }

    public String getEXP_DATE() {
        return EXP_DATE;
    }

    public String getTOTAL_AVAILABLE() {
        return TOTAL_AVAILABLE;
    }

    public String getBATCH_NO() {
        return BATCH_NO;
    }
}

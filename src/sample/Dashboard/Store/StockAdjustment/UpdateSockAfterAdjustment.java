package sample.Dashboard.Store.StockAdjustment;

import java.sql.*;

public class UpdateSockAfterAdjustment {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String UPDATE_STOCK = "UPDATE consumable_item_stock SET qty_left = ? WHERE item_description = ?";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public void updateStock(String qty, String itemDesc){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASSPATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    preparedStatement = connection.prepareStatement(UPDATE_STOCK, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setString(1, qty);
                    preparedStatement.setString(2, itemDesc);
                    preparedStatement.executeUpdate();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
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
            }
        }).start();
    }
}

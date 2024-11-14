package sample.Dashboard.Store.AddStock;

import javafx.collections.ObservableList;

import java.sql.*;

public class RetrieveConsumableStock {
    private static final String DB_USERNAME = "root";
    private static final String DBP_ASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String CONS_STOCK = "SELECT * FROM consumable_item_stock ORDER BY Id DESC";
    private static final String STOCKED_ITEMS = "SELECT item_description FROM consumable_item_stock";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public void consumableStockData(ObservableList<ConsumableStockDataClass> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASSPATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DBP_ASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(CONS_STOCK);
                    while (resultSet.next()){
                        Integer id = resultSet.getInt("Id");
                        String supName = resultSet.getString("supplier_name");
                        String supTel = resultSet.getString("supplier_tel");
                        String itemDesc = resultSet.getString("item_description");
                        String batchNo = resultSet.getString("batch_number");
                        String packsReceived = resultSet.getString("packs_received");
                        String totalQtyInPack = resultSet.getString("total_qty_in_a_pack");
                        String unitPackPrice = resultSet.getString("unit_pack_price");
                        String unitItemPrice = resultSet.getString("unit_item_price");
                        String minStock = resultSet.getString("minimum_stock");
                        String totalUnitQty = resultSet.getString("total_unit_qty");
                        String totalPacksAmount = resultSet.getString("total_packs_amount");
                        String total_packs_amount = resultSet.getString("total_packs_amount");
                        String prodDate = resultSet.getString("production_date");
                        String expDate = resultSet.getString("expiry_date");
                        String time = resultSet.getString("filled_time");
                        String date = resultSet.getString("filled_date");
                        String user = resultSet.getString("personnel");
                        String reqId = resultSet.getString("request_id");
                        String qtyLeft = resultSet.getString("qty_left");
                        data.add(new ConsumableStockDataClass(id, supName, supTel, itemDesc, batchNo, packsReceived, totalQtyInPack, unitPackPrice, unitItemPrice, minStock, totalUnitQty, totalPacksAmount, prodDate, expDate, time, date, user, reqId, qtyLeft));
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
    public void fetchConsItems(ObservableList<String> consData){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASSPATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DBP_ASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(STOCKED_ITEMS);
                    while (resultSet.next()){
                        String item = resultSet.getString("item_description");
                        consData.add(item);
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

package sample.Dashboard.Store.AddStock;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class AddConsumableStockToDB {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String QUERY = "INSERT INTO consumable_item_stock (supplier_name, supplier_tel, item_description, batch_number, packs_received, total_qty_in_a_pack, unit_pack_price, unit_item_price, minimum_stock, total_unit_qty, total_packs_amount, " +
            "production_date, expiry_date, filled_time, filled_date, personnel, request_id, qty_left) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public  void addConsumableStockToDB(String supplierName, String supplierTel, String item, String batchNo, String packsReceived, String qtyInAPack, String unitPackPrice, String unitItem,
                                        String minStock, String totalUnitQty, String totalPacksAmount, String prodDate, String expiryDate, String time, Date date, String user, String reqId){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE);
            preparedStatement.setString(1, supplierName);
            preparedStatement.setString(2, supplierTel);
            preparedStatement.setString(3, item);
            preparedStatement.setString(4, batchNo);
            preparedStatement.setString(5, packsReceived);
            preparedStatement.setString(6, qtyInAPack);
            preparedStatement.setString(7, unitPackPrice);
            preparedStatement.setString(8, unitItem);
            preparedStatement.setString(9, minStock);
            preparedStatement.setString(10, totalUnitQty);
            preparedStatement.setString(11, totalPacksAmount);
            preparedStatement.setString(12, prodDate);
            preparedStatement.setString(13, expiryDate);
            preparedStatement.setString(14, time);
            preparedStatement.setDate(15, date);
            preparedStatement.setString(16, user);
            preparedStatement.setString(17, reqId);
            preparedStatement.setString(18, totalUnitQty);
            preparedStatement.executeUpdate();
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Consumable successfully added";
            String title = "Success";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, title, icon);

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
}

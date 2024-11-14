package sample.Dashboard.MonthlyStockNotification;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class UpdateNewMonthStockWithQtyAvailable {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String QUERY = "UPDATE stock_adjustment_request AS sar " +
            "JOIN (" +
            " SELECT " +
            "(sar_inner.quantity_left - COALESCE(SUM(rl.cartridge_used))) AS new_qty_available, " +
            "sar_inner.Id AS sar_id " +
            "FROM " +
            "(SELECT * FROM stock_adjustment_request WHERE MONTH(filled_date) = MONTH(CURDATE()) AND YEAR(filled_date) = YEAR(CURDATE()) ORDER BY Id DESC LIMIT 1) AS sar_inner " +
            "LEFT JOIN " +
            "request_list AS rl ON MONTH(rl.examined_date) = MONTH(CURDATE()) AND YEAR(rl.examined_date) = YEAR(CURDATE())" +
            ") AS subquery " +
            "ON sar.Id = subquery.sar_id " +
            "SET " +
            "sar.qty_available = ?, " +
            "sar.quantity_left = ? " +
            "WHERE " +
            "MONTH(sar.filled_date) = MONTH(CURDATE()) AND YEAR(sar.filled_date) = YEAR(CURDATE()) AND sar.Id = subquery.sar_id";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void updateStockAvailable_Left(String qtyAva, String qtyLeft){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, qtyAva);
            preparedStatement.setString(2, qtyLeft);
            int inserted = preparedStatement.executeUpdate();
            if(inserted > 0){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Stock successfully updated";
                String dtitle = "Success!";
                ButtonType btnType = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
                dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
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

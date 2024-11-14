package sample.Dashboard.Store.StockAdjustment;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class AddStockAdjReqToDB {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_REQ = "INSERT INTO stock_adjustment_request(item_description, batch_number, qty_available, adjustment_value," +
            " adjustment_option, reason, filled_time, filled_date, personnel, requisition_id, approval_status, quantity_left, production_date, expiry_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();

    public void addStockAdjReq(String item, String batchNo, String qtyAvailable, String adjValue, String adjOpt, String reason, String time, Date date,
                               String user, String reqId, String appStatus, String qtyLeft, String proDate, String expDate){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_REQ, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, batchNo);
            preparedStatement.setString(3, qtyAvailable);
            preparedStatement.setString(4, adjValue);
            preparedStatement.setString(5, adjOpt);
            preparedStatement.setString(6, reason);
            preparedStatement.setString(7, time);
            preparedStatement.setDate(8, date);
            preparedStatement.setString(9, user);
            preparedStatement.setString(10, reqId);
            preparedStatement.setString(11, appStatus);
            preparedStatement.setString(12, qtyLeft);
            preparedStatement.setString(13, proDate);
            preparedStatement.setString(14, expDate);
            preparedStatement.executeUpdate();
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Adjustment added successfully";
            String dtitle = "Success";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dBox.showDialogBox(btnType, contentText, dtitle, icon);
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
    public void addStockAdjReqNoDialog(String item, String batchNo, String qtyAvailable, String adjValue, String adjOpt, String reason, String time, Date date,
                               String user, String reqId, String appStatus, String qtyLeft, String proDate, String expDate){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_REQ, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, batchNo);
            preparedStatement.setString(3, qtyAvailable);
            preparedStatement.setString(4, adjValue);
            preparedStatement.setString(5, adjOpt);
            preparedStatement.setString(6, reason);
            preparedStatement.setString(7, time);
            preparedStatement.setDate(8, date);
            preparedStatement.setString(9, user);
            preparedStatement.setString(10, reqId);
            preparedStatement.setString(11, appStatus);
            preparedStatement.setString(12, qtyLeft);
            preparedStatement.setString(13, proDate);
            preparedStatement.setString(14, expDate);
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
}

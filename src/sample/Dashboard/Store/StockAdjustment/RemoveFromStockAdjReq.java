package sample.Dashboard.Store.StockAdjustment;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class RemoveFromStockAdjReq {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String DELETE_REQUEST = "DELETE FROM stock_adjustment_request WHERE Id = ?";
    private static final String CHECK_ID = "SELECT first_name FROM dispensary_assessment_form WHERE Id = ?";

    DialogBox dialogBox = new DialogBox();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public void removeData(Integer id){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(DELETE_REQUEST, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Data removed successfully";
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
    public boolean checkIfIdExist(Integer id){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(CHECK_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return true;
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
        return false;
    }
}

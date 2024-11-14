package sample.Dashboard.Hub.ExternalRequest.ExtReqTable;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class AcceptRequest {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ACCEPT_QUERY = "UPDATE external_request_list SET acceptance_status = ?, reason_for_status = ? WHERE Id = ?";
    private static final String ACCEPT_STATUS = "SELECT Id, reason_for_status FROM external_request_list WHERE request_id = ? AND acceptance_status IS NOT NULL";


    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();

    String COMMENT;

    public void acceptExternalReq(String status, String reason, String id){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ACCEPT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, reason);
            preparedStatement.setString(3, id);
            int accepted = preparedStatement.executeUpdate();
            if(accepted > 0){
                Image icon = new Image("/sample/images/etblogo.png");
                String contentText = "Request successfully saved";
                String dtitle = "Success!";
                ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dBox.showDialogBox(btnType, contentText, dtitle, icon);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
    public boolean checkIfRequestAccepted(String reqId){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ACCEPT_STATUS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, reqId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                COMMENT = resultSet.getString("reason_for_status");
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
        return false;
    }

    public String getCOMMENT() {
        return COMMENT;
    }
}

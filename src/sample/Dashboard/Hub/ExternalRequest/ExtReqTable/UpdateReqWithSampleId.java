package sample.Dashboard.Hub.ExternalRequest.ExtReqTable;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;

import java.sql.*;

public class UpdateReqWithSampleId {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ACCEPT_QUERY = "UPDATE request_list SET serial_id = ?, sample_id = ? WHERE request_id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();

    public void acceptExternalReq(String serialId, String sampleId, String reqId){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ACCEPT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, serialId);
            preparedStatement.setString(2, sampleId);
            preparedStatement.setString(3, reqId);
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
}

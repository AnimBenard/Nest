package sample.Dashboard.Hub.ExternalRequest.ExtReqTable;

import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveRequestID {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String RETRIEVE_QUERY = "SELECT request_id FROM external_request_list WHERE Id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();

    String REQUEST_ID;
    public void acceptExternalReq(String id){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(RETRIEVE_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                REQUEST_ID = resultSet.getString("request_id");
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
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }
}

package sample.Dashboard.Hub.ExternalRequest.ExtReqTable.PreviewExtResults;

import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveExtResults {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ACCEPT_QUERY = "SELECT results, examined_by, examined_date FROM request_list WHERE request_id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dBox = new DialogBox();
    
    String RESULTS, RES_USER, DATE;
    public void retrieveExtRes(String reqID){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ACCEPT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, reqID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                RESULTS = resultSet.getString("results");
                RES_USER = resultSet.getString("examined_by");
                DATE = resultSet.getString("examined_date");
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

    public String getRESULTS() {
        return RESULTS;
    }

    public String getRES_USER() {
        return RES_USER;
    }

    public String getDATE() {
        return DATE;
    }
}

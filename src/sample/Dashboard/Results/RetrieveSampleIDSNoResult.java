package sample.Dashboard.Results;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveSampleIDSNoResult {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String RETRIEVE_SAMPLE_IDS = "SELECT sample_id FROM request_list WHERE results IS NULL ORDER BY Id DESC";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveSampleID(ObservableList<String> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(RETRIEVE_SAMPLE_IDS);
                    while (resultSet.next()){
                        String sampleId = resultSet.getString("sample_id");
                        data.add(sampleId);
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
                    if(statement != null){
                        try {
                            statement.close();
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
        }).start();
    }
}

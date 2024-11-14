package sample.Dashboard.Hub;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class FetchRefFacility {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_FAC = "SELECT facility_name FROM referring_facility";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveRefFac(ObservableList<String> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(FETCH_FAC);
                    while (resultSet.next()){
                        String fac = resultSet.getString("facility_name");
                        data.add(fac);
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

package sample.Dashboard.Analytics.FacilitySampleCount;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveFacSampleCount {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_TOTAL_COUNT = "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(referring_facility) DESC) AS pos, referring_facility, COUNT(referring_facility) AS total FROM request_list WHERE MONTH(request_date) = MONTH(CURDATE()) AND YEAR(request_date) = YEAR(CURDATE()) group by referring_facility ORDER BY total DESC";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveSampleCount(ObservableList<FacCountDataClass> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(FETCH_TOTAL_COUNT);
                    while (resultSet.next()){
                        String pos = resultSet.getString("pos");
                        String fac = resultSet.getString("referring_facility");
                        String count = resultSet.getString("total");
                        data.add(new FacCountDataClass(pos, fac, count));
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
                    if(statement != null){
                        try {
                            statement.close();
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
        }).start();
    }
}

package sample.Dashboard.Hub.ExternalRequest;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class FetchFacilityAndCode {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_REF_FAC = "SELECT facility_name FROM referring_facility";
    private static final String FETCH_CODE = "SELECT facility_code FROM referring_facility WHERE facility_name = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    String FAC_CODE;

    public void fetchRefFac(ObservableList<String> data){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(FETCH_REF_FAC);
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
    public void fetchCode(String facName){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(FETCH_CODE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, facName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                FAC_CODE = resultSet.getString("facility_code");
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

    public String getFAC_CODE() {
        return FAC_CODE;
    }
}

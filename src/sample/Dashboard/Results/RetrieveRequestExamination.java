package sample.Dashboard.Results;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveRequestExamination {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_LIST = "SELECT Id, surname, othernames, request_date, sample_id, reason_for_examination, request_type FROM request_list WHERE sample_id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveExamination(ObservableList<RequestExaminationDataClass> data, String sampleID){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Class.forName(CLASS_PATH);
                   connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                   preparedStatement = connection.prepareStatement(FETCH_LIST, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                   preparedStatement.setString(1, sampleID);
                   resultSet = preparedStatement.executeQuery();
                   while (resultSet.next()){
                       Integer id = resultSet.getInt("Id");
                       String samId = resultSet.getString("sample_id");
                       String reason = resultSet.getString("reason_for_examination");
                       String type = resultSet.getString("request_type");
                       String surname = resultSet.getString("surname");
                       String othernames = resultSet.getString("othernames");
                       String fullName = surname + " " + othernames;
                       String reqDate = resultSet.getString("request_date");
                       data.add(new RequestExaminationDataClass(id, samId, reason, type, fullName, reqDate));
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
       }).start();
    }
}

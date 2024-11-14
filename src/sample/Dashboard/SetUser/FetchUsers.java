package sample.Dashboard.SetUser;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

public class FetchUsers {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String USERS = "SELECT username FROM user_account";
    private static final String FULL_NAME = "SELECT CONCAT(surname, ' ', othernames) AS full_name FROM user_account WHERE username = ?";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    String NAME;
    public void fetchUsers(ObservableList<String> data){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASSPATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(USERS);
                    while (resultSet.next()){
                        String users = resultSet.getString("username");
                        data.add(users);
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
            }
        });
        thread.start();
    }
    @FXML
    public void fetchFullNameByUsername(String username){
        try {
            Class.forName(CLASSPATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(FULL_NAME, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                NAME = resultSet.getString("full_name");
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
    }

    public String getNAME() {
        return NAME;
    }
}

package sample.Dashboard.Users;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveValidUsers {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String USER_INFO = "SELECT * FROM user_account WHERE verification = 'VERIFIED'";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveValidatedUsers(ObservableList<UserAccountDataClass> data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(USER_INFO);
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("Id");
                        String surname = resultSet.getString("surname");
                        String othername = resultSet.getString("othernames");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("user_password");
                        String grade = resultSet.getString("grade");
                        String userNo = resultSet.getString("user_no");
                        String verification = resultSet.getString("verification");
                        String accDate = resultSet.getString("account_date");
                        String accTime = resultSet.getString("account_time");
                        String verDate = resultSet.getString("verification_date");
                        String verTime = resultSet.getString("verification_time");
                        String personnel = resultSet.getString("personnel");
                        data.add(new UserAccountDataClass(id, surname, othername, username, password, grade, userNo, verification, accDate, accTime, verDate, verTime, personnel));
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
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

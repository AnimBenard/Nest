package sample.Dashboard.Hub.ExternalRequest;

import javafx.collections.ObservableList;
import sample.Alert.DialogBox;

import java.sql.*;

public class RetrieveExtRequestListData {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String FETCH_LIST = "SELECT * FROM external_request_list WHERE request_type = 'HUB' ORDER BY Id DESC LIMIT 30";
    private static final String FETCH_AWAITING_LIST = "SELECT * FROM external_request_list WHERE destination_facility = 'METHODIST HOSPITAL WENCHI' AND acceptance_status IS NULL";
    private static final String FETCH_RECEIVED_LIST = "SELECT * FROM external_request_list WHERE destination_facility = 'METHODIST HOSPITAL WENCHI' AND acceptance_status IS NOT NULL";


    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    public void retrieveRequestList(ObservableList<ExtRequestListDataClass> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(FETCH_LIST);
                    while (resultSet.next()){
                        Integer id = resultSet.getInt("Id");
                        String serial = resultSet.getString("serial_id");
                        String samId = resultSet.getString("sample_id");
                        String surname = resultSet.getString("surname");
                        String othername = resultSet.getString("othernames");
                        String fullName = othername + " " + surname;
                        String age = resultSet.getString("age");
                        String sex = resultSet.getString("sex");
                        String address = resultSet.getString("address");
                        String disTBNo = resultSet.getString("district_tb_number");
                        String refFac = resultSet.getString("referring_facility");
                        String specType = resultSet.getString("type_of_specimen");
                        String samApp = resultSet.getString("sample_appearance");
                        String reason = resultSet.getString("reason_for_examination");
                        String reqTime = resultSet.getString("request_time");
                        String reqDate = resultSet.getString("request_date");
                        String reqUser = resultSet.getString("request_user");
                        String reqType = resultSet.getString("request_type");
                        String reqId = resultSet.getString("request_id");
                        String results = resultSet.getString("results");
                        String examinedBy = resultSet.getString("examined_by");
                        String examinedTime = resultSet.getString("examined_time");
                        String examDate = resultSet.getString("examined_date");
                        String remarks = resultSet.getString("remarks");
                        String validation = resultSet.getString("validation");
                        String valTime = resultSet.getString("validation_time");
                        String valDate = resultSet.getString("validation_date");
                        String valBy = resultSet.getString("validated_by");
                        String delReq = resultSet.getString("validated_by");
                        String followUpMonths = resultSet.getString("follow_up_months");
                        String ageUnits = resultSet.getString("age_units");
                        String fullAge = age + " " + ageUnits;
                        String acceptStatus = resultSet.getString("acceptance_status");
                        String reasonStatus = resultSet.getString("reason_for_status");
                        String destFac = resultSet.getString("destination_facility");
                        String destFacCode = resultSet.getString("destination_facility_code");
                        String reqFac = resultSet.getString("request_facility");
                        String reqFacCode = resultSet.getString("request_facility_code");
                        data.add(new ExtRequestListDataClass(id, serial, samId, surname, othername, age, sex, address, disTBNo, refFac, specType, samApp, reason, reqTime, reqDate,
                                reqUser, reqType, reqId, results, examinedBy, examinedTime, examDate, remarks, validation, valTime, valDate, valBy, delReq, followUpMonths, fullName,
                                ageUnits, fullAge, acceptStatus, reasonStatus, destFac, destFacCode, reqFac, reqFacCode));
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
    public void retrieveAwaitingList(ObservableList<ExtRequestListDataClass> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(FETCH_AWAITING_LIST);
                    while (resultSet.next()){
                        Integer id = resultSet.getInt("Id");
                        String serial = resultSet.getString("serial_id");
                        String samId = resultSet.getString("sample_id");
                        String surname = resultSet.getString("surname");
                        String othername = resultSet.getString("othernames");
                        String fullName = othername + " " + surname;
                        String age = resultSet.getString("age");
                        String sex = resultSet.getString("sex");
                        String address = resultSet.getString("address");
                        String disTBNo = resultSet.getString("district_tb_number");
                        String refFac = resultSet.getString("referring_facility");
                        String specType = resultSet.getString("type_of_specimen");
                        String samApp = resultSet.getString("sample_appearance");
                        String reason = resultSet.getString("reason_for_examination");
                        String reqTime = resultSet.getString("request_time");
                        String reqDate = resultSet.getString("request_date");
                        String reqUser = resultSet.getString("request_user");
                        String reqType = resultSet.getString("request_type");
                        String reqId = resultSet.getString("request_id");
                        String results = resultSet.getString("results");
                        String examinedBy = resultSet.getString("examined_by");
                        String examinedTime = resultSet.getString("examined_time");
                        String examDate = resultSet.getString("examined_date");
                        String remarks = resultSet.getString("remarks");
                        String validation = resultSet.getString("validation");
                        String valTime = resultSet.getString("validation_time");
                        String valDate = resultSet.getString("validation_date");
                        String valBy = resultSet.getString("validated_by");
                        String delReq = resultSet.getString("validated_by");
                        String followUpMonths = resultSet.getString("follow_up_months");
                        String ageUnits = resultSet.getString("age_units");
                        String fullAge = age + " " + ageUnits;
                        String acceptStatus = resultSet.getString("acceptance_status");
                        String reasonStatus = resultSet.getString("reason_for_status");
                        String destFac = resultSet.getString("destination_facility");
                        String destFacCode = resultSet.getString("destination_facility_code");
                        String reqFac = resultSet.getString("request_facility");
                        String reqFacCode = resultSet.getString("request_facility_code");
                        data.add(new ExtRequestListDataClass(id, serial, samId, surname, othername, age, sex, address, disTBNo, refFac, specType, samApp, reason, reqTime, reqDate,
                                reqUser, reqType, reqId, results, examinedBy, examinedTime, examDate, remarks, validation, valTime, valDate, valBy, delReq, followUpMonths, fullName,
                                ageUnits, fullAge, acceptStatus, reasonStatus, destFac, destFacCode, reqFac, reqFacCode));
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
    public void retrieveReceivedList(ObservableList<ExtRequestListDataClass> data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName(CLASS_PATH);
                    connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    resultSet = statement.executeQuery(FETCH_RECEIVED_LIST);
                    while (resultSet.next()){
                        Integer id = resultSet.getInt("Id");
                        String serial = resultSet.getString("serial_id");
                        String samId = resultSet.getString("sample_id");
                        String surname = resultSet.getString("surname");
                        String othername = resultSet.getString("othernames");
                        String fullName = othername + " " + surname;
                        String age = resultSet.getString("age");
                        String sex = resultSet.getString("sex");
                        String address = resultSet.getString("address");
                        String disTBNo = resultSet.getString("district_tb_number");
                        String refFac = resultSet.getString("referring_facility");
                        String specType = resultSet.getString("type_of_specimen");
                        String samApp = resultSet.getString("sample_appearance");
                        String reason = resultSet.getString("reason_for_examination");
                        String reqTime = resultSet.getString("request_time");
                        String reqDate = resultSet.getString("request_date");
                        String reqUser = resultSet.getString("request_user");
                        String reqType = resultSet.getString("request_type");
                        String reqId = resultSet.getString("request_id");
                        String results = resultSet.getString("results");
                        String examinedBy = resultSet.getString("examined_by");
                        String examinedTime = resultSet.getString("examined_time");
                        String examDate = resultSet.getString("examined_date");
                        String remarks = resultSet.getString("remarks");
                        String validation = resultSet.getString("validation");
                        String valTime = resultSet.getString("validation_time");
                        String valDate = resultSet.getString("validation_date");
                        String valBy = resultSet.getString("validated_by");
                        String delReq = resultSet.getString("validated_by");
                        String followUpMonths = resultSet.getString("follow_up_months");
                        String ageUnits = resultSet.getString("age_units");
                        String fullAge = age + " " + ageUnits;
                        String acceptStatus = resultSet.getString("acceptance_status");
                        String reasonStatus = resultSet.getString("reason_for_status");
                        String destFac = resultSet.getString("destination_facility");
                        String destFacCode = resultSet.getString("destination_facility_code");
                        String reqFac = resultSet.getString("request_facility");
                        String reqFacCode = resultSet.getString("request_facility_code");
                        data.add(new ExtRequestListDataClass(id, serial, samId, surname, othername, age, sex, address, disTBNo, refFac, specType, samApp, reason, reqTime, reqDate,
                                reqUser, reqType, reqId, results, examinedBy, examinedTime, examDate, remarks, validation, valTime, valDate, valBy, delReq, followUpMonths, fullName,
                                ageUnits, fullAge, acceptStatus, reasonStatus, destFac, destFacCode, reqFac, reqFacCode));
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

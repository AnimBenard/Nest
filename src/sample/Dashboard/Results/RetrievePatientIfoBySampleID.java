package sample.Dashboard.Results;

import sample.Alert.DialogBox;

import java.sql.*;

public class RetrievePatientIfoBySampleID {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String RETRIEVE_SAMPLE_IDS = "SELECT * FROM request_list WHERE sample_id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    String SAMPLE_ID, SURNAME, OTHERNAMES, AGE, SEX, ADDRESS, DIS_TB_NO, REF_FAC, TYPE_OF_SPEC, SAMPLE_APPEARANCE, REASON_FOR_EXAM, REQUEST_TIME, REQ_DATE, REQ_USER, REQUEST_TYPE, REQUEST_ID;

    public void retrievePatInfo(String sampleID){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(RETRIEVE_SAMPLE_IDS, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, sampleID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SAMPLE_ID = resultSet.getString("sample_id");
                SURNAME = resultSet.getString("surname");
                OTHERNAMES = resultSet.getString("othernames");
                AGE = resultSet.getString("age");
                SEX = resultSet.getString("sex");
                ADDRESS = resultSet.getString("address");
                DIS_TB_NO = resultSet.getString("district_tb_number");
                REF_FAC = resultSet.getString("referring_facility");
                TYPE_OF_SPEC = resultSet.getString("type_of_specimen");
                SAMPLE_APPEARANCE = resultSet.getString("sample_appearance");
                REASON_FOR_EXAM = resultSet.getString("reason_for_examination");
                REQUEST_TIME = resultSet.getString("request_time");
                REQ_DATE = resultSet.getString("request_date");
                REQ_USER = resultSet.getString("request_user");
                REQUEST_TYPE = resultSet.getString("request_time");
                REQ_DATE = resultSet.getString("request_date");
                REQ_USER = resultSet.getString("request_user");
                REQUEST_TYPE = resultSet.getString("request_type");
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

    public String getSAMPLE_ID() {
        return SAMPLE_ID;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public String getOTHERNAMES() {
        return OTHERNAMES;
    }

    public String getAGE() {
        return AGE;
    }

    public String getSEX() {
        return SEX;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getDIS_TB_NO() {
        return DIS_TB_NO;
    }

    public String getREF_FAC() {
        return REF_FAC;
    }

    public String getTYPE_OF_SPEC() {
        return TYPE_OF_SPEC;
    }

    public String getSAMPLE_APPEARANCE() {
        return SAMPLE_APPEARANCE;
    }

    public String getREASON_FOR_EXAM() {
        return REASON_FOR_EXAM;
    }

    public String getREQUEST_TIME() {
        return REQUEST_TIME;
    }

    public String getREQ_DATE() {
        return REQ_DATE;
    }

    public String getREQ_USER() {
        return REQ_USER;
    }

    public String getREQUEST_TYPE() {
        return REQUEST_TYPE;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }
}

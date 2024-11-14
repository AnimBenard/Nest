package sample.Dashboard.Users;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserAccountDataClass {
    private final IntegerProperty ID;
    private final StringProperty SURNAME;
    private final StringProperty OTHER_NAMES;
    private final StringProperty USERNAME;
    private final StringProperty PASSWORD;
    private final StringProperty GRADE;
    private final StringProperty USER_NO;
    private final StringProperty VERIFICATION;
    private final StringProperty FILLED_DATE;
    private final StringProperty FILLED_TIME;
    private final StringProperty VERIFICATION_DATE;
    private final StringProperty VERIFICATION_TIME;
    private final StringProperty USER;

    public UserAccountDataClass(Integer id, String surname, String othername, String userName, String password, String grade, String userNo, String verification,
                                String filledDate, String filledTime, String verificationDate, String verificationTime, String user){
        this.ID = new SimpleIntegerProperty(id);
        this.SURNAME = new SimpleStringProperty(surname);
        this.OTHER_NAMES = new SimpleStringProperty(othername);
        this.USERNAME = new SimpleStringProperty(userName);
        this.PASSWORD = new SimpleStringProperty(password);
        this.GRADE = new SimpleStringProperty(grade);
        this.USER_NO = new SimpleStringProperty(userNo);
        this.VERIFICATION = new SimpleStringProperty(verification);
        this.FILLED_DATE = new SimpleStringProperty(filledDate);
        this.FILLED_TIME = new SimpleStringProperty(filledTime);
        this.VERIFICATION_DATE = new SimpleStringProperty(verificationDate);
        this.VERIFICATION_TIME = new SimpleStringProperty(verificationTime);
        this.USER = new SimpleStringProperty(user);
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getSURNAME() {
        return SURNAME.get();
    }

    public StringProperty SURNAMEProperty() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME.set(SURNAME);
    }

    public String getOTHER_NAMES() {
        return OTHER_NAMES.get();
    }

    public StringProperty OTHER_NAMESProperty() {
        return OTHER_NAMES;
    }

    public void setOTHER_NAMES(String OTHER_NAMES) {
        this.OTHER_NAMES.set(OTHER_NAMES);
    }

    public String getUSERNAME() {
        return USERNAME.get();
    }

    public StringProperty USERNAMEProperty() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME.set(USERNAME);
    }

    public String getPASSWORD() {
        return PASSWORD.get();
    }

    public StringProperty PASSWORDProperty() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD.set(PASSWORD);
    }

    public String getGRADE() {
        return GRADE.get();
    }

    public StringProperty GRADEProperty() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE.set(GRADE);
    }

    public String getUSER_NO() {
        return USER_NO.get();
    }

    public StringProperty USER_NOProperty() {
        return USER_NO;
    }

    public void setUSER_NO(String USER_NO) {
        this.USER_NO.set(USER_NO);
    }

    public String getVERIFICATION() {
        return VERIFICATION.get();
    }

    public StringProperty VERIFICATIONProperty() {
        return VERIFICATION;
    }

    public void setVERIFICATION(String VERIFICATION) {
        this.VERIFICATION.set(VERIFICATION);
    }

    public String getFILLED_DATE() {
        return FILLED_DATE.get();
    }

    public StringProperty FILLED_DATEProperty() {
        return FILLED_DATE;
    }

    public void setFILLED_DATE(String FILLED_DATE) {
        this.FILLED_DATE.set(FILLED_DATE);
    }

    public String getFILLED_TIME() {
        return FILLED_TIME.get();
    }

    public StringProperty FILLED_TIMEProperty() {
        return FILLED_TIME;
    }

    public void setFILLED_TIME(String FILLED_TIME) {
        this.FILLED_TIME.set(FILLED_TIME);
    }

    public String getVERIFICATION_DATE() {
        return VERIFICATION_DATE.get();
    }

    public StringProperty VERIFICATION_DATEProperty() {
        return VERIFICATION_DATE;
    }

    public void setVERIFICATION_DATE(String VERIFICATION_DATE) {
        this.VERIFICATION_DATE.set(VERIFICATION_DATE);
    }

    public String getVERIFICATION_TIME() {
        return VERIFICATION_TIME.get();
    }

    public StringProperty VERIFICATION_TIMEProperty() {
        return VERIFICATION_TIME;
    }

    public void setVERIFICATION_TIME(String VERIFICATION_TIME) {
        this.VERIFICATION_TIME.set(VERIFICATION_TIME);
    }

    public String getUSER() {
        return USER.get();
    }

    public StringProperty USERProperty() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER.set(USER);
    }
}

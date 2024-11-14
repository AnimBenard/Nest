package sample.Dashboard.Results;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestExaminationDataClass {
    private final IntegerProperty ID;
    private final StringProperty SAMPLE_NO;
    private final StringProperty EXAMINATION;
    private final StringProperty TYPE;
    private final StringProperty PATIENT_NAME;
    private final StringProperty REQUEST_DATE;

    public RequestExaminationDataClass(Integer id, String sampleNo, String examination, String type, String patName, String reqDate){
        this.ID = new SimpleIntegerProperty(id);
        this.SAMPLE_NO = new SimpleStringProperty(sampleNo);
        this.PATIENT_NAME = new SimpleStringProperty(patName);
        this.REQUEST_DATE = new SimpleStringProperty(reqDate);
        this.EXAMINATION = new SimpleStringProperty(examination);
        this.TYPE = new SimpleStringProperty(type);
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

    public String getSAMPLE_NO() {
        return SAMPLE_NO.get();
    }

    public StringProperty SAMPLE_NOProperty() {
        return SAMPLE_NO;
    }

    public void setSAMPLE_NO(String SAMPLE_NO) {
        this.SAMPLE_NO.set(SAMPLE_NO);
    }

    public String getEXAMINATION() {
        return EXAMINATION.get();
    }

    public StringProperty EXAMINATIONProperty() {
        return EXAMINATION;
    }

    public void setEXAMINATION(String EXAMINATION) {
        this.EXAMINATION.set(EXAMINATION);
    }

    public String getTYPE() {
        return TYPE.get();
    }

    public StringProperty TYPEProperty() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE.set(TYPE);
    }

    public String getPATIENT_NAME() {
        return PATIENT_NAME.get();
    }

    public StringProperty PATIENT_NAMEProperty() {
        return PATIENT_NAME;
    }

    public void setPATIENT_NAME(String PATIENT_NAME) {
        this.PATIENT_NAME.set(PATIENT_NAME);
    }

    public String getREQUEST_DATE() {
        return REQUEST_DATE.get();
    }

    public StringProperty REQUEST_DATEProperty() {
        return REQUEST_DATE;
    }

    public void setREQUEST_DATE(String REQUEST_DATE) {
        this.REQUEST_DATE.set(REQUEST_DATE);
    }
}

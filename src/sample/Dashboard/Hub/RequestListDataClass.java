package sample.Dashboard.Hub;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestListDataClass {
    private final IntegerProperty ID;
    private final StringProperty SERIAL_ID;
    private final StringProperty SAMPLE_ID;
    private final StringProperty SURNAME;
    private final StringProperty OTHERNAME;
    private final StringProperty FULL_NAME;
    private final StringProperty AGE;
    private final StringProperty SEX;
    private final StringProperty ADDRESS;
    private final StringProperty DIST_TB_NUMBER;
    private final StringProperty REF_FACILITY;
    private final StringProperty SPECIMEN_TYPE;
    private final StringProperty SAMPLE_APPEARANCE;
    private final StringProperty REASON_FOR_EXAMINATION;
    private final StringProperty REQUEST_TIME;
    private final StringProperty REQUEST_DATE;
    private final StringProperty REQUEST_USER;
    private final StringProperty REQUEST_TYPE;
    private final StringProperty REQUEST_ID;
    private final StringProperty RESULTS;
    private final StringProperty EXAMINED_BY;
    private final StringProperty EXAMINED_TIME;
    private final StringProperty EXAMINED_DATE;
    private final StringProperty REMARKS;
    private final StringProperty VALIDATION;
    private final StringProperty VALIDATION_TIME;
    private final StringProperty VALIDATION_DATE;
    private final StringProperty VALIDATED_BY;
    private final StringProperty DELETED_REQUEST;
    private final StringProperty FOLLOW_UP_MONTHS;
    private final StringProperty AGE_UNITS;
    private final StringProperty FULL_AGE;

    public RequestListDataClass(Integer id, String serialId, String sampleId, String surname, String othername, String age, String sex, String address,
                                String distTBNo, String refFac, String specType, String samApp, String reason, String reqTime, String reqDate,
                                String reqUser, String reqType, String reqId, String results, String examinedBy, String examTime, String examDate,
                                String remarks, String validation, String valTime, String valDate, String valBy, String delRequest, String followUpMonths,
                                String fullName, String ageUnits, String fullAge){
        this.ID = new SimpleIntegerProperty(id);
        this.SERIAL_ID = new SimpleStringProperty(serialId);
        this.SAMPLE_ID = new SimpleStringProperty(sampleId);
        this.SURNAME = new SimpleStringProperty(surname);
        this.OTHERNAME = new SimpleStringProperty(othername);
        this.AGE = new SimpleStringProperty(age);
        this.SEX = new SimpleStringProperty(sex);
        this.ADDRESS = new SimpleStringProperty(address);
        this.DIST_TB_NUMBER = new SimpleStringProperty(distTBNo);
        this.REF_FACILITY = new SimpleStringProperty(refFac);
        this.SPECIMEN_TYPE = new SimpleStringProperty(specType);
        this.SAMPLE_APPEARANCE = new SimpleStringProperty(samApp);
        this.REASON_FOR_EXAMINATION = new SimpleStringProperty(reason);
        this.REQUEST_TIME = new SimpleStringProperty(reqTime);
        this.REQUEST_DATE = new SimpleStringProperty(reqDate);
        this.REQUEST_USER = new SimpleStringProperty(reqUser);
        this.REQUEST_TYPE = new SimpleStringProperty(reqType);
        this.REQUEST_ID = new SimpleStringProperty(reqId);
        this.RESULTS = new SimpleStringProperty(results);
        this.EXAMINED_BY = new SimpleStringProperty(examinedBy);
        this.EXAMINED_TIME = new SimpleStringProperty(examTime);
        this.EXAMINED_DATE = new SimpleStringProperty(examDate);
        this.REMARKS = new SimpleStringProperty(remarks);
        this.VALIDATION = new SimpleStringProperty(validation);
        this.VALIDATION_TIME = new SimpleStringProperty(valTime);
        this.VALIDATION_DATE = new SimpleStringProperty(valDate);
        this.VALIDATED_BY = new SimpleStringProperty(valBy);
        this.DELETED_REQUEST = new SimpleStringProperty(delRequest);
        this.FOLLOW_UP_MONTHS = new SimpleStringProperty(followUpMonths);
        this.FULL_NAME = new SimpleStringProperty(fullName);
        this.AGE_UNITS = new SimpleStringProperty(ageUnits);
        this.FULL_AGE = new SimpleStringProperty(fullAge);
    }

    public String getFULL_AGE() {
        return FULL_AGE.get();
    }

    public StringProperty FULL_AGEProperty() {
        return FULL_AGE;
    }

    public void setFULL_AGE(String FULL_AGE) {
        this.FULL_AGE.set(FULL_AGE);
    }

    public String getAGE_UNITS() {
        return AGE_UNITS.get();
    }

    public StringProperty AGE_UNITSProperty() {
        return AGE_UNITS;
    }

    public void setAGE_UNITS(String AGE_UNITS) {
        this.AGE_UNITS.set(AGE_UNITS);
    }

    public String getFULL_NAME() {
        return FULL_NAME.get();
    }

    public StringProperty FULL_NAMEProperty() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME.set(FULL_NAME);
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

    public String getSERIAL_ID() {
        return SERIAL_ID.get();
    }

    public StringProperty SERIAL_IDProperty() {
        return SERIAL_ID;
    }

    public void setSERIAL_ID(String SERIAL_ID) {
        this.SERIAL_ID.set(SERIAL_ID);
    }

    public String getSAMPLE_ID() {
        return SAMPLE_ID.get();
    }

    public StringProperty SAMPLE_IDProperty() {
        return SAMPLE_ID;
    }

    public void setSAMPLE_ID(String SAMPLE_ID) {
        this.SAMPLE_ID.set(SAMPLE_ID);
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

    public String getOTHERNAME() {
        return OTHERNAME.get();
    }

    public StringProperty OTHERNAMEProperty() {
        return OTHERNAME;
    }

    public void setOTHERNAME(String OTHERNAME) {
        this.OTHERNAME.set(OTHERNAME);
    }

    public String getAGE() {
        return AGE.get();
    }

    public StringProperty AGEProperty() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE.set(AGE);
    }

    public String getSEX() {
        return SEX.get();
    }

    public StringProperty SEXProperty() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX.set(SEX);
    }

    public String getADDRESS() {
        return ADDRESS.get();
    }

    public StringProperty ADDRESSProperty() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS.set(ADDRESS);
    }

    public String getDIST_TB_NUMBER() {
        return DIST_TB_NUMBER.get();
    }

    public StringProperty DIST_TB_NUMBERProperty() {
        return DIST_TB_NUMBER;
    }

    public void setDIST_TB_NUMBER(String DIST_TB_NUMBER) {
        this.DIST_TB_NUMBER.set(DIST_TB_NUMBER);
    }

    public String getREF_FACILITY() {
        return REF_FACILITY.get();
    }

    public StringProperty REF_FACILITYProperty() {
        return REF_FACILITY;
    }

    public void setREF_FACILITY(String REF_FACILITY) {
        this.REF_FACILITY.set(REF_FACILITY);
    }

    public String getSPECIMEN_TYPE() {
        return SPECIMEN_TYPE.get();
    }

    public StringProperty SPECIMEN_TYPEProperty() {
        return SPECIMEN_TYPE;
    }

    public void setSPECIMEN_TYPE(String SPECIMEN_TYPE) {
        this.SPECIMEN_TYPE.set(SPECIMEN_TYPE);
    }

    public String getSAMPLE_APPEARANCE() {
        return SAMPLE_APPEARANCE.get();
    }

    public StringProperty SAMPLE_APPEARANCEProperty() {
        return SAMPLE_APPEARANCE;
    }

    public void setSAMPLE_APPEARANCE(String SAMPLE_APPEARANCE) {
        this.SAMPLE_APPEARANCE.set(SAMPLE_APPEARANCE);
    }

    public String getREASON_FOR_EXAMINATION() {
        return REASON_FOR_EXAMINATION.get();
    }

    public StringProperty REASON_FOR_EXAMINATIONProperty() {
        return REASON_FOR_EXAMINATION;
    }

    public void setREASON_FOR_EXAMINATION(String REASON_FOR_EXAMINATION) {
        this.REASON_FOR_EXAMINATION.set(REASON_FOR_EXAMINATION);
    }

    public String getREQUEST_TIME() {
        return REQUEST_TIME.get();
    }

    public StringProperty REQUEST_TIMEProperty() {
        return REQUEST_TIME;
    }

    public void setREQUEST_TIME(String REQUEST_TIME) {
        this.REQUEST_TIME.set(REQUEST_TIME);
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

    public String getREQUEST_USER() {
        return REQUEST_USER.get();
    }

    public StringProperty REQUEST_USERProperty() {
        return REQUEST_USER;
    }

    public void setREQUEST_USER(String REQUEST_USER) {
        this.REQUEST_USER.set(REQUEST_USER);
    }

    public String getREQUEST_TYPE() {
        return REQUEST_TYPE.get();
    }

    public StringProperty REQUEST_TYPEProperty() {
        return REQUEST_TYPE;
    }

    public void setREQUEST_TYPE(String REQUEST_TYPE) {
        this.REQUEST_TYPE.set(REQUEST_TYPE);
    }

    public String getREQUEST_ID() {
        return REQUEST_ID.get();
    }

    public StringProperty REQUEST_IDProperty() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID.set(REQUEST_ID);
    }

    public String getRESULTS() {
        return RESULTS.get();
    }

    public StringProperty RESULTSProperty() {
        return RESULTS;
    }

    public void setRESULTS(String RESULTS) {
        this.RESULTS.set(RESULTS);
    }

    public String getEXAMINED_BY() {
        return EXAMINED_BY.get();
    }

    public StringProperty EXAMINED_BYProperty() {
        return EXAMINED_BY;
    }

    public void setEXAMINED_BY(String EXAMINED_BY) {
        this.EXAMINED_BY.set(EXAMINED_BY);
    }

    public String getEXAMINED_TIME() {
        return EXAMINED_TIME.get();
    }

    public StringProperty EXAMINED_TIMEProperty() {
        return EXAMINED_TIME;
    }

    public void setEXAMINED_TIME(String EXAMINED_TIME) {
        this.EXAMINED_TIME.set(EXAMINED_TIME);
    }

    public String getEXAMINED_DATE() {
        return EXAMINED_DATE.get();
    }

    public StringProperty EXAMINED_DATEProperty() {
        return EXAMINED_DATE;
    }

    public void setEXAMINED_DATE(String EXAMINED_DATE) {
        this.EXAMINED_DATE.set(EXAMINED_DATE);
    }

    public String getREMARKS() {
        return REMARKS.get();
    }

    public StringProperty REMARKSProperty() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS.set(REMARKS);
    }

    public String getVALIDATION() {
        return VALIDATION.get();
    }

    public StringProperty VALIDATIONProperty() {
        return VALIDATION;
    }

    public void setVALIDATION(String VALIDATION) {
        this.VALIDATION.set(VALIDATION);
    }

    public String getVALIDATION_TIME() {
        return VALIDATION_TIME.get();
    }

    public StringProperty VALIDATION_TIMEProperty() {
        return VALIDATION_TIME;
    }

    public void setVALIDATION_TIME(String VALIDATION_TIME) {
        this.VALIDATION_TIME.set(VALIDATION_TIME);
    }

    public String getVALIDATION_DATE() {
        return VALIDATION_DATE.get();
    }

    public StringProperty VALIDATION_DATEProperty() {
        return VALIDATION_DATE;
    }

    public void setVALIDATION_DATE(String VALIDATION_DATE) {
        this.VALIDATION_DATE.set(VALIDATION_DATE);
    }

    public String getVALIDATED_BY() {
        return VALIDATED_BY.get();
    }

    public StringProperty VALIDATED_BYProperty() {
        return VALIDATED_BY;
    }

    public void setVALIDATED_BY(String VALIDATED_BY) {
        this.VALIDATED_BY.set(VALIDATED_BY);
    }

    public String getDELETED_REQUEST() {
        return DELETED_REQUEST.get();
    }

    public StringProperty DELETED_REQUESTProperty() {
        return DELETED_REQUEST;
    }

    public void setDELETED_REQUEST(String DELETED_REQUEST) {
        this.DELETED_REQUEST.set(DELETED_REQUEST);
    }

    public String getFOLLOW_UP_MONTHS() {
        return FOLLOW_UP_MONTHS.get();
    }

    public StringProperty FOLLOW_UP_MONTHSProperty() {
        return FOLLOW_UP_MONTHS;
    }

    public void setFOLLOW_UP_MONTHS(String FOLLOW_UP_MONTHS) {
        this.FOLLOW_UP_MONTHS.set(FOLLOW_UP_MONTHS);
    }
}

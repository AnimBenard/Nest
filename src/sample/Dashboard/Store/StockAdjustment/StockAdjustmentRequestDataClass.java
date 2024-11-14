package sample.Dashboard.Store.StockAdjustment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockAdjustmentRequestDataClass {
    private final IntegerProperty ID;
    private final StringProperty ITEM;
    private final StringProperty BATCH_NO;
    private final StringProperty QTY_AVAILABLE;
    private final StringProperty ADJUSTMENT_VALUE;
    private final StringProperty OPTION;
    private final StringProperty REASON;
    private final StringProperty TIME;
    private final StringProperty DATE;
    private final StringProperty PERSONNEL;
    private final StringProperty REQUEST_ID;
    private final StringProperty APPROVAL_STATUS;
    private final StringProperty QTY_LEFT;
    private final StringProperty PRO_DATE;
    private final StringProperty EXP_DATE;

    public StockAdjustmentRequestDataClass(Integer id, String item, String batchNo, String qtyAvailable, String adjValue, String option,
                                           String reason, String time, String date, String personnel, String reqID, String appStatus,
                                           String qtyLeft, String proDate, String expDate){
        this.ID = new SimpleIntegerProperty(id);
        this.ITEM = new SimpleStringProperty(item);
        this.BATCH_NO = new SimpleStringProperty(batchNo);
        this.QTY_AVAILABLE = new SimpleStringProperty(qtyAvailable);
        this.ADJUSTMENT_VALUE = new SimpleStringProperty(adjValue);
        this.OPTION = new SimpleStringProperty(option);
        this.REASON = new SimpleStringProperty(reason);
        this.TIME = new SimpleStringProperty(time);
        this.DATE = new SimpleStringProperty(date);
        this.PERSONNEL = new SimpleStringProperty(personnel);
        this.REQUEST_ID = new SimpleStringProperty(reqID);
        this.APPROVAL_STATUS = new SimpleStringProperty(appStatus);
        this.QTY_LEFT = new SimpleStringProperty(qtyLeft);
        this.PRO_DATE = new SimpleStringProperty(proDate);
        this.EXP_DATE = new SimpleStringProperty(expDate);
    }

    public String getPRO_DATE() {
        return PRO_DATE.get();
    }

    public StringProperty PRO_DATEProperty() {
        return PRO_DATE;
    }

    public void setPRO_DATE(String PRO_DATE) {
        this.PRO_DATE.set(PRO_DATE);
    }

    public String getEXP_DATE() {
        return EXP_DATE.get();
    }

    public StringProperty EXP_DATEProperty() {
        return EXP_DATE;
    }

    public void setEXP_DATE(String EXP_DATE) {
        this.EXP_DATE.set(EXP_DATE);
    }

    public String getQTY_LEFT() {
        return QTY_LEFT.get();
    }

    public StringProperty QTY_LEFTProperty() {
        return QTY_LEFT;
    }

    public void setQTY_LEFT(String QTY_LEFT) {
        this.QTY_LEFT.set(QTY_LEFT);
    }

    public String getAPPROVAL_STATUS() {
        return APPROVAL_STATUS.get();
    }

    public StringProperty APPROVAL_STATUSProperty() {
        return APPROVAL_STATUS;
    }

    public void setAPPROVAL_STATUS(String APPROVAL_STATUS) {
        this.APPROVAL_STATUS.set(APPROVAL_STATUS);
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

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getITEM() {
        return ITEM.get();
    }

    public StringProperty ITEMProperty() {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        this.ITEM.set(ITEM);
    }

    public String getBATCH_NO() {
        return BATCH_NO.get();
    }

    public StringProperty BATCH_NOProperty() {
        return BATCH_NO;
    }

    public void setBATCH_NO(String BATCH_NO) {
        this.BATCH_NO.set(BATCH_NO);
    }

    public String getQTY_AVAILABLE() {
        return QTY_AVAILABLE.get();
    }

    public StringProperty QTY_AVAILABLEProperty() {
        return QTY_AVAILABLE;
    }

    public void setQTY_AVAILABLE(String QTY_AVAILABLE) {
        this.QTY_AVAILABLE.set(QTY_AVAILABLE);
    }

    public String getADJUSTMENT_VALUE() {
        return ADJUSTMENT_VALUE.get();
    }

    public StringProperty ADJUSTMENT_VALUEProperty() {
        return ADJUSTMENT_VALUE;
    }

    public void setADJUSTMENT_VALUE(String ADJUSTMENT_VALUE) {
        this.ADJUSTMENT_VALUE.set(ADJUSTMENT_VALUE);
    }

    public String getOPTION() {
        return OPTION.get();
    }

    public StringProperty OPTIONProperty() {
        return OPTION;
    }

    public void setOPTION(String OPTION) {
        this.OPTION.set(OPTION);
    }

    public String getREASON() {
        return REASON.get();
    }

    public StringProperty REASONProperty() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON.set(REASON);
    }

    public String getTIME() {
        return TIME.get();
    }

    public StringProperty TIMEProperty() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME.set(TIME);
    }

    public String getDATE() {
        return DATE.get();
    }

    public StringProperty DATEProperty() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE.set(DATE);
    }

    public String getPERSONNEL() {
        return PERSONNEL.get();
    }

    public StringProperty PERSONNELProperty() {
        return PERSONNEL;
    }

    public void setPERSONNEL(String PERSONNEL) {
        this.PERSONNEL.set(PERSONNEL);
    }
}

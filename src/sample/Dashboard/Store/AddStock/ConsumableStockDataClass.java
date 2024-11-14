package sample.Dashboard.Store.AddStock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConsumableStockDataClass {
    private final IntegerProperty ID;
    private final StringProperty SUPPLIER_NAME;
    private final StringProperty SUPPLIER_TEL;
    private final StringProperty ITEM;
    private final StringProperty BATCH_NO;
    private final StringProperty PACKS_RECEIVED;
    private final StringProperty TOTAL_QTY_IN_PACK;
    private final StringProperty UNIT_PACK_PRICE;
    private final StringProperty UNIT_ITEM_PRICE;
    private final StringProperty MINIMUM_STOCK;
    private final StringProperty TOTAL_UNIT_QTY;
    private final StringProperty TOTAL_PACKS_AMOUNT;
    private final StringProperty PRODUCTION_DATE;
    private final StringProperty EXPIRY_DATE;
    private final StringProperty TIME;
    private final StringProperty DATE;
    private final StringProperty PERSONNEL;
    private final StringProperty REQUEST_ID;
    private final StringProperty QTY_LEFT;

    public ConsumableStockDataClass(Integer id, String supplierName, String supplierTel, String item, String batchNo, String packsReceived,
                                    String totalQtyInPack, String unitPackPrice, String unitItemPrice, String minimumStock, String totalUnitQty,
                                    String totalPacksAmount, String proDate, String expDate, String time, String date, String user, String reqId,
                                    String qtyLeft){
        this.ID = new SimpleIntegerProperty(id);
        this.SUPPLIER_NAME = new SimpleStringProperty(supplierName);
        this.SUPPLIER_TEL = new SimpleStringProperty(supplierTel);
        this.ITEM = new SimpleStringProperty(item);
        this.BATCH_NO = new SimpleStringProperty(batchNo);
        this.PACKS_RECEIVED = new SimpleStringProperty(packsReceived);
        this.TOTAL_QTY_IN_PACK = new SimpleStringProperty(totalQtyInPack);
        this.UNIT_PACK_PRICE = new SimpleStringProperty(unitPackPrice);
        this.UNIT_ITEM_PRICE = new SimpleStringProperty(unitItemPrice);
        this.MINIMUM_STOCK = new SimpleStringProperty(minimumStock);
        this.TOTAL_UNIT_QTY = new SimpleStringProperty(totalUnitQty);
        this.TOTAL_PACKS_AMOUNT = new SimpleStringProperty(totalPacksAmount);
        this.PRODUCTION_DATE = new SimpleStringProperty(proDate);
        this.EXPIRY_DATE = new SimpleStringProperty(expDate);
        this.TIME = new SimpleStringProperty(time);
        this.DATE = new SimpleStringProperty(date);
        this.PERSONNEL = new SimpleStringProperty(user);
        this.REQUEST_ID = new SimpleStringProperty(reqId);
        this.QTY_LEFT = new SimpleStringProperty(qtyLeft);
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

    public String getSUPPLIER_NAME() {
        return SUPPLIER_NAME.get();
    }

    public StringProperty SUPPLIER_NAMEProperty() {
        return SUPPLIER_NAME;
    }

    public void setSUPPLIER_NAME(String SUPPLIER_NAME) {
        this.SUPPLIER_NAME.set(SUPPLIER_NAME);
    }

    public String getSUPPLIER_TEL() {
        return SUPPLIER_TEL.get();
    }

    public StringProperty SUPPLIER_TELProperty() {
        return SUPPLIER_TEL;
    }

    public void setSUPPLIER_TEL(String SUPPLIER_TEL) {
        this.SUPPLIER_TEL.set(SUPPLIER_TEL);
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

    public String getPACKS_RECEIVED() {
        return PACKS_RECEIVED.get();
    }

    public StringProperty PACKS_RECEIVEDProperty() {
        return PACKS_RECEIVED;
    }

    public void setPACKS_RECEIVED(String PACKS_RECEIVED) {
        this.PACKS_RECEIVED.set(PACKS_RECEIVED);
    }

    public String getTOTAL_QTY_IN_PACK() {
        return TOTAL_QTY_IN_PACK.get();
    }

    public StringProperty TOTAL_QTY_IN_PACKProperty() {
        return TOTAL_QTY_IN_PACK;
    }

    public void setTOTAL_QTY_IN_PACK(String TOTAL_QTY_IN_PACK) {
        this.TOTAL_QTY_IN_PACK.set(TOTAL_QTY_IN_PACK);
    }

    public String getUNIT_PACK_PRICE() {
        return UNIT_PACK_PRICE.get();
    }

    public StringProperty UNIT_PACK_PRICEProperty() {
        return UNIT_PACK_PRICE;
    }

    public void setUNIT_PACK_PRICE(String UNIT_PACK_PRICE) {
        this.UNIT_PACK_PRICE.set(UNIT_PACK_PRICE);
    }

    public String getUNIT_ITEM_PRICE() {
        return UNIT_ITEM_PRICE.get();
    }

    public StringProperty UNIT_ITEM_PRICEProperty() {
        return UNIT_ITEM_PRICE;
    }

    public void setUNIT_ITEM_PRICE(String UNIT_ITEM_PRICE) {
        this.UNIT_ITEM_PRICE.set(UNIT_ITEM_PRICE);
    }

    public String getMINIMUM_STOCK() {
        return MINIMUM_STOCK.get();
    }

    public StringProperty MINIMUM_STOCKProperty() {
        return MINIMUM_STOCK;
    }

    public void setMINIMUM_STOCK(String MINIMUM_STOCK) {
        this.MINIMUM_STOCK.set(MINIMUM_STOCK);
    }

    public String getTOTAL_UNIT_QTY() {
        return TOTAL_UNIT_QTY.get();
    }

    public StringProperty TOTAL_UNIT_QTYProperty() {
        return TOTAL_UNIT_QTY;
    }

    public void setTOTAL_UNIT_QTY(String TOTAL_UNIT_QTY) {
        this.TOTAL_UNIT_QTY.set(TOTAL_UNIT_QTY);
    }

    public String getTOTAL_PACKS_AMOUNT() {
        return TOTAL_PACKS_AMOUNT.get();
    }

    public StringProperty TOTAL_PACKS_AMOUNTProperty() {
        return TOTAL_PACKS_AMOUNT;
    }

    public void setTOTAL_PACKS_AMOUNT(String TOTAL_PACKS_AMOUNT) {
        this.TOTAL_PACKS_AMOUNT.set(TOTAL_PACKS_AMOUNT);
    }

    public String getPRODUCTION_DATE() {
        return PRODUCTION_DATE.get();
    }

    public StringProperty PRODUCTION_DATEProperty() {
        return PRODUCTION_DATE;
    }

    public void setPRODUCTION_DATE(String PRODUCTION_DATE) {
        this.PRODUCTION_DATE.set(PRODUCTION_DATE);
    }

    public String getEXPIRY_DATE() {
        return EXPIRY_DATE.get();
    }

    public StringProperty EXPIRY_DATEProperty() {
        return EXPIRY_DATE;
    }

    public void setEXPIRY_DATE(String EXPIRY_DATE) {
        this.EXPIRY_DATE.set(EXPIRY_DATE);
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

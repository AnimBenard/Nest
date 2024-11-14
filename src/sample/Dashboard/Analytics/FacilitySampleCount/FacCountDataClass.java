package sample.Dashboard.Analytics.FacilitySampleCount;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FacCountDataClass {
    private final StringProperty POSITION;
    private final StringProperty FACILITY;
    private final StringProperty COUNT;

    public FacCountDataClass(String pos, String fac, String count){
        this.POSITION = new SimpleStringProperty(pos);
        this.FACILITY = new SimpleStringProperty(fac);
        this.COUNT = new SimpleStringProperty(count);
    }

    public String getPOSITION() {
        return POSITION.get();
    }

    public StringProperty POSITIONProperty() {
        return POSITION;
    }

    public void setPOSITION(String POSITION) {
        this.POSITION.set(POSITION);
    }

    public String getFACILITY() {
        return FACILITY.get();
    }

    public StringProperty FACILITYProperty() {
        return FACILITY;
    }

    public void setFACILITY(String FACILITY) {
        this.FACILITY.set(FACILITY);
    }

    public String getCOUNT() {
        return COUNT.get();
    }

    public StringProperty COUNTProperty() {
        return COUNT;
    }

    public void setCOUNT(String COUNT) {
        this.COUNT.set(COUNT);
    }
}

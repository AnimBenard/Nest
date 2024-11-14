package sample.Alert;

import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetRedNode {
    String TIME, REQUEST_ID;
    java.sql.Date DATE;
    public void formatCBToUpper(ComboBox<String> comboBox){
        comboBox.getEditor().setTextFormatter(new TextFormatter<Object>(text -> {
            text.setText(text.getText().toUpperCase());
            return text;
        }));
    }
    public void checkNumberField(TextField field) {
        field.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.matches("\\d*")) return;
            field.setText(t1.replaceAll("[^\\d]", ""));
        });
    }
    public void checkNumberFieldCB(ComboBox comboBox) {
        comboBox.valueProperty().addListener((observableValue, s, t1) -> {
            if (String.valueOf(t1).matches("\\d*")) return;
            comboBox.setValue(String.valueOf(t1).replaceAll("[^\\d]", ""));
        });
    }
    public void formatTFieldToUpper(TextField field) {
        field.setTextFormatter(new TextFormatter<Object>((text) -> {
            text.setText(text.getText().toUpperCase());
            return text;
        }));
    }
    public void timeAndDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        DATE = new java.sql.Date(date.getTime());
        DateFormat format = new SimpleDateFormat("HH:mm");
        TIME = format.format(calendar.getTime());
    }

    public String getTIME() {
        return TIME;
    }

    public java.sql.Date getDATE() {
        return DATE;
    }
    public void setRedCB(ComboBox<String> comboBox){
        comboBox.setStyle("-fx-border-color: RED");
    }
    public void unSetRedCB(ComboBox<String> comboBox){
        comboBox.setStyle("-fx-border-color: #00c0ff; -fx-border-radius: 5; -fx-background-radius: 5");
    }
    public void setRedTextField(TextField field){
        field.setStyle("-fx-border-color: RED");
    }
    public void unSetRedTextField(TextField field){
        field.setStyle("-fx-border-color: #00c0ff; -fx-border-radius: 5; -fx-background-radius: 5");
    }
    public void setRedPF(PasswordField field){
        field.setStyle("-fx-border-color: RED");
    }
    public void setRedTA(TextArea field){
        field.setStyle("-fx-border-color: RED");
    }

    public void createRequestId(String item){
        //creates requestCode
        Date date = new Date();
        java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
        Calendar cal = Calendar.getInstance();
        DateFormat tCodeTimeFormat = new SimpleDateFormat("HHmmss");
        DateFormat tCodeDateFormat = new SimpleDateFormat("yyyyMMdd");
        String tcTime = tCodeTimeFormat.format(cal.getTime());
        String tcDate = tCodeDateFormat.format(sqlDate1);
        String firstChars = item.substring(0, 1);
        REQUEST_ID = firstChars + tcDate + tcTime;
    }
    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void autoCompleteCB(ObservableList<String> data, ComboBox<String> cBox){
        FilteredList<String> filteredList = new FilteredList<>(data, b -> true);
        cBox.getEditor().textProperty().addListener((observableValue, s, t1) -> {
            final TextField editor = cBox.getEditor();
            String selected = cBox.getSelectionModel().getSelectedItem();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(selected == null || !selected.equals(editor.getText())){
                        filteredList.setPredicate(data -> {
                            if(t1 == null || t1.isEmpty()){
                                return true;
                            }else if(data.toUpperCase().startsWith(t1.toUpperCase())) {
                                cBox.show();
                                return true;
                            }
                            return false;

                        });
                    }
                }
            });
        });
        cBox.setItems(filteredList);
    }
    public void checkIntegerNumberInFields(TextField field){
        field.textProperty().addListener((observableValue, s, value) -> {
            if(field.getText() == null || field.getText().isEmpty()){
                //
            }else {
                try{
                    Integer.parseInt(value);
                }catch (NumberFormatException exception){
                    setRedTextField(field);
                    new Shake(field).play();
                    field.clear();
                }
            }
        });
    }
    public void checkDecimalNumberInFields(TextField field){
        field.textProperty().addListener((observableValue, s, value) -> {
            if(field.getText() == null || field.getText().isEmpty()){
                //
            }else {
                try{
                    Double.parseDouble(value);
                }catch (NumberFormatException exception){
                    setRedTextField(field);
                    new Shake(field).play();
                    field.clear();
                }
            }

        });
    }
    public void setRedDP(DatePicker datePicker){
        datePicker.setStyle("-fx-border-color: RED");
    }

    public void setAnchorPaneHoverStyles(AnchorPane pane){
        pane.setOnMouseEntered(e -> {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(0.1);
            pane.setEffect(shadow);
        });
        pane.setOnMouseExited(e -> {
            pane.setEffect(null);
        });

    }
    public void setImageViewHoverStyles(ImageView image){
        image.setOnMouseEntered(e -> {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(0.05);
            image.setEffect(shadow);
        });
        image.setOnMouseExited(e -> {
            image.setEffect(null);
        });
    }
}

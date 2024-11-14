package sample.Alert;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AutoCompleteCB {
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
}

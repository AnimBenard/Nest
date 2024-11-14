package sample.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DialogBox {

    public void showDialogBox(ButtonType type1, String contentText, String title, Image icon){

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText(contentText);
        dialog.setTitle(title);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        dialog.getDialogPane().getButtonTypes().add(type1);
        dialog.showAndWait();
    }
}

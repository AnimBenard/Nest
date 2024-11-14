package sample.SignupLogin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OpenLogin {
    private static Stage stage;
    public void openLoginScene() throws IOException {
        Image icon = new Image("/sample/images/etblogo.png");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/SignupLogin/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 824, 500);
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("eTBD");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.centerOnScreen();
        //loads the insight ach pane
        //call a method that loads the pane to the admin menu fxml
        stage.show();
    }
    public void closeStage(){
        stage.close();
    }
}

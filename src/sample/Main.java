package sample;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.SignupLogin.OpenLogin;

import java.io.IOException;

public class Main extends Application {
    OpenLogin openLogin = new OpenLogin();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Image icon = new Image("/sample/images/etblogo.png");
        StageStyle style = StageStyle.TRANSPARENT;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 600, 145, Color.TRANSPARENT);
        root.setStyle("-fx-background-color: transparent");
        scene.setCursor(Cursor.WAIT);
        primaryStage.setTitle("Easy TBD");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.initStyle(style);
        primaryStage.centerOnScreen();
        primaryStage.show();

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
        pauseTransition.setOnFinished(e -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), root);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.play();
            fadeTransition.setOnFinished(e1 -> {
                primaryStage.close();
                closeAndOpenLoginScene(primaryStage);
            });
        });
        pauseTransition.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void closeAndOpenLoginScene(Stage stage){
        stage.close();
        try {
            openLogin.openLoginScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

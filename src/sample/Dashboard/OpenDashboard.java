package sample.Dashboard;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OpenDashboard {
    public void openDashBoardScene(String name, String userNo) throws IOException {
        Image icon = new Image("/sample/images/etblogo.png");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/dashboard.fxml"));
        Parent root = loader.load();
        DashboardController controller = loader.getController();
        controller.getUserNo(name, userNo);
        Scene scene = new Scene(root, 1366, 700);
        Stage stage = new Stage();
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
}

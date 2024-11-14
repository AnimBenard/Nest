package sample.Dashboard.Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Alert.SetRedNode;
import sample.Dashboard.Store.AddStock.AddStockController;
import sample.Dashboard.Store.StockAdjustment.StockAdjustmentController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {
    @FXML
    AnchorPane ach_addStock, ach_stockAdjustment;
    @FXML
    Label lb_name, lb_userNo;

    SetRedNode setRedNode = new SetRedNode();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRedNode.setAnchorPaneHoverStyles(ach_addStock);
        setRedNode.setAnchorPaneHoverStyles(ach_stockAdjustment);
    }
    public void getUserNo(String name, String userNo){
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }
    @FXML
    public void openAddNewStock(MouseEvent event){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Store/AddStock/addStock.fxml"));
            DialogPane pane = loader.load();
            AddStockController controller = loader.getController();
            controller.setUserLabels(lb_name.getText(), lb_userNo.getText());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            dialog.setTitle("");
            stage.setOnCloseRequest(e -> stage.hide());
            dialog.showAndWait();

        }catch (IOException ioException){

        }
    }
    @FXML
    public void openStockAdjustment(MouseEvent event){
        try{
            Image icon = new Image("/sample/images/etblogo.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Dashboard/Store/StockAdjustment/stockAdjustment.fxml"));
            DialogPane pane = loader.load();
            StockAdjustmentController controller = loader.getController();
            controller.setUsername(lb_name.getText(), lb_userNo.getText());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            dialog.setTitle("");
            stage.setOnCloseRequest(e -> stage.hide());
            dialog.showAndWait();

        }catch (IOException ioException){

        }
    }
}

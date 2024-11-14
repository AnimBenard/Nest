package sample.Dashboard.Results.GeneExpert;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import sample.Alert.DialogBox;
import sample.Dashboard.Store.StockAdjustment.FetchBatchNoQtyAvailable;
import sample.Dashboard.Store.StockAdjustment.UpdateSockAfterAdjustment;

import java.sql.*;

public class AddResultsToDb {
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String CLASS_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String ADD_RESULT = "UPDATE request_list SET results = ?, examined_by = ?, examined_time = ?, examined_date = ?, remarks = ?, cartridge_used = ? WHERE sample_id = ?";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DialogBox dialogBox = new DialogBox();

    UpdateSockAfterAdjustment updateSockAfterAdjustment = new UpdateSockAfterAdjustment();
    FetchBatchNoQtyAvailable fetchBatchNoQtyAvailable = new FetchBatchNoQtyAvailable();

    public void addResults(String results, String examBy, String time, Date date, String remarks, String sampleID, Integer used){
        try {
            Class.forName(CLASS_PATH);
            connection = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(ADD_RESULT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, results);
            preparedStatement.setString(2, examBy);
            preparedStatement.setString(3, time);
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, remarks);
            preparedStatement.setString(6, String.valueOf(used));
            preparedStatement.setString(7, sampleID);
            preparedStatement.executeLargeUpdate();
            updateStockCartridge(used);//update stock after results are added by subtracting cartridge used
            Image icon = new Image("/sample/images/etblogo.png");
            String contentText = "Results added successfully";
            String dtitle = "Success!";
            ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogBox.showDialogBox(btnType, contentText, dtitle, icon);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void updateStockCartridge(Integer used){
        fetchBatchNoQtyAvailable.fetchBatchQtyAvailable("GeneXpert Cartridge");
        Integer qtyAvailable = Integer.valueOf(fetchBatchNoQtyAvailable.getTOTAL_AVAILABLE());
        Integer qtyLeft = qtyAvailable - used;
        updateSockAfterAdjustment.updateStock(String.valueOf(qtyLeft), "GeneXpert Cartridge");
    }
}

package sample.Dashboard.Report.YearlyXpert.YearHub;

import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.*;
import com.spire.pdf.tables.PdfCellStyle;
import com.spire.pdf.tables.PdfHeaderSource;
import com.spire.pdf.tables.PdfTable;
import com.spire.pdf.tables.PdfTableDataSourceType;
import com.spire.pdf.tables.table.DataTable;
import com.spire.pdf.tables.table.common.JdbcAdapter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.sql.*;

public class YearCartridgeReportHub {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String RESULTS_QUERY = "(SELECT COALESCE('Number of cartridges in the stock at the begining of the reortting period', '') AS indicator,  IFNULL(qty_available, 0) as qty FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND YEAR(filled_date) = ? ORDER BY Id ASC LIMIT 1) " +
            "UNION " +
            "SELECT COALESCE('Number of cartridges received during reporting period', '') AS indicator, IFNULL(SUM(adjustment_value), 0) FROM stock_adjustment_request WHERE reason = 'Shortage' AND YEAR(filled_date) = ? " +
            "UNION " +
            "SELECT COALESCE('Number of cartridges used at the end of the reporting period', '') AS indicator ,  IFNULL(SUM(cartridge_used), 0) FROM request_list WHERE YEAR(examined_date) = ? AND request_type = 'HUB' " +
            "UNION  " +
            "(SELECT  COALESCE('Number of cartridges in stock at the end of the reporting period', '') AS indicator, quantity_left FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND YEAR(filled_date) = ? ORDER BY Id DESC LIMIT 1) " +
            " UNION " +
            " (SELECT COALESCE('Any stock out of cartridges during the reporting period', '') AS indicator, CASE WHEN quantity_left <= 0 THEN 'Yes' ELSE 'No' END FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND YEAR(filled_date) = ? ORDER BY Id DESC LIMIT 1) " +
            "UNION " +
            " (SELECT COALESCE('If Yes for how many days was there a stock out of cartridge', '') AS indicator, CASE WHEN quantity_left <= 0 THEN DATEDIFF(CURDATE(), MIN(filled_date)) ELSE '0' END FROM stock_adjustment_request WHERE item_description = 'GeneXpert Cartridge' AND YEAR(filled_date) = ? ORDER BY Id DESC LIMIT 1) " +
            "UNION " +
            "SELECT COALESCE('Number any cartridges expired during reporting period', '') AS indicator, iFNULL(SUM(adjustment_value), 0) FROM stock_adjustment_request WHERE reason = 'Expired' AND YEAR(filled_date) = ?";

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void addDataToTable(PdfPageBase page, String ward, String year, String month) {
        PdfBrush brush1 = PdfBrushes.getBlack();
        //create data table
        PdfTable table = new PdfTable();
        table.getStyle().setCellPadding(1);
        table.getStyle().setBorderPen(new PdfPen(brush1, 0.75f));
        table.getStyle().getDefaultStyle().setBackgroundBrush(PdfBrushes.getWhite());
        table.getStyle().getDefaultStyle().setFont(new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 9)));
        table.getStyle().setAlternateStyle(new PdfCellStyle());
        table.getStyle().getAlternateStyle().setBackgroundBrush(PdfBrushes.getLightGray());
        table.getStyle().getAlternateStyle().setFont(new PdfTrueTypeFont(new Font("Arial", 0, 9)));
        table.getStyle().setHeaderSource(PdfHeaderSource.Column_Captions);
        //table.getStyle().getHeaderStyle().setBackgroundBrush(PdfBrushes.getCadetBlue());
        table.getStyle().getHeaderStyle().setFont(new PdfTrueTypeFont(new Font("Arial", Font.BOLD, 9)));

        table.getStyle().getHeaderStyle().setStringFormat(new PdfStringFormat(PdfTextAlignment.Center));
        table.getStyle().setShowHeader(true);
        DataTable dataTable = new DataTable();


        try {
            Class.forName(CLASSPATH);
            try {
                conn = DriverManager.getConnection(DB_PATH, DB_USERNAME, DB_PASSWORD);
                preparedStatement = conn.prepareStatement(RESULTS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                preparedStatement.setString(1, year);
                preparedStatement.setString(2, year);
                preparedStatement.setString(3, year);
                preparedStatement.setString(4, year);
                preparedStatement.setString(5, year);
                preparedStatement.setString(6, year);
                preparedStatement.setString(7, year);
                resultSet = preparedStatement.executeQuery();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(dataTable, resultSet);
                table.setDataSourceType(PdfTableDataSourceType.Table_Direct);
                table.setDataSource(dataTable);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        float width = (float) page.getCanvas().getClientSize().getWidth() - (float) (table.getColumns().getCount() + 1) * table.getStyle().getBorderPen().getWidth();
        table.getColumns().get(0).setWidth(width * 0.35f * width);
        table.getColumns().get(0).setStringFormat(new PdfStringFormat(PdfTextAlignment.Left, PdfVerticalAlignment.Middle));
        table.getColumns().get(1).setWidth(width * 0.13f * width);
        table.getColumns().get(1).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getStyle().setShowHeader(true);
        table.getColumns().get(0).setColumnName("Indicator");
        table.getColumns().get(1).setColumnName("No of Cartridges");
        //draw investigation title before table
        float y = 10;
        PdfLayoutResult result = table.draw(page, new Point2D.Float(0, y));

        //draw lab scientist label
        /*y = (float) result.getBounds().getHeight() + 130;
        addLabPersonnelLabel(page, y);*/
    }

}


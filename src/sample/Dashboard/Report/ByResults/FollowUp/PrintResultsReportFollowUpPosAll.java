package sample.Dashboard.Report.ByResults.FollowUp;

import com.spire.pdf.*;
import com.spire.pdf.automaticfields.PdfAutomaticField;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.*;
import com.spire.pdf.tables.PdfCellStyle;
import com.spire.pdf.tables.PdfHeaderSource;
import com.spire.pdf.tables.PdfTable;
import com.spire.pdf.tables.PdfTableDataSourceType;
import com.spire.pdf.tables.table.DataTable;
import com.spire.pdf.tables.table.common.JdbcAdapter;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.sql.*;

public class PrintResultsReportFollowUpPosAll {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String RESULTS_QUERY = "SELECT sample_id, surname, othernames, CONCAT(age, ' ', age_units) AS age, sex, referring_facility, address, reason_for_examination, results, request_date FROM request_list WHERE reason_for_examination = 'Follow Up' AND request_date BETWEEN ? AND ? AND results LIKE '%Scanty%' OR results LIKE '1+' OR results LIKE '2+' OR results LIKE '3+' OR results LIKE '4+'";

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void printReportForm(String result, String reqType,String from, String to, String datePrinted) {
        //margin
        PdfDocument doc = new PdfDocument();
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(margin.getTop());
        margin.setLeft(unitCvtr.convertUnits(0.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(margin.getLeft());
        addHeaderAndFooter(doc, PdfPageSize.A4, margin, result, reqType, from, to, datePrinted);
        PdfPageBase page = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        PdfPageBase page2 = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        addDataToTable(page, result, reqType, from, to);
        //draw watermarks
        String waterMark = "VERIFIED COPY";
        addWaterMark(page, waterMark);
        doc.saveToFile("C:/Users/animb/IdeaProjects/Vibro/output/monthlyWardReportForm.pdf");

    }
    public void savePDFReportForm(String result, String reqType, String from, String to, String datePrinted, String filePath) {
        //margin
        PdfDocument doc = new PdfDocument();
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.2f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(margin.getTop());
        margin.setLeft(unitCvtr.convertUnits(0.3f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(margin.getLeft());
        addHeaderAndFooter(doc, PdfPageSize.A3, margin, result, reqType, from, to, datePrinted);
        PdfPageBase page = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        addDataToTable(page, result, reqType, from, to);

        //draw watermarks
        String waterMark = "VERIFIED COPY";
        addWaterMark(page, waterMark);
        doc.saveToFile(filePath, FileFormat.PDF);

    }

    public void addHeaderAndFooter(PdfDocument doc, Dimension2D pageSize, PdfMargins margin, String result, String reqType, String from, String to, String datePrinted) {
        PdfPageTemplateElement header = new PdfPageTemplateElement(margin.getLeft(), pageSize.getHeight());
        doc.getTemplate().setLeft(header);
        PdfPageTemplateElement topSpace = new PdfPageTemplateElement(pageSize.getWidth(), margin.getTop() + 80);
        topSpace.setForeground(true);
        doc.getTemplate().setTop(topSpace);
        // draw app icon
        // addAppLogo(topSpace);
        addHospitalLogo(topSpace);

        //count patients and investigations


        String font1 = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        String detailVariableFont = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.BOLD, 12));
        PdfTrueTypeFont font2 = new PdfTrueTypeFont(new Font("Arial", Font.BOLD, 11));
        PdfTrueTypeFont font3 = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 9));
        PdfTrueTypeFont font4 = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 12));
        PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Center);
        PdfStringFormat format2 = new PdfStringFormat(PdfTextAlignment.Center);
        String FacilityName = "METHODIST HOSPITAL WENCHI";
        String methodName = "Monthly Report for MTB RIF Testing";
        String wardName = reqType + " (Result = " + result + ")";
        String dateRange = "From: "+ from + "  to " + to;
        //String totalInv = "Total investigations: " + countAll;
        Dimension2D dimension2D = new Dimension();
        Dimension2D dimension2D2 = new Dimension();
        dimension2D.setSize(font.measureString(FacilityName, format));
        dimension2D2.setSize(font.measureString(methodName, format));

        float y = topSpace.getHeight() - font.getHeight() - 1;
        PdfPen pen = new PdfPen(new PdfRGBColor(Color.black), 0.75f);
        //topSpace.getGraphics().setTransparency(0.5f);
        y = y - 85 - (float) dimension2D.getHeight();
        topSpace.getGraphics().drawString(FacilityName, font, PdfBrushes.getBlack(), pageSize.getWidth() / 2, y, format);
        y = y + (float) font.measureString(FacilityName, format).getHeight();
        y = y + 5;
        topSpace.getGraphics().drawString(methodName, font2, PdfBrushes.getBlack(), pageSize.getWidth() / 2, y, format);
        y = y + (float) font.measureString(methodName, format).getHeight();
        y = y + 5;
        topSpace.getGraphics().drawString(wardName, font2, PdfBrushes.getBlack(), pageSize.getWidth() / 2, y, format);
        y = y + (float) font.measureString(wardName, format).getHeight();
        y = y + 10;
        topSpace.getGraphics().drawLine(pen, margin.getLeft(), y, pageSize.getWidth() - margin.getRight(), y);
        y = y + 9;
        topSpace.getGraphics().drawString(dateRange, font3, PdfBrushes.getBlack(), 80f, y, format);
        //topSpace.getGraphics().drawString(totalInv, font3, PdfBrushes.getBlack(), 380f, y, format);
        //draw header top line
        y = y + 18;
        topSpace.getGraphics().drawLine(pen, margin.getLeft(), y, pageSize.getWidth() - margin.getRight(), y);
        //draw name variable

        PdfPageTemplateElement rightSpace = new PdfPageTemplateElement(margin.getRight(), pageSize.getHeight());
        doc.getTemplate().setRight(rightSpace);
        //Draw dynamic fields as footer
        PdfPageTemplateElement footer = new PdfPageTemplateElement(pageSize.getWidth(), margin.getBottom());
        footer.setForeground(true);
        doc.getTemplate().setBottom(footer);
        y = font.getHeight() + 1;
        footer.getGraphics().setTransparency(0.5f);
        footer.getGraphics().drawLine(pen, margin.getLeft(), y, pageSize.getWidth() - margin.getRight(), y);
        y = y + 1;
        PdfPageNumberField pageNumber = new PdfPageNumberField();
        PdfPageCountField pageCount = new PdfPageCountField();
        PdfCompositeField pageNumberLabel = new PdfCompositeField();
        PdfCompositeField cfDatePrinted = new PdfCompositeField();
        pageNumberLabel.setAutomaticFields(new PdfAutomaticField[]{pageNumber, pageCount});
        pageNumberLabel.setBrush(PdfBrushes.getBlack());
        pageNumberLabel.setFont(font);
        String pageNumFont = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        format = new PdfStringFormat(PdfTextAlignment.Right);
        pageNumberLabel.setStringFormat(format);
        cfDatePrinted.setText("printed on: " + datePrinted);
        cfDatePrinted.setFont(new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 8)));
        pageNumberLabel.setText("page {0} of {1}");
        pageNumberLabel.setFont(new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 8)));
        pageNumberLabel.setBounds(footer.getBounds());
        pageNumberLabel.draw(footer.getGraphics(), -margin.getLeft(), y);
        cfDatePrinted.draw(footer.getGraphics(), margin.getRight(), y);
    }

    public void addWaterMark(PdfPageBase page, String waterMark) {
        String font = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        Dimension2D dimension2D = new Dimension();
        dimension2D.setSize(page.getCanvas().getClientSize().getWidth() / 2, page.getCanvas().getClientSize().getHeight() / 3);
        PdfTilingBrush brush = new PdfTilingBrush(dimension2D);
        brush.getGraphics().setTransparency(0.3f);
        brush.getGraphics().save();
        brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2, (float) brush.getSize().getHeight() / 2);
        brush.getGraphics().rotateTransform(-45);
        brush.getGraphics().drawString(waterMark, new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 24)), PdfBrushes.getLightGray(), 0, 0, new PdfStringFormat(PdfTextAlignment.Center));
        brush.getGraphics().restore();
        brush.getGraphics().setTransparency(1);
        Rectangle2D loRect = new Rectangle2D.Float();
        loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
        page.getCanvas().drawRectangle(brush, loRect);
    }

    /*public void addAppLogo(PdfPageTemplateElement topSpace){
        //this is called in add header and footer method
        String appLogo = "C:/Users/animb/IdeaProjects/Vibro/src/sample/images/vibrio.png";
        String fontFile = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 10));
        String appName = "Vibro";
        PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Center);

        PdfImage appImage = PdfImage.fromFile(appLogo);
        float appImgWidth = 15;
        float appImgHeight = 14;
        double x = 45;
        float y = topSpace.getHeight() - 120;
        float y2 = topSpace.getHeight() - 103;
        double x2 = 53;
        topSpace.getGraphics().drawString(appName, font, PdfBrushes.getBlack(), x2, y2, format);
        topSpace.getGraphics().drawImage(appImage, x, y, appImgWidth, appImgHeight);
    }*/
    public void addHospitalLogo(PdfPageTemplateElement topSpace){
        String hospitalLogo = "C:/Users/Public/EasyTBD/download.png";
        PdfImage appImage = PdfImage.fromFile(hospitalLogo);
        float appImgWidth = appImage.getWidth() / 5;
        float appImgHeight = appImage.getHeight() / 5;
        double x = 550;
        float y = topSpace.getHeight() - 110;
        topSpace.getGraphics().drawImage(appImage, x, y, appImgWidth, appImgHeight);
    }
    public void addDataToTable(PdfPageBase page, String result, String reqType, String from, String to) {
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
                preparedStatement.setString(1, from);
                preparedStatement.setString(2, to);
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
        table.getColumns().get(0).setWidth(width * 0.12f * width);
        table.getColumns().get(0).setStringFormat(new PdfStringFormat(PdfTextAlignment.Left, PdfVerticalAlignment.Middle));
        table.getColumns().get(1).setWidth(width * 0.25f * width);
        table.getColumns().get(1).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(2).setWidth(width * 0.25f * width);
        table.getColumns().get(2).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(3).setWidth(width * 0.12f * width);
        table.getColumns().get(3).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(4).setWidth(width * 0.08f * width);
        table.getColumns().get(4).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(5).setWidth(width * 0.30f * width);
        table.getColumns().get(5).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(6).setWidth(width * 0.20f * width);
        table.getColumns().get(6).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(7).setWidth(width * 0.15f * width);
        table.getColumns().get(7).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(8).setWidth(width * 0.12f * width);
        table.getColumns().get(8).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(9).setWidth(width * 0.12f * width);
        table.getColumns().get(9).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getStyle().setShowHeader(true);
        table.getColumns().get(0).setColumnName("Sample ID");
        table.getColumns().get(1).setColumnName("Surname");
        table.getColumns().get(2).setColumnName("Othername");
        table.getColumns().get(3).setColumnName("Age");
        table.getColumns().get(4).setColumnName("Sex");
        table.getColumns().get(5).setColumnName("Ref. Facility");
        table.getColumns().get(6).setColumnName("Address");
        table.getColumns().get(7).setColumnName("Investigation");
        table.getColumns().get(8).setColumnName("Result");
        table.getColumns().get(9).setColumnName("Req Date");

        //draw investigation title before table
        float y = 10;
        PdfLayoutResult results = table.draw(page, new Point2D.Float(0, y));
        //draw lab scientist label
        /*y = (float) result.getBounds().getHeight() + 130;
        addLabPersonnelLabel(page, y);*/
    }

    public void addInvestigationHeader(String invName, PdfPageBase page, float y) {
        //this will add investigation name as header
        String fontFile = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 12));
        PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Center);
        float x = (float) (page.getClientSize().getWidth() / 2);
        page.getCanvas().drawString(invName, font, PdfBrushes.getBlack(), x, y, format);
    }

    public void mergePDFFiles() {
        String[] filesPath = new String[]{
                "C:/Users/animb/IdeaProjects/Vibro/output/resultsForm.pdf",
                "C:/Users/animb/IdeaProjects/Vibro/output/resultsForm.pdf"
        };
        PdfDocumentBase newDoc = PdfDocument.mergeFiles(filesPath);
        newDoc.save("output/mergeFiles.pdf");
    }

    public void addLabPersonnelLabel(PdfPageBase page, float y) {
        String fontFile = "C:/Users/animb/IdeaProjects/Vibro/src/sample/fonts/Ubuntu.ttf";
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 10));
        PdfStringFormat format = new PdfStringFormat(PdfTextAlignment.Center);
        float x = 80;
        page.getCanvas().drawString("Lab Scientist: ______________", font, PdfBrushes.getBlack(), x, y, format);
    }
    /*public void saveFBCResultsToPDF(PdfDocument doc, String filePath, String inv, String tCode, String reqDate, String fullName, String age, String sex, String testCode, String reqDatePrint, String reqTime, String resDate, String resTime){
        //this method will do the same thing as print fbc results but
        //will save it to PDF
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(margin.getTop());
        margin.setLeft(unitCvtr.convertUnits(0.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(unitCvtr.convertUnits(0.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        addHeaderAndFooter(doc, PdfPageSize.A4, margin, fullName, age, sex, testCode, reqDatePrint, reqTime, resDate, resTime);
        PdfPageBase page=doc.getPages().add(PdfPageSize.A4, margin);
        addDataToTable(page, inv, tCode, reqDate);

        //draw watermarks
        String waterMark = "VERIFIED COPY";
        addWaterMark(page, waterMark);
        doc.saveToFile(filePath, FileFormat.PDF);

    }*/
}


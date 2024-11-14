package sample.Dashboard.Report;

import animatefx.animation.Shake;
import com.spire.pdf.PdfDocument;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.Alert.SetRedNode;
import sample.Dashboard.Hub.FetchRefFacility;
import sample.Dashboard.Report.ByAgeGroup.PrintReportByAgeGroup;
import sample.Dashboard.Report.ByRefFac.FetchAllRefFacility;
import sample.Dashboard.Report.ByRefFac.PrintRegisterReportByFac;
import sample.Dashboard.Report.ByResults.FollowUp.*;
import sample.Dashboard.Report.ByResults.NewCases.PrintResultsReport;
import sample.Dashboard.Report.ByResults.NewCases.PrintResultsReportAllResAllType;
import sample.Dashboard.Report.ByResults.NewCases.PrintResultsReportAllSelType;
import sample.Dashboard.Report.MonthlyXpert.All.PrintMonthlyGeneExpertReportAll;
import sample.Dashboard.Report.MonthlyXpert.Hub.PrintMonthlyGeneExpertReportHub;
import sample.Dashboard.Report.MonthlyXpert.Spokes.PrintMonthlyGeneExpertReportSpokes;
import sample.Dashboard.Report.Register.PrintRegisterReport;
import sample.Dashboard.Report.Register.PrintRegisterReportAllType;
import sample.Dashboard.Report.YearlyXpert.YearHub.PrintYearlyGeneExpertReportHub;
import sample.Dashboard.Report.YearlyXpert.YearReportAll.PrintYearlyGeneExpertReportAll;
import sample.Dashboard.Report.YearlyXpert.YearSpokes.PrintYearlyGeneExpertReportSpokes;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    ComboBox<String> cb_month, cb_year, cb_type, cb_yearly_year, cb_yearly_type, cb_resultNC, cb_typeNC, cb_resultFU, cb_typeFU;
    @FXML
    ComboBox<String> cb_monthMR, cb_yearMR, cb_typeMR, cb_monthMRBF, cb_yearMRBF, cb_typeMRBF, cb_monthReportByAge, cb_yearReportByAge;
    @FXML
    DatePicker dp_fromNC, dp_toNC, dp_fromFU, dp_toFU;
    @FXML
    Label lb_name, lb_userNo;
    @FXML
    Button btn_xPertExportMonth, btn_yearly_xPertExportMonth, btn_byResultsNC, btn_byResultsFU, btn_xPertExportMonthMR;

    SetRedNode setRedNode = new SetRedNode();
    FetchRefFacility fetchRefFacility = new FetchRefFacility();
    ObservableList<String> refFacData = FXCollections.observableArrayList();
    PrintMonthlyGeneExpertReportAll printMonthlyGeneExpertReportAll = new PrintMonthlyGeneExpertReportAll();
    PrintMonthlyGeneExpertReportSpokes printMonthlyGeneExpertReportSpokes = new PrintMonthlyGeneExpertReportSpokes();
    PrintMonthlyGeneExpertReportHub printMonthlyGeneExpertReportHub = new PrintMonthlyGeneExpertReportHub();
    PrintYearlyGeneExpertReportAll printYearlyGeneExpertReportAll = new PrintYearlyGeneExpertReportAll();
    PrintYearlyGeneExpertReportSpokes printYearlyGeneExpertReportSpokes = new PrintYearlyGeneExpertReportSpokes();
    PrintYearlyGeneExpertReportHub printYearlyGeneExpertReportHub = new PrintYearlyGeneExpertReportHub();
    PrintResultsReport printResultsReport = new PrintResultsReport();
    PrintResultsReportAllSelType printResultsReportAllSelType = new PrintResultsReportAllSelType();
    PrintResultsReportAllResAllType printResultsReportAllResAllType = new PrintResultsReportAllResAllType();
    PrintResultsReportFollowUpNegSelType printResultsReportFollowUpNegSelType = new PrintResultsReportFollowUpNegSelType();
    PrintResultsReportFollowUpPosSelType printResultsReportFollowUpPosSelType = new PrintResultsReportFollowUpPosSelType();
    PrintResultsReportFollowUpNegAll printResultsReportFollowUpNegAll = new PrintResultsReportFollowUpNegAll();
    PrintResultsReportFollowUpPosAll printResultsReportFollowUpPosAll = new PrintResultsReportFollowUpPosAll();
    PrintResultsReportFollowUpAllResSelType printResultsReportFollowUpAllResSelType = new PrintResultsReportFollowUpAllResSelType();
    PrintResultsReportFollowUpAllResAllSel printResultsReportFollowUpAllResAllSel = new PrintResultsReportFollowUpAllResAllSel();
    PrintRegisterReport printRegisterReport = new PrintRegisterReport();
    PrintRegisterReportAllType printRegisterReportAllType = new PrintRegisterReportAllType();
    FetchAllRefFacility fetchAllRefFacility = new FetchAllRefFacility();
    PrintRegisterReportByFac printRegisterReportByFac = new PrintRegisterReportByFac();
    PrintReportByAgeGroup printReportByAgeGroup = new PrintReportByAgeGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMonthToCb();
        addRefFacToCB();
    }

    public void getUserNo(String name, String userNo) {
        lb_name.setText(name);
        lb_userNo.setText(userNo);
    }

    public void addRefFacToCB() {
        refFacData.clear();
        fetchAllRefFacility.fetchRefFac(refFacData);
        cb_typeMRBF.setItems(refFacData);
    }

    public void addMonthToCb() {
        ObservableList<String> monthData = FXCollections.observableArrayList();
        monthData.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        cb_month.setItems(monthData);
        cb_monthMR.setItems(monthData);
        cb_monthMRBF.setItems(monthData);
        cb_monthReportByAge.setItems(monthData);

        ObservableList<String> yearData = FXCollections.observableArrayList();
        yearData.addAll("2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035");
        cb_year.setItems(yearData);
        cb_yearly_year.setItems(yearData);
        cb_yearMR.setItems(yearData);
        cb_yearMRBF.setItems(yearData);
        cb_yearReportByAge.setItems(yearData);

        ObservableList<String> typeData = FXCollections.observableArrayList();
        typeData.addAll("Spokes", "Hub", "All");
        cb_type.setItems(typeData);
        cb_yearly_type.setItems(typeData);
        cb_typeNC.setItems(typeData);
        cb_typeFU.setItems(typeData);
        cb_typeMR.setItems(typeData);

        ObservableList<String> resultData = FXCollections.observableArrayList();
        resultData.addAll("Neg", "Pos", "All");
        cb_resultNC.setItems(resultData);
        cb_resultFU.setItems(resultData);
    }

    @FXML
    public void saveMonthlyReportXpertToPDF(MouseEvent event) {
        if (cb_month.getValue() == null || cb_month.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_month);
            new Shake(cb_month).play();
        } else if (cb_year.getValue() == null || cb_year.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_year);
            new Shake(cb_year).play();
        } else if (cb_type.getValue() == null || cb_type.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_type);
            new Shake(cb_type).play();
        } else {
            if (cb_type.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String month = cb_month.getValue();
                                String year = cb_year.getValue();
                                String type = cb_type.getValue();
                                printMonthlyGeneExpertReportAll.savePDFReportForm(type, year, month, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_type.getValue().equals("Spokes")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String month = cb_month.getValue();
                                String year = cb_year.getValue();
                                String type = cb_type.getValue();
                                printMonthlyGeneExpertReportSpokes.savePDFReportForm(type, year, month, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_type.getValue().equals("Hub")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String month = cb_month.getValue();
                                String year = cb_year.getValue();
                                String type = cb_type.getValue();
                                printMonthlyGeneExpertReportHub.savePDFReportForm(type, year, month, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            }

        }
    }

    @FXML
    public void saveYearlyReportXpertToPDF(MouseEvent event) {
        if (cb_yearly_year.getValue() == null || cb_yearly_year.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_yearly_year);
            new Shake(cb_yearly_year).play();
        } else if (cb_yearly_type.getValue() == null || cb_yearly_type.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_yearly_type);
            new Shake(cb_yearly_type).play();
        } else {
            if (cb_yearly_type.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String year = cb_yearly_year.getValue();
                                String type = cb_yearly_type.getValue();
                                printYearlyGeneExpertReportAll.savePDFReportForm(type, year, "", printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_yearly_type.getValue().equals("Spokes")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String year = cb_yearly_year.getValue();
                                String type = cb_yearly_type.getValue();
                                printYearlyGeneExpertReportSpokes.savePDFReportForm(type, year, "", printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_yearly_type.getValue().equals("Hub")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String year = cb_yearly_year.getValue();
                                String type = cb_yearly_type.getValue();
                                printYearlyGeneExpertReportHub.savePDFReportForm(type, year, "", printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            }

        }
    }

    @FXML
    public void printReportNewCases(MouseEvent event) {
        if (cb_resultNC.getValue() == null || cb_resultNC.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_resultNC);
            new Shake(cb_resultNC).play();
        } else if (dp_fromNC.getValue() == null) {
            setRedNode.setRedDP(dp_fromNC);
            new Shake(dp_fromNC).play();
        } else if (dp_toNC.getValue() == null) {
            setRedNode.setRedDP(dp_toNC);
            new Shake(dp_toNC).play();
        } else if (cb_typeNC.getValue() == null || cb_typeNC.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_typeNC);
            new Shake(cb_typeNC).play();
        } else {
            String result = cb_resultNC.getValue();
            String from = String.valueOf(dp_fromNC.getValue());
            String to = String.valueOf(dp_toNC.getValue());
            String type = cb_typeNC.getValue();
            if (!cb_resultNC.getValue().equals("All") && !cb_typeNC.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReport.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultNC.getValue().equals("All") && !cb_typeNC.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportAllSelType.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultNC.getValue().equals("All") && cb_typeNC.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportAllResAllType.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            }
        }
    }

    @FXML
    public void printFollowUpReport(MouseEvent event) {
        if (cb_resultFU.getValue() == null || cb_resultFU.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_resultFU);
            new Shake(cb_resultFU).play();
        } else if (dp_fromFU.getValue() == null) {
            setRedNode.setRedDP(dp_fromFU);
            new Shake(dp_fromFU).play();
        } else if (dp_toFU.getValue() == null) {
            setRedNode.setRedDP(dp_toFU);
            new Shake(dp_toFU).play();
        } else if (cb_typeFU.getValue() == null || cb_typeFU.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_typeFU);
            new Shake(cb_typeFU).play();
        } else {
            String result = cb_resultFU.getValue();
            String from = String.valueOf(dp_fromFU.getValue());
            String to = String.valueOf(dp_toFU.getValue());
            String type = cb_typeFU.getValue();
            if (cb_resultFU.getValue().equals("Neg") && !cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpNegSelType.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultFU.getValue().equals("Pos") && !cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpPosSelType.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultFU.getValue().equals("Pos") && cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpPosAll.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultFU.getValue().equals("Neg") && cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpNegAll.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultFU.getValue().equals("All") && !cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpAllResSelType.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (cb_resultFU.getValue().equals("All") && cb_typeFU.getValue().equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printResultsReportFollowUpAllResAllSel.savePDFReportForm(result, type, from, to, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            }
        }
    }

    @FXML
    public void printRegisterReport(MouseEvent event) {
        if (cb_monthMR.getValue() == null || cb_monthMR.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_monthMR);
            new Shake(cb_monthMR).play();
        } else if (cb_yearMR.getValue() == null || cb_yearMR.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_yearMR);
            new Shake(cb_yearMR).play();
        } else if (cb_typeMR.getValue() == null || cb_typeMR.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_typeMR);
            new Shake(cb_typeMR).play();
        } else {
            String month = cb_monthMR.getValue();
            String year = cb_yearMR.getValue();
            String type = cb_typeMR.getValue();
            if (!type.equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printRegisterReport.savePDFReportForm(year, month, type, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            } else if (type.equals("All")) {
                PauseTransition pst = new PauseTransition(Duration.seconds(1));
                FileChooser chooser = new FileChooser();
                PdfDocument doc = new PdfDocument();
                //chooser.setInitialDirectory(new File("C:\\Users"));
                Window stage = btn_xPertExportMonth.getScene().getWindow();
                chooser.setTitle("save file");
                chooser.setInitialFileName("save");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
                File file = chooser.showSaveDialog(stage);
                //chooser.setInitialDirectory(file.getParentFile());
                String path = file.getAbsolutePath();
                if (file != null) {
                    pst.setOnFinished(e -> {
                        String printDate = String.valueOf(LocalDate.now());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                printRegisterReportAllType.savePDFReportForm(year, month, type, printDate, path);
                            }
                        }).start();
                    });
                    pst.play();
                }
            }
        }
    }
    @FXML
    public void printReportByFac(MouseEvent event) {
        if (cb_monthMRBF.getValue() == null || cb_monthMRBF.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_monthMRBF);
            new Shake(cb_monthMRBF).play();
        } else if (cb_yearMRBF.getValue() == null || cb_yearMRBF.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_yearMRBF);
            new Shake(cb_yearMRBF).play();
        } else if (cb_typeMRBF.getValue() == null || cb_typeMRBF.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_typeMRBF);
            new Shake(cb_typeMRBF).play();
        } else {
            String month = cb_monthMRBF.getValue();
            String year = cb_yearMRBF.getValue();
            String fac = cb_typeMRBF.getValue();
            PauseTransition pst = new PauseTransition(Duration.seconds(1));
            FileChooser chooser = new FileChooser();
            PdfDocument doc = new PdfDocument();
            //chooser.setInitialDirectory(new File("C:\\Users"));
            Window stage = btn_xPertExportMonth.getScene().getWindow();
            chooser.setTitle("save file");
            chooser.setInitialFileName("save");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
            File file = chooser.showSaveDialog(stage);
            //chooser.setInitialDirectory(file.getParentFile());
            String path = file.getAbsolutePath();
            if (file != null) {
                pst.setOnFinished(e -> {
                    String printDate = String.valueOf(LocalDate.now());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            printRegisterReportByFac.savePDFReportForm(year, month, fac, printDate, path);
                        }
                    }).start();
                });
                pst.play();
            }
        }
    }
    @FXML
    public void printReportByAgeGroup(MouseEvent event) {
        if (cb_monthReportByAge.getValue() == null || cb_monthReportByAge.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_monthReportByAge);
            new Shake(cb_monthReportByAge).play();
        } else if (cb_yearReportByAge.getValue() == null || cb_yearReportByAge.getValue().isEmpty()) {
            setRedNode.setRedCB(cb_yearReportByAge);
            new Shake(cb_yearReportByAge).play();
        } else {
            String month = cb_monthReportByAge.getValue();
            String year = cb_yearReportByAge.getValue();
            PauseTransition pst = new PauseTransition(Duration.seconds(1));
            FileChooser chooser = new FileChooser();
            PdfDocument doc = new PdfDocument();
            //chooser.setInitialDirectory(new File("C:\\Users"));
            Window stage = btn_xPertExportMonth.getScene().getWindow();
            chooser.setTitle("save file");
            chooser.setInitialFileName("save");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
            File file = chooser.showSaveDialog(stage);
            //chooser.setInitialDirectory(file.getParentFile());
            String path = file.getAbsolutePath();
            if (file != null) {
                pst.setOnFinished(e -> {
                    String printDate = String.valueOf(LocalDate.now());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            printReportByAgeGroup.savePDFReportForm(year, month, printDate, path);
                        }
                    }).start();
                });
                pst.play();
            }
        }
    }
}

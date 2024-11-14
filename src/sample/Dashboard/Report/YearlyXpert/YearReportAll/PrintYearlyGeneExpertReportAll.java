package sample.Dashboard.Report.YearlyXpert.YearReportAll;

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

public class PrintYearlyGeneExpertReportAll {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String RESULTS_QUERY = "SELECT  " +
            "ttp_jan.indicator, " +
            "ttp_jan.male_count_jan,  " +
            "ttp_jan.female_count_jan,  " +
            "ttp_feb.male_count_feb,  " +
            "ttp_feb.female_count_feb,  " +
            "ttp_mar.male_count_mar,  " +
            "ttp_mar.female_count_mar, " +
            "ttp_apr.male_count_apr,  " +
            "ttp_apr.female_count_apr, " +
            "ttp_may.male_count_may,  " +
            "ttp_may.female_count_may, " +
            "ttp_jun.male_count_jun, " +
            "ttp_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_feb " +
            " " +
            "ON ttp_jan.indicator = ttp_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_mar " +
            " " +
            "ON ttp_jan.indicator = ttp_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_apr " +
            "ON ttp_jan.indicator = ttp_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_may " +
            "ON ttp_jan.indicator = ttp_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed' AS indicator, " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_jun " +
            "ON ttp_jan.indicator = ttp_jun.indicator  " +
            " " +
            "UNION " +
            " " +
            "SELECT  " +
            "ttpnp_jan.indicator, " +
            "ttpnp_jan.male_count_jan,  " +
            "ttpnp_jan.female_count_jan,  " +
            "ttpnp_feb.male_count_feb,  " +
            "ttpnp_feb.female_count_feb,  " +
            "ttpnp_mar.male_count_mar,  " +
            "ttpnp_mar.female_count_mar, " +
            "ttpnp_apr.male_count_apr,  " +
            "ttpnp_apr.female_count_apr, " +
            "ttpnp_may.male_count_may,  " +
            "ttpnp_may.female_count_may, " +
            "ttpnp_jun.male_count_jun, " +
            "ttpnp_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_feb " +
            " " +
            "ON ttpnp_jan.indicator = ttpnp_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_mar " +
            " " +
            "ON ttpnp_jan.indicator = ttpnp_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_apr " +
            "ON ttpnp_jan.indicator = ttpnp_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_may " +
            "ON ttpnp_jan.indicator = ttpnp_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed New Patient' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_jun " +
            "ON ttpnp_jan.indicator = ttpnp_jun.indicator  " +
            " " +
            "UNION  " +
            " " +
            "SELECT  " +
            "ttpfptp_jan.indicator, " +
            "ttpfptp_jan.male_count_jan,  " +
            "ttpfptp_jan.female_count_jan,  " +
            "ttpfptp_feb.male_count_feb,  " +
            "ttpfptp_feb.female_count_feb,  " +
            "ttpfptp_mar.male_count_mar,  " +
            "ttpfptp_mar.female_count_mar, " +
            "ttpfptp_apr.male_count_apr,  " +
            "ttpfptp_apr.female_count_apr, " +
            "ttpfptp_may.male_count_may,  " +
            "ttpfptp_may.female_count_may, " +
            "ttpfptp_jun.male_count_jun, " +
            "ttpfptp_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_feb " +
            " " +
            "ON ttpfptp_jan.indicator = ttpfptp_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_mar " +
            " " +
            "ON ttpfptp_jan.indicator = ttpfptp_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_apr " +
            "ON ttpfptp_jan.indicator = ttpfptp_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_may " +
            "ON ttpfptp_jan.indicator = ttpfptp_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_jun " +
            "ON ttpfptp_jan.indicator = ttpfptp_jun.indicator  " +
            " " +
            "UNION " +
            " " +
            "SELECT  " +
            "mtbnd_jan.indicator, " +
            "mtbnd_jan.male_count_jan,  " +
            "mtbnd_jan.female_count_jan,  " +
            "mtbnd_feb.male_count_feb,  " +
            "mtbnd_feb.female_count_feb,  " +
            "mtbnd_mar.male_count_mar,  " +
            "mtbnd_mar.female_count_mar, " +
            "mtbnd_apr.male_count_apr,  " +
            "mtbnd_apr.female_count_apr, " +
            "mtbnd_may.male_count_may,  " +
            "mtbnd_may.female_count_may, " +
            "mtbnd_jun.male_count_jun, " +
            "mtbnd_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_feb " +
            " " +
            "ON mtbnd_jan.indicator = mtbnd_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_mar " +
            " " +
            "ON mtbnd_jan.indicator = mtbnd_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_apr " +
            "ON mtbnd_jan.indicator = mtbnd_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_may " +
            "ON mtbnd_jan.indicator = mtbnd_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_jun " +
            "ON mtbnd_jan.indicator = mtbnd_jun.indicator  " +
            " " +
            "UNION " +
            " " +
            "SELECT  " +
            "mtbdrifnd_jan.indicator, " +
            "mtbdrifnd_jan.male_count_jan,  " +
            "mtbdrifnd_jan.female_count_jan,  " +
            "mtbdrifnd_feb.male_count_feb,  " +
            "mtbdrifnd_feb.female_count_feb,  " +
            "mtbdrifnd_mar.male_count_mar,  " +
            "mtbdrifnd_mar.female_count_mar, " +
            "mtbdrifnd_apr.male_count_apr,  " +
            "mtbdrifnd_apr.female_count_apr, " +
            "mtbdrifnd_may.male_count_may,  " +
            "mtbdrifnd_may.female_count_may, " +
            "mtbdrifnd_jun.male_count_jun, " +
            "mtbdrifnd_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_feb " +
            " " +
            "ON mtbdrifnd_jan.indicator = mtbdrifnd_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_mar " +
            " " +
            "ON mtbdrifnd_jan.indicator = mtbdrifnd_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_apr " +
            "ON mtbdrifnd_jan.indicator = mtbdrifnd_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_may " +
            "ON mtbdrifnd_jan.indicator = mtbdrifnd_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_jun " +
            "ON mtbdrifnd_jan.indicator = mtbdrifnd_jun.indicator  " +
            " " +
            "UNION " +
            " " +
            "SELECT  " +
            "mtbdrifd_jan.indicator, " +
            "mtbdrifd_jan.male_count_jan,  " +
            "mtbdrifd_jan.female_count_jan,  " +
            "mtbdrifd_feb.male_count_feb,  " +
            "mtbdrifd_feb.female_count_feb,  " +
            "mtbdrifd_mar.male_count_mar,  " +
            "mtbdrifd_mar.female_count_mar, " +
            "mtbdrifd_apr.male_count_apr,  " +
            "mtbdrifd_apr.female_count_apr, " +
            "mtbdrifd_may.male_count_may,  " +
            "mtbdrifd_may.female_count_may, " +
            "mtbdrifd_jun.male_count_jun, " +
            "mtbdrifd_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_feb " +
            " " +
            "ON mtbdrifd_jan.indicator = mtbdrifd_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_mar " +
            " " +
            "ON mtbdrifd_jan.indicator = mtbdrifd_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_apr " +
            "ON mtbdrifd_jan.indicator = mtbdrifd_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_may " +
            "ON mtbdrifd_jan.indicator = mtbdrifd_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_jun " +
            "ON mtbdrifd_jan.indicator = mtbdrifd_jun.indicator  " +
            " " +
            "UNION " +
            " " +
            "SELECT  " +
            "mtbdrifi_jan.indicator, " +
            "mtbdrifi_jan.male_count_jan,  " +
            "mtbdrifi_jan.female_count_jan,  " +
            "mtbdrifi_feb.male_count_feb,  " +
            "mtbdrifi_feb.female_count_feb,  " +
            "mtbdrifi_mar.male_count_mar,  " +
            "mtbdrifi_mar.female_count_mar, " +
            "mtbdrifi_apr.male_count_apr,  " +
            "mtbdrifi_apr.female_count_apr, " +
            "mtbdrifi_may.male_count_may,  " +
            "mtbdrifi_may.female_count_may, " +
            "mtbdrifi_jun.male_count_jun, " +
            "mtbdrifi_jun.female_count_jun  " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_feb " +
            " " +
            "ON mtbdrifi_jan.indicator = mtbdrifi_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_mar " +
            " " +
            "ON mtbdrifi_jan.indicator = mtbdrifi_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_apr " +
            "ON mtbdrifi_jan.indicator = mtbdrifi_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_may " +
            "ON mtbdrifi_jan.indicator = mtbdrifi_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_jun " +
            "ON mtbdrifi_jan.indicator = mtbdrifi_jun.indicator  " +
            " " +
            "UNION  " +
            " " +
            "SELECT  " +
            "err_jan.indicator, " +
            "err_jan.male_count_jan,  " +
            "err_jan.female_count_jan,  " +
            "err_feb.male_count_feb,  " +
            "err_feb.female_count_feb,  " +
            "err_mar.male_count_mar,  " +
            "err_mar.female_count_mar, " +
            "err_apr.male_count_apr,  " +
            "err_apr.female_count_apr, " +
            "err_may.male_count_may,  " +
            "err_may.female_count_may, " +
            "err_jun.male_count_jun, " +
            "err_jun.female_count_jun  " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_feb " +
            " " +
            "ON err_jan.indicator = err_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_mar " +
            " " +
            "ON err_jan.indicator = err_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_apr " +
            "ON err_jan.indicator = err_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_may " +
            "ON err_jan.indicator = err_may.indicator  " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Errors' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_jun " +
            "ON err_jan.indicator = err_jun.indicator  " +
            " " +
            "UNION  " +
            " " +
            "SELECT  " +
            "irr_jan.indicator, " +
            "irr_jan.male_count_jan,  " +
            "irr_jan.female_count_jan,  " +
            "irr_feb.male_count_feb,  " +
            "irr_feb.female_count_feb,  " +
            "irr_mar.male_count_mar,  " +
            "irr_mar.female_count_mar, " +
            "irr_apr.male_count_apr,  " +
            "irr_apr.female_count_apr, " +
            "irr_may.male_count_may,  " +
            "irr_may.female_count_may, " +
            "irr_jun.male_count_jun, " +
            "irr_jun.female_count_jun  " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_feb " +
            " " +
            "ON irr_jan.indicator = irr_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_mar " +
            " " +
            "ON irr_jan.indicator = irr_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_apr " +
            "ON irr_jan.indicator = irr_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_may " +
            "ON irr_jan.indicator = irr_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'Invalid results' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_jun " +
            "ON irr_jan.indicator = irr_jun.indicator  " +
            " " +
            "UNION  " +
            " " +
            "SELECT  " +
            "nrrfc_jan.indicator, " +
            "nrrfc_jan.male_count_jan,  " +
            "nrrfc_jan.female_count_jan,  " +
            "nrrfc_feb.male_count_feb,  " +
            "nrrfc_feb.female_count_feb,  " +
            "nrrfc_mar.male_count_mar,  " +
            "nrrfc_mar.female_count_mar, " +
            "nrrfc_apr.male_count_apr,  " +
            "nrrfc_apr.female_count_apr, " +
            "nrrfc_may.male_count_may,  " +
            "nrrfc_may.female_count_may, " +
            "nrrfc_jun.male_count_jun, " +
            "nrrfc_jun.female_count_jun " +
            " " +
            "FROM " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_jan, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_jan " +
            "FROM request_list WHERE MONTH(examined_date) = '01' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_jan " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_feb, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_feb " +
            "FROM request_list WHERE MONTH(examined_date) = '02' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_feb " +
            " " +
            "ON nrrfc_jan.indicator = nrrfc_feb.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_mar, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_mar " +
            "FROM request_list WHERE MONTH(examined_date) = '03' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_mar " +
            " " +
            "ON nrrfc_jan.indicator = nrrfc_mar.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_apr, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_apr " +
            "FROM request_list WHERE MONTH(examined_date) = '04' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_apr " +
            "ON nrrfc_jan.indicator = nrrfc_apr.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_may, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_may " +
            "FROM request_list WHERE MONTH(examined_date) = '05' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_may " +
            "ON nrrfc_jan.indicator = nrrfc_may.indicator  " +
            " " +
            "LEFT JOIN " +
            " " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_jun, " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_jun " +
            "FROM request_list WHERE MONTH(examined_date) = '06' AND YEAR(examined_date) = ? AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_jun " +
            "ON nrrfc_jan.indicator = nrrfc_jun.indicator";


    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    YearXpertReportPage2All yearXpertReportPage2All = new YearXpertReportPage2All();
    YearCartridgeReportAll yearCartridgeReportAll = new YearCartridgeReportAll();

    public void printReportForm(String ward, String year,String month, String datePrinted) {
        //margin
        PdfDocument doc = new PdfDocument();
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(margin.getTop());
        margin.setLeft(unitCvtr.convertUnits(0.5f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(margin.getLeft());
        addHeaderAndFooter(doc, PdfPageSize.A4, margin, ward, year, month, datePrinted);
        PdfPageBase page = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        PdfPageBase page2 = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        addDataToTable(page, ward, year, month);
        yearXpertReportPage2All.addDataToTable(page2, "", year, "");
        //draw watermarks
        String waterMark = "VERIFIED COPY";
        addWaterMark(page, waterMark);
        doc.saveToFile("C:/Users/animb/IdeaProjects/Vibro/output/monthlyWardReportForm.pdf");

    }
    public void savePDFReportForm(String ward, String year, String month, String datePrinted, String filePath) {
        //margin
        PdfDocument doc = new PdfDocument();
        PdfUnitConvertor unitCvtr = new PdfUnitConvertor();
        PdfMargins margin = new PdfMargins();
        margin.setTop(unitCvtr.convertUnits(1.2f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setBottom(margin.getTop());
        margin.setLeft(unitCvtr.convertUnits(0.3f, PdfGraphicsUnit.Centimeter, PdfGraphicsUnit.Point));
        margin.setRight(margin.getLeft());
        addHeaderAndFooter(doc, PdfPageSize.A3, margin, ward, year, month, datePrinted);
        PdfPageBase page = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        PdfPageBase page2 = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        PdfPageBase page3 = doc.getPages().add(PdfPageSize.A4, margin, PdfPageRotateAngle.Rotate_Angle_0, PdfPageOrientation.Landscape);
        addDataToTable(page, ward, year, month);
        yearXpertReportPage2All.addDataToTable(page2, "", year, "");
        yearCartridgeReportAll.addDataToTable(page3, "", year, "");


        //draw watermarks
        String waterMark = "VERIFIED COPY";
        addWaterMark(page, waterMark);
        doc.saveToFile(filePath, FileFormat.PDF);

    }

    public void addHeaderAndFooter(PdfDocument doc, Dimension2D pageSize, PdfMargins margin, String ward, String year, String month, String datePrinted) {
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
        String wardName = "Hub and Spokes";
        String dateRange = "Year: "+ year;
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
    public void addDataToTable(PdfPageBase page, String ward,  String year, String month) {
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
                preparedStatement.setString(8, year);
                preparedStatement.setString(9, year);
                preparedStatement.setString(10, year);
                preparedStatement.setString(11, year);
                preparedStatement.setString(12, year);
                preparedStatement.setString(13, year);
                preparedStatement.setString(14, year);
                preparedStatement.setString(15, year);
                preparedStatement.setString(16, year);
                preparedStatement.setString(17, year);
                preparedStatement.setString(18, year);
                preparedStatement.setString(19, year);
                preparedStatement.setString(20, year);
                preparedStatement.setString(21, year);
                preparedStatement.setString(22, year);
                preparedStatement.setString(23, year);
                preparedStatement.setString(24, year);
                preparedStatement.setString(25, year);
                preparedStatement.setString(26, year);
                preparedStatement.setString(27, year);
                preparedStatement.setString(28, year);
                preparedStatement.setString(29, year);
                preparedStatement.setString(30, year);
                preparedStatement.setString(31, year);
                preparedStatement.setString(32, year);
                preparedStatement.setString(33, year);
                preparedStatement.setString(34, year);
                preparedStatement.setString(35, year);
                preparedStatement.setString(36, year);
                preparedStatement.setString(37, year);
                preparedStatement.setString(38, year);
                preparedStatement.setString(39, year);
                preparedStatement.setString(40, year);
                preparedStatement.setString(41, year);
                preparedStatement.setString(42, year);
                preparedStatement.setString(43, year);
                preparedStatement.setString(44, year);
                preparedStatement.setString(45, year);
                preparedStatement.setString(46, year);
                preparedStatement.setString(47, year);
                preparedStatement.setString(48, year);
                preparedStatement.setString(49, year);
                preparedStatement.setString(50, year);
                preparedStatement.setString(51, year);
                preparedStatement.setString(52, year);
                preparedStatement.setString(53, year);
                preparedStatement.setString(54, year);
                preparedStatement.setString(55, year);
                preparedStatement.setString(56, year);
                preparedStatement.setString(57, year);
                preparedStatement.setString(58, year);
                preparedStatement.setString(59, year);
                preparedStatement.setString(60, year);
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
        table.getColumns().get(1).setWidth(width * 0.10f * width);
        table.getColumns().get(1).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(2).setWidth(width * 0.10f * width);
        table.getColumns().get(2).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(3).setWidth(width * 0.10f * width);
        table.getColumns().get(3).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(4).setWidth(width * 0.10f * width);
        table.getColumns().get(4).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(5).setWidth(width * 0.10f * width);
        table.getColumns().get(5).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(6).setWidth(width * 0.10f * width);
        table.getColumns().get(6).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(7).setWidth(width * 0.10f * width);
        table.getColumns().get(7).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(8).setWidth(width * 0.10f * width);
        table.getColumns().get(8).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(9).setWidth(width * 0.10f * width);
        table.getColumns().get(9).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(10).setWidth(width * 0.10f * width);
        table.getColumns().get(10).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(11).setWidth(width * 0.10f * width);
        table.getColumns().get(11).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getColumns().get(12).setWidth(width * 0.10f * width);
        table.getColumns().get(12).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center, PdfVerticalAlignment.Middle));
        table.getStyle().setShowHeader(true);
        table.getColumns().get(0).setColumnName("Indicator");
        table.getColumns().get(1).setColumnName("M_Jan");
        table.getColumns().get(2).setColumnName("F_Jan");
        table.getColumns().get(3).setColumnName("M_Feb");
        table.getColumns().get(4).setColumnName("F_Feb");
        table.getColumns().get(5).setColumnName("M_Mar");
        table.getColumns().get(6).setColumnName("F_Mar");
        table.getColumns().get(7).setColumnName("M_Apr");
        table.getColumns().get(8).setColumnName("F_Apr");
        table.getColumns().get(9).setColumnName("M_May");
        table.getColumns().get(10).setColumnName("F_May");
        table.getColumns().get(11).setColumnName("M_Jun");
        table.getColumns().get(12).setColumnName("F_Jun");
        //draw investigation title before table
        float y = 10;
        PdfLayoutResult result = table.draw(page, new Point2D.Float(0, y));
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


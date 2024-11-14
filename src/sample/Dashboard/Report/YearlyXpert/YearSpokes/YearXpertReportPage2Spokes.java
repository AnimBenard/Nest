package sample.Dashboard.Report.YearlyXpert.YearSpokes;

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

public class YearXpertReportPage2Spokes {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Limbobowrn-1311";
    private static final String DB_PATH = "jdbc:mysql://localhost:3306/easy_tbd";
    private static final String CLASSPATH = "com.mysql.cj.jdbc.Driver";
    private static final String RESULTS_QUERY = "SELECT      " +
            "ttp_jul.indicator,     " +
            "ttp_jul.male_count_jul,     " +
            "ttp_jul.female_count_jul,     " +
            "ttp_aug.male_count_aug,     " +
            "ttp_aug.female_count_aug,     " +
            "ttp_sep.male_count_sep,     " +
            "ttp_sep.female_count_sep,     " +
            "ttp_oct.male_count_oct,     " +
            "ttp_oct.female_count_oct,     " +
            "ttp_nov.male_count_nov,     " +
            "ttp_nov.female_count_nov,     " +
            "ttp_dec.male_count_dec,     " +
            "ttp_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_jul     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_aug     " +
            "ON ttp_jul.indicator = ttp_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_sep     " +
            "ON ttp_jul.indicator = ttp_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_oct     " +
            "ON ttp_jul.indicator = ttp_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_nov     " +
            "ON ttp_jul.indicator = ttp_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed' AS indicator,     " +
            "    COUNT(CASE WHEN sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS ttp_dec     " +
            "ON ttp_jul.indicator = ttp_dec.indicator     " +
            "     " +
            "UNION     " +
            "     " +
            "SELECT      " +
            "ttpnp_jul.indicator,     " +
            "ttpnp_jul.male_count_jul,     " +
            "ttpnp_jul.female_count_jul,     " +
            "ttpnp_aug.male_count_aug,     " +
            "ttpnp_aug.female_count_aug,     " +
            "ttpnp_sep.male_count_sep,     " +
            "ttpnp_sep.female_count_sep,     " +
            "ttpnp_oct.male_count_oct,     " +
            "ttpnp_oct.female_count_oct,     " +
            "ttpnp_nov.male_count_nov,     " +
            "ttpnp_nov.female_count_nov,     " +
            "ttpnp_dec.male_count_dec,     " +
            "ttpnp_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_aug     " +
            "ON ttpnp_jul.indicator = ttpnp_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_sep     " +
            "ON ttpnp_jul.indicator = ttpnp_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_oct     " +
            "ON ttpnp_jul.indicator = ttpnp_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_nov     " +
            "ON ttpnp_jul.indicator = ttpnp_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed New Patient' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS ttpnp_dec     " +
            "ON ttpnp_jul.indicator = ttpnp_dec.indicator     " +
            "     " +
            "UNION      " +
            "     " +
            "SELECT      " +
            "ttpfptp_jul.indicator,     " +
            "ttpfptp_jul.male_count_jul,     " +
            "ttpfptp_jul.female_count_jul,     " +
            "ttpfptp_aug.male_count_aug,     " +
            "ttpfptp_aug.female_count_aug,     " +
            "ttpfptp_sep.male_count_sep,     " +
            "ttpfptp_sep.female_count_sep,     " +
            "ttpfptp_oct.male_count_oct,     " +
            "ttpfptp_oct.female_count_oct,     " +
            "ttpfptp_nov.male_count_nov,     " +
            "ttpfptp_nov.female_count_nov,     " +
            "ttpfptp_dec.male_count_dec,     " +
            "ttpfptp_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_aug     " +
            "ON ttpfptp_jul.indicator = ttpfptp_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_sep     " +
            "ON ttpfptp_jul.indicator = ttpfptp_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_oct     " +
            "ON ttpfptp_jul.indicator = ttpfptp_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_nov     " +
            "ON ttpfptp_jul.indicator = ttpfptp_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Total Test Performed for Preiously Treated Patients (Failures, relapse return after default)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'Drug Resistant' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'Drug Resistant') AS ttpfptp_dec     " +
            "ON ttpfptp_jul.indicator = ttpfptp_dec.indicator     " +
            "     " +
            "UNION     " +
            "     " +
            "SELECT      " +
            "mtbnd_jul.indicator,     " +
            "mtbnd_jul.male_count_jul,     " +
            "mtbnd_jul.female_count_jul,     " +
            "mtbnd_aug.male_count_aug,     " +
            "mtbnd_aug.female_count_aug,     " +
            "mtbnd_sep.male_count_sep,     " +
            "mtbnd_sep.female_count_sep,     " +
            "mtbnd_oct.male_count_oct,     " +
            "mtbnd_oct.female_count_oct,     " +
            "mtbnd_nov.male_count_nov,     " +
            "mtbnd_nov.female_count_nov,     " +
            "mtbnd_dec.male_count_dec,     " +
            "mtbnd_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_aug     " +
            "ON mtbnd_jul.indicator = mtbnd_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_sep     " +
            "ON mtbnd_jul.indicator = mtbnd_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_oct     " +
            "ON mtbnd_jul.indicator = mtbnd_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_nov     " +
            "ON mtbnd_jul.indicator = mtbnd_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Not Detected (Negative)' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination = 'TB Diagnosis' AND results = 'Neg' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination = 'TB Diagnosis') AS mtbnd_dec     " +
            "ON mtbnd_jul.indicator = mtbnd_dec.indicator     " +
            "     " +
            "UNION     " +
            "     " +
            "SELECT      " +
            "mtbdrifnd_jul.indicator,     " +
            "mtbdrifnd_jul.male_count_jul,     " +
            "mtbdrifnd_jul.female_count_jul,     " +
            "mtbdrifnd_aug.male_count_aug,     " +
            "mtbdrifnd_aug.female_count_aug,     " +
            "mtbdrifnd_sep.male_count_sep,     " +
            "mtbdrifnd_sep.female_count_sep,     " +
            "mtbdrifnd_oct.male_count_oct,     " +
            "mtbdrifnd_oct.female_count_oct,     " +
            "mtbdrifnd_nov.male_count_nov,     " +
            "mtbdrifnd_nov.female_count_nov,     " +
            "mtbdrifnd_dec.male_count_dec,     " +
            "mtbdrifnd_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_aug     " +
            "ON mtbdrifnd_jul.indicator = mtbdrifnd_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_sep     " +
            "ON mtbdrifnd_jul.indicator = mtbdrifnd_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_oct     " +
            "ON mtbdrifnd_jul.indicator = mtbdrifnd_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_nov     " +
            "ON mtbdrifnd_jul.indicator = mtbdrifnd_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance not Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RS' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifnd_dec     " +
            "ON mtbdrifnd_jul.indicator = mtbdrifnd_dec.indicator     " +
            "     " +
            "UNION     " +
            "     " +
            "SELECT      " +
            "mtbdrifd_jul.indicator,     " +
            "mtbdrifd_jul.male_count_jul,     " +
            "mtbdrifd_jul.female_count_jul,     " +
            "mtbdrifd_aug.male_count_aug,     " +
            "mtbdrifd_aug.female_count_aug,     " +
            "mtbdrifd_sep.male_count_sep,     " +
            "mtbdrifd_sep.female_count_sep,     " +
            "mtbdrifd_oct.male_count_oct,     " +
            "mtbdrifd_oct.female_count_oct,     " +
            "mtbdrifd_nov.male_count_nov,     " +
            "mtbdrifd_nov.female_count_nov,     " +
            "mtbdrifd_dec.male_count_dec,     " +
            "mtbdrifd_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_aug     " +
            "ON mtbdrifd_jul.indicator = mtbdrifd_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_sep     " +
            "ON mtbdrifd_jul.indicator = mtbdrifd_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_oct     " +
            "ON mtbdrifd_jul.indicator = mtbdrifd_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_nov     " +
            "ON mtbdrifd_jul.indicator = mtbdrifd_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Detected' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RR' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifd_dec     " +
            "ON mtbdrifd_jul.indicator = mtbdrifd_dec.indicator     " +
            "     " +
            "UNION     " +
            "     " +
            "SELECT      " +
            "mtbdrifi_jul.indicator,     " +
            "mtbdrifi_jul.male_count_jul,     " +
            "mtbdrifi_jul.female_count_jul,     " +
            "mtbdrifi_aug.male_count_aug,     " +
            "mtbdrifi_aug.female_count_aug,     " +
            "mtbdrifi_sep.male_count_sep,     " +
            "mtbdrifi_sep.female_count_sep,     " +
            "mtbdrifi_oct.male_count_oct,     " +
            "mtbdrifi_oct.female_count_oct,     " +
            "mtbdrifi_nov.male_count_nov,     " +
            "mtbdrifi_nov.female_count_nov,     " +
            "mtbdrifi_dec.male_count_dec,     " +
            "mtbdrifi_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_aug     " +
            "ON mtbdrifi_jul.indicator = mtbdrifi_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_sep     " +
            "ON mtbdrifi_jul.indicator = mtbdrifi_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_oct     " +
            "ON mtbdrifi_jul.indicator = mtbdrifi_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_nov     " +
            "ON mtbdrifi_jul.indicator = mtbdrifi_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'MTB Detected Rifampicin Resistance Indeterminate' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Pos/RI' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS mtbdrifi_dec     " +
            "ON mtbdrifi_jul.indicator = mtbdrifi_dec.indicator     " +
            "     " +
            "UNION      " +
            "     " +
            "SELECT      " +
            "err_jul.indicator,     " +
            "err_jul.male_count_jul,     " +
            "err_jul.female_count_jul,     " +
            "err_aug.male_count_aug,     " +
            "err_aug.female_count_aug,     " +
            "err_sep.male_count_sep,     " +
            "err_sep.female_count_sep,     " +
            "err_oct.male_count_oct,     " +
            "err_oct.female_count_oct,     " +
            "err_nov.male_count_nov,     " +
            "err_nov.female_count_nov,     " +
            "err_dec.male_count_dec,     " +
            "err_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_aug     " +
            "ON err_jul.indicator = err_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_sep     " +
            "ON err_jul.indicator = err_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_oct     " +
            "ON err_jul.indicator = err_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_nov     " +
            "ON err_jul.indicator = err_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Errors' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Err' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS err_dec     " +
            "ON err_jul.indicator = err_dec.indicator      " +
            "     " +
            "UNION      " +
            "     " +
            "SELECT      " +
            "irr_jul.indicator,     " +
            "irr_jul.male_count_jul,     " +
            "irr_jul.female_count_jul,     " +
            "irr_aug.male_count_aug,     " +
            "irr_aug.female_count_aug,     " +
            "irr_sep.male_count_sep,     " +
            "irr_sep.female_count_sep,     " +
            "irr_oct.male_count_oct,     " +
            "irr_oct.female_count_oct,     " +
            "irr_nov.male_count_nov,     " +
            "irr_nov.female_count_nov,     " +
            "irr_dec.male_count_dec,     " +
            "irr_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_aug     " +
            "ON irr_jul.indicator = irr_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_sep     " +
            "ON irr_jul.indicator = irr_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_oct     " +
            "ON irr_jul.indicator = irr_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_nov     " +
            "ON irr_jul.indicator = irr_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'Invalid results' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'Inv' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS irr_dec     " +
            "ON irr_jul.indicator = irr_dec.indicator      " +
            "     " +
            "UNION      " +
            "     " +
            "SELECT      " +
            "nrrfc_jul.indicator,     " +
            "nrrfc_jul.male_count_jul,     " +
            "nrrfc_jul.female_count_jul,     " +
            "nrrfc_aug.male_count_aug,     " +
            "nrrfc_aug.female_count_aug,     " +
            "nrrfc_sep.male_count_sep,     " +
            "nrrfc_sep.female_count_sep,     " +
            "nrrfc_oct.male_count_oct,     " +
            "nrrfc_oct.female_count_oct,     " +
            "nrrfc_nov.male_count_nov,     " +
            "nrrfc_nov.female_count_nov,     " +
            "nrrfc_dec.male_count_dec,     " +
            "nrrfc_dec.female_count_dec     " +
            "     " +
            "FROM     " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_jul,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_jul     " +
            "FROM request_list WHERE MONTH(examined_date) = '07' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_jul     " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_aug,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_aug     " +
            "FROM request_list WHERE MONTH(examined_date) = '08' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_aug     " +
            "ON nrrfc_jul.indicator = nrrfc_aug.indicator       " +
            "     " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_sep,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_sep     " +
            "FROM request_list WHERE MONTH(examined_date) = '09' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_sep     " +
            "ON nrrfc_jul.indicator = nrrfc_sep.indicator      " +
            "LEFT JOIN      " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_oct,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_oct     " +
            "FROM request_list WHERE MONTH(examined_date) = '10' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_oct     " +
            "ON nrrfc_jul.indicator = nrrfc_oct.indicator      " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_nov,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_nov     " +
            "FROM request_list WHERE MONTH(examined_date) = '11' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_nov     " +
            "ON nrrfc_jul.indicator = nrrfc_nov.indicator      " +
            "     " +
            "LEFT JOIN     " +
            "     " +
            "(SELECT  'No of RIF Resistance referred for culture and DST' AS indicator,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'M' THEN 1 END) AS male_count_dec,     " +
            "    COUNT(CASE WHEN reason_for_examination IN ('TB Diagnosis', 'Drug Resistant') AND results = 'CULTURE' AND sex = 'F' THEN 1 END) AS female_count_dec     " +
            "     " +
            "FROM request_list WHERE MONTH(examined_date) = '12' AND YEAR(examined_date) = ? AND request_type = 'SPOKES' AND results IS NOT NULL AND reason_for_examination IN ('TB Diagnosis', 'Drug Resistant')) AS nrrfc_dec     " +
            "ON nrrfc_jul.indicator = nrrfc_dec.indicator ";

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

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
        table.getColumns().get(1).setColumnName("M_Jul");
        table.getColumns().get(2).setColumnName("F_Jul");
        table.getColumns().get(3).setColumnName("M_Aug");
        table.getColumns().get(4).setColumnName("F_Aug");
        table.getColumns().get(5).setColumnName("M_Sep");
        table.getColumns().get(6).setColumnName("F_Sep");
        table.getColumns().get(7).setColumnName("M_Oct");
        table.getColumns().get(8).setColumnName("F_Oct");
        table.getColumns().get(9).setColumnName("M_Nov");
        table.getColumns().get(10).setColumnName("F_Nov");
        table.getColumns().get(11).setColumnName("M_Dec");
        table.getColumns().get(12).setColumnName("F_Dec");
        //draw investigation title before table
        float y = 10;
        PdfLayoutResult result = table.draw(page, new Point2D.Float(0, y));
        //draw lab scientist label
        /*y = (float) result.getBounds().getHeight() + 130;
        addLabPersonnelLabel(page, y);*/
    }
}

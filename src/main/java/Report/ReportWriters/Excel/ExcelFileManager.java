package Report.ReportWriters.Excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A class responsible for managing Excel file operations, including opening, saving, and closing workbooks.
 */
public class ExcelFileManager {
    private static final String FILE_NAME = "test_results.xlsx";

    private Workbook workbook;
    private File file;

    /**
     * Constructor to initialize the ExcelFileManager and load or create the Excel workbook.
     */
    public ExcelFileManager() {
        file = new File(FILE_NAME);
        initWorkbook();
    }

    /**
     * Initializes the workbook, loading it from an existing file if available or creating a new one.
     */
    private void initWorkbook() {
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            } catch (IOException e) {
                e.printStackTrace();
                workbook = new XSSFWorkbook(); // יצירת Workbook חדש במקרה של כשל
            }
        } else {
            workbook = new XSSFWorkbook(); // יצירת Workbook חדש
        }
    }
    /**
     * Retrieves the current workbook.
     *
     * @return The current workbook.
     */

    public Workbook getWorkbook() {
        return workbook;
    }

    /**
     * Retrieves an existing sheet or creates a new one if not found.
     *
     * @param sheetName The name of the sheet to retrieve or create.
     * @return The sheet corresponding to the provided name.
     */
    public Sheet getOrCreateSheet(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    /**
     * Saves the workbook to the specified file.
     */
    public void saveWorkbook() {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Closes the workbook and releases resources.
     */
    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




package Report.ReportWriters.Excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileManager {
    private static final String FILE_NAME = "test_results.xlsx";

    private Workbook workbook;
    private File file;

    public ExcelFileManager() {
        file = new File(FILE_NAME);
        initWorkbook();
    }

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

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getOrCreateSheet(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    public void saveWorkbook() {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


package Report.ReportWriters.Excel;

import org.apache.poi.ss.usermodel.*;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ExcelDataManager {
    private static final String SHEET_NAME = "Test Results";
    private static final int HEADER_ROW_INDEX = 0;

    private final ExcelFileManager fileManager;
    private Sheet sheet;
    private int currentRow;

    public ExcelDataManager(ExcelFileManager fileManager) {
        this.fileManager = fileManager;
        this.sheet = fileManager.getOrCreateSheet(SHEET_NAME);
        initSheet();
    }

    private void initSheet() {
        if (sheet.getLastRowNum() == 0) { // אם הקובץ חדש או שהטבלה ריקה
            createHeaders();
            currentRow = 1;
        } else {
            currentRow = sheet.getLastRowNum() + 1;
        }
    }

    private void createHeaders() {
        Row headerRow = sheet.createRow(HEADER_ROW_INDEX);
        headerRow.createCell(0).setCellValue("Test Name");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Message");
        headerRow.createCell(3).setCellValue("Date");
    }

    public void writeTestResult(String testName, String status, String message) {
        Row row = sheet.createRow(currentRow++);
        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(message);
        row.createCell(3).setCellValue(getCurrentDate());
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public void save() {
        fileManager.saveWorkbook();
    }

    public void close() {
        fileManager.closeWorkbook();
    }
}

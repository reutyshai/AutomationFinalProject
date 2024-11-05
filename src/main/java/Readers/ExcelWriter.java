package Readers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelWriter {
    private static ExcelWriter instance;
    private Workbook workbook;
    private Sheet sheet;
    private int position = 0;

    private ExcelWriter() {
        workbook = new XSSFWorkbook(); // יצירת Workbook חדש
        sheet = workbook.createSheet("Test Results"); // יצירת גיליון חדש
        createHeaders(); // יצירת כותרות
    }

    public static synchronized ExcelWriter getInstance() {
        if (instance == null) {
            instance = new ExcelWriter();
        }
        return instance;
    }

    private void createHeaders() {
        Row headerRow = sheet.createRow(0); // יצירת שורה חדשה בכותרות
        headerRow.createCell(0).setCellValue("Test Name");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Massage");
        headerRow.createCell(3).setCellValue("Date");
    }

    public void writeData(Map<String, String> testResults) {
        Row row = sheet.createRow(++this.position); // יצירת שורה חדשה
        row.createCell(0).setCellValue(testResults.get("name"));
        row.createCell(1).setCellValue(testResults.get("status"));
        row.createCell(2).setCellValue(testResults.get("date"));
        row.createCell(3).setCellValue(testResults.getOrDefault("exception", ""));

        saveToFile("test_results.xlsx");
    }

    private void saveToFile(String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

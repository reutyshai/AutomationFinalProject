package Readers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelWriter {
    private static ExcelWriter instance;
    private Workbook workbook;
    private Sheet sheet;
    private int position = 0;

    private ExcelWriter() {
        setPositionToWrite();
    }

    public static synchronized ExcelWriter getInstance() {
        if (instance == null) {
            instance = new ExcelWriter();
        }
        return instance;
    }

    private void setPositionToWrite() {
        File file = new File("test_results.xlsx");
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheet("Test Results");
                position = sheet.getLastRowNum();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Test Results");
            createHeaders();
            position = 0;
        }
    }

    private void createHeaders() {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test Name");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Message");
        headerRow.createCell(3).setCellValue("Date");
    }

    public void writeData(Map<String, String> testResults) {
        Row row = sheet.createRow(++position);
        row.createCell(0).setCellValue(testResults.getOrDefault("Name", ""));
        row.createCell(1).setCellValue(testResults.getOrDefault("Status", ""));
        row.createCell(2).setCellValue(testResults.getOrDefault("Message", ""));
        row.createCell(3).setCellValue(testResults.getOrDefault("Date", ""));

        saveToFile("test_results.xlsx");
    }

    private void saveToFile(String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

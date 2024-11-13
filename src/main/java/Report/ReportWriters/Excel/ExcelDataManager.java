package Report.ReportWriters.Excel;

import Entities.Image;

import org.apache.poi.ss.usermodel.*;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A class responsible for managing Excel data, including creating and writing test results to a sheet.
 * It handles metadata, headers, and updates the current row for writing results.
 */
public class ExcelDataManager {
    private static final String SHEET_NAME = "Test Results";
    private static final int HEADER_ROW_INDEX = 1;

    private final ExcelFileManager fileManager;
    private Sheet sheet;
    private int currentRow;

    /**
     * Constructor to initialize the ExcelDataManager with a provided ExcelFileManager instance.
     *
     * @param fileManager An instance of ExcelFileManager for file operations.
     */
    public ExcelDataManager(ExcelFileManager fileManager) {
        this.fileManager = fileManager;
        this.sheet = fileManager.getOrCreateSheet(SHEET_NAME);
        initSheet();
    }

    /**
     * Initializes the sheet by creating headers and writing metadata if the sheet is empty.
     */

    private void initSheet() {

        if (sheet.getLastRowNum() == -1) {
            createHeaders();
            writeMetaData();
            currentRow = 2;
        } else {

            currentRow = readMetaData() + 1;
        }
    }

    /**
     * Creates the headers row for the sheet, highlighting and applying background colors.
     */
    private void createHeaders() {
        Row headerRow = sheet.getRow(HEADER_ROW_INDEX);
        int colIndex = 0;
        if (headerRow == null) {
            headerRow = sheet.createRow(HEADER_ROW_INDEX);
            ExcelStyleManager.highlightText(fileManager.getWorkbook(), headerRow, colIndex, "Test Name");
            ExcelStyleManager.setBackgroundColor(fileManager.getWorkbook(), headerRow, colIndex++, IndexedColors.LIGHT_BLUE);

            ExcelStyleManager.highlightText(fileManager.getWorkbook(), headerRow, colIndex, "Status");
            ExcelStyleManager.setBackgroundColor(fileManager.getWorkbook(), headerRow, colIndex++, IndexedColors.LIGHT_BLUE);

            ExcelStyleManager.highlightText(fileManager.getWorkbook(), headerRow, colIndex, "Message");
            ExcelStyleManager.setBackgroundColor(fileManager.getWorkbook(), headerRow, colIndex++, IndexedColors.LIGHT_BLUE);

            ExcelStyleManager.highlightText(fileManager.getWorkbook(), headerRow, colIndex, "Date");
            ExcelStyleManager.setBackgroundColor(fileManager.getWorkbook(), headerRow, colIndex, IndexedColors.LIGHT_BLUE);
        }
    }

    /**
     * Writes metadata to the sheet, such as the number of test results.
     */
    private void writeMetaData() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(currentRow);
    }

    /**
     * Reads metadata from the sheet, such as the current row for writing data.
     *
     * @return The row index where new data will be written.
     */

    private int readMetaData() {
        if (sheet.getRow(0) != null) {
            Cell cell = sheet.getRow(0).getCell(0);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        }
        return 0;
    }

    /**
     * Writes a test result to the sheet, including the test name, status, message, and current date.
     *
     * @param testName The name of the test.
     * @param status   The status of the test (e.g., passed, failed).
     * @param message  A message related to the test result.
     */
    public void writeTestResult(String testName, String status, String message) {
        Row row = sheet.createRow(currentRow++);
        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(message);
        row.createCell(3).setCellValue(getCurrentDate());

    }

    /**
     * Writes a test result to the sheet with an image path, highlighting the text in red.
     *
     * @param testName  The name of the test.
     * @param status    The status of the test.
     * @param message   A message related to the test result.
     * @param imagePath The path to an image to be included in the report.
     */
    public void writeTestResult(String testName, String status, String message, String imagePath) {
        Row row = sheet.createRow(currentRow++);
        ExcelStyleManager.writeTextWithColor(fileManager.getWorkbook(),
                row, 0, testName, IndexedColors.RED);
        ExcelStyleManager.writeTextWithColor(fileManager.getWorkbook(),
                row, 1, status, IndexedColors.RED);
        ExcelStyleManager.writeTextWithColor(fileManager.getWorkbook(),
                row, 2, message, IndexedColors.RED);
        ExcelStyleManager.writeTextWithColor(fileManager.getWorkbook(),
                row, 3, getCurrentDate(), IndexedColors.RED);
        addImage(imagePath);
    }

    /**
     * Retrieves the current date formatted as "yyyy-MM-dd HH:mm:ss".
     *
     * @return The current date as a formatted string.
     */
    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * Adds an image to the sheet at the specified path, adjusting the row index to account for the image's height.
     *
     * @param imagePath The path to the image to be added to the sheet.
     */

    public void addImage(String imagePath) {
        Image image = new Image(imagePath);
        int imageHeightInRows = ExcelImageUtils.addImageToSheet(sheet, fileManager.getWorkbook(),
                image, 5, currentRow);

        currentRow += imageHeightInRows;
    }

    /**
     * Saves the workbook, updating metadata before saving.
     */

    public void save() {
        writeMetaData();
        fileManager.saveWorkbook();
    }

    /**
     * Closes the workbook, releasing resources.
     */

    public void close() {
        fileManager.closeWorkbook();
    }
}

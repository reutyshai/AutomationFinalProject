package Report.ReportWriters;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;

public class ExcelWriter {
    private static ExcelWriter instance;
    private Workbook workbook;
    private Sheet sheet;
    private int position;
    private int imagesSumRows;

    private final int META_ROW_INDEX = 0; // שורת המטא-דאטה

    public void setImagesSumRows(int imagesSumRows) {
         this.imagesSumRows=imagesSumRows;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public int getPosition() {
        return position;
    }

    private ExcelWriter() {
        initFile();
    }

    public static synchronized ExcelWriter getInstance() {
        if (instance == null) {
            instance = new ExcelWriter();
        }
        return instance;
    }

    private void initFile() {
        File file = new File("test_results.xlsx");
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheet("Test Results");

                // קריאת המידע מהשורות הקיימות
                position = sheet.getLastRowNum();
                imagesSumRows = readMetaData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Test Results");
            createHeaders();
            position = 0;
            imagesSumRows = 0;
        }
    }

    private int readMetaData() {
        Row metaRow = sheet.getRow(META_ROW_INDEX);
        if (metaRow != null && metaRow.getCell(0) != null) {
            return (int) metaRow.getCell(0).getNumericCellValue();
        }
        return 0; // ברירת מחדל אם אין נתון
    }

    private void writeMetaData() {
        Row metaRow = sheet.getRow(META_ROW_INDEX);
        if (metaRow == null) {
            metaRow = sheet.createRow(META_ROW_INDEX);
        }
        metaRow.createCell(0).setCellValue(imagesSumRows);
    }

    public void writeToXlsxFile(Map<String, String> testResults) {
        writeData(testResults);
    }

    private void createHeaders() {
        Row headerRow = sheet.createRow(1); // הכותרות יתחילו אחרי שורת המטא-דאטה
        headerRow.createCell(0).setCellValue("Test Name");
        headerRow.createCell(1).setCellValue("Status");
        headerRow.createCell(2).setCellValue("Message");
        headerRow.createCell(3).setCellValue("Date");
    }

    private void writeData(Map<String, String> testResults) {
        Row row = sheet.createRow(++position);
        row.createCell(0).setCellValue(testResults.getOrDefault("Name", ""));
        row.createCell(1).setCellValue(testResults.getOrDefault("Status", ""));
        row.createCell(2).setCellValue(testResults.getOrDefault("Message", ""));
        row.createCell(3).setCellValue(testResults.getOrDefault("Date", ""));
    }



    public void saveAndClose() {
//        writeMetaData(); // עדכון המידע בקובץ
        try (FileOutputStream fileOut = new FileOutputStream("test_results.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
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

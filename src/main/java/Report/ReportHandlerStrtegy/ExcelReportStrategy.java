package Report.ReportHandlerStrtegy;

import Report.ReportWriters.ExcelWriter;
import Readers.Image;
import Report.ImageReport.ImageToXlsx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExcelReportStrategy implements ReportHandler {
    private final ExcelWriter excelWriter;
    private final Map<String, String> testResults;
    private ReportHandler next;
    Image image;
    ImageToXlsx imageToXlsx;

    public ExcelReportStrategy(Image image) {
        this.excelWriter = ExcelWriter.getInstance();
        this.testResults = new HashMap<>();
        this.image = image;
        imageToXlsx=new ImageToXlsx();
    }

    private void logTestResult(String message, String status, String testName) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        testResults.put("Name", testName);
        testResults.put("Status", status);
        testResults.put("Message", message);
        testResults.put("Date", dateString);
    }

    @Override
    public void setNext() {
        this.next = null;
    }

    @Override
    public void successReport(String message, String testName) {
        logTestResult(message, "Succeeded", testName);
        excelWriter.writeToXlsxFile(testResults);
    }

    @Override
    public void failureReport(String message, String testName) {
        logTestResult(message, "Failed", testName);
        imageToXlsx.addImageToReport(image);
        excelWriter.writeToXlsxFile(testResults);

    }

    @Override
    public void disabledReport(String message, String testName) {
        logTestResult(message, "Disabled", testName);
        excelWriter.writeToXlsxFile(testResults);

    }

    @Override
    public void abortingReport(String message, String testName) {
        logTestResult(message, "Aborted", testName);
        excelWriter.writeToXlsxFile(testResults);
    }
}

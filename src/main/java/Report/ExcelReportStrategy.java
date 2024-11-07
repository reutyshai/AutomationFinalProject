package Report;

import Readers.ExcelWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExcelReportStrategy implements ReportHandler {
    private ReportHandler next;
    private ExcelWriter excelWriter = ExcelWriter.getInstance();
    private Map<String, String> testResults;

    public ExcelReportStrategy() {
        setNext();
        testResults = new HashMap<>();
    }

    private void logTestResult(String message, String status, String testName) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        testResults.put("Name", testName);
        testResults.put("Status", status);
        testResults.put("Message", message);
        testResults.put("Date", dateString);
        excelWriter.writeData(testResults);
    }


    @Override
    public void setNext() {
        this.next = null;
    }

    @Override
    public void successReport(String message, String testName) {
        logTestResult(message, "Succeeded", testName);
    }

    @Override
    public void failureReport(String message, String testName) {
        logTestResult(message, "Failed", testName);
    //add screenshot
    }

    @Override
    public void disabledReport(String message, String testName) {
        logTestResult(message, "Disabled", testName);

    }

    @Override
    public void abortingReport(String message, String testName) {
        logTestResult(message, "Aborted", testName);
    }
}

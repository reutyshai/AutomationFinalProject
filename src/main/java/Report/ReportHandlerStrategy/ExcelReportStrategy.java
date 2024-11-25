package Report.ReportHandlerStrategy;

import Report.ReportWriters.ImageUtils.Image;
import Report.ReportWriters.Excel.ExcelReportManager;

/**
 * A reporting strategy implementation for Excel-based test result management.
 * It uses the {@link ExcelReportManager} to record test results in an Excel file.
 */
public class ExcelReportStrategy implements ReportHandler {
    private final ExcelReportManager excelReportManager;
    private ReportHandler next;
    Image image;

    /**
     * Constructor initializes the {@link ExcelReportManager} instance for managing Excel reports.
     */
    public ExcelReportStrategy() {
        this.excelReportManager = ExcelReportManager.getInstance();
    }

    /**
     * Sets the next reporting strategy in the chain.
     * Currently, no additional handlers are used in this implementation.
     */
    @Override
    public void setNext() {
        this.next = null;
    }

    /**
     * Handles a successful test report.
     * Logs the test result as "Succeeded" in the Excel report.
     *
     * @param message  - The success message.
     * @param testName - The name of the test.
     */
    @Override
    public void successReport(String message, String testName) {
        excelReportManager.reportTestResult(testName, "Succeeded", message);
    }

    /**
     * Handles a failed test report.
     * Logs the test result as "Failed" in the Excel report and optionally attaches an image.
     *
     * @param message   - The failure message.
     * @param testName  - The name of the test.
     * @param imagePath - The file path of the failure screenshot or image.
     */
    @Override
    public void failureReport(String message, String testName, String imagePath) {
        excelReportManager.reportTestResult(testName, "Failed", message, imagePath);
    }

    /**
     * Handles a disabled test report.
     * Logs the test result as "Disabled" in the Excel report.
     *
     * @param message  - The disabled test message.
     * @param testName - The name of the test.
     */
    @Override
    public void disabledReport(String message, String testName) {
        excelReportManager.reportTestResult(testName, "Disabled", message);
    }

    /**
     * Handles an aborted test report.
     * Logs the test result as "Aborted" in the Excel report.
     *
     * @param message  - The aborted test message.
     * @param testName - The name of the test.
     */
    @Override
    public void abortingReport(String message, String testName) {
        excelReportManager.reportTestResult(testName, "Aborted", message);
    }
}

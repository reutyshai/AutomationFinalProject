package Report.ReportHandlerStrtegy;

import Report.ReportWriters.Logger;

/**
 * A reporting strategy that logs test results using the {@link Logger}.
 * Implements a chain of responsibility pattern to forward reports to the next handler in the chain.
 */
public class LogReportStrategy implements ReportHandler {
    private ReportHandler next;
    private final Logger logger = Logger.getInstance();

    /**
     * Constructor initializes the logger and sets the next handler in the chain.
     */
    public LogReportStrategy() {
        setNext();
    }

    /**
     * Logs a successful test report and forwards the report to the next handler.
     *
     * @param message  - The success message.
     * @param testName - The name of the test.
     */
    @Override
    public void successReport(String message, String testName) {
        logger.info("Test: " + testName + " " + message);
        if (next != null) {
            next.successReport(message, testName);
        }
    }

    /**
     * Logs a failed test report and forwards the report to the next handler.
     * Includes the path to an image (e.g., a screenshot) if available.
     *
     * @param message   - The failure message.
     * @param testName  - The name of the test.
     * @param imagePath - The file path of the failure screenshot or report image.
     */
    @Override
    public void failureReport(String message, String testName, String imagePath) {
        logger.error("Test: " + testName + " " + message + ". Path of image report: " + imagePath);
        if (next != null) {
            next.failureReport(message, testName, imagePath);
        }
    }

    /**
     * Logs a disabled test report and forwards the report to the next handler.
     *
     * @param message  - The disabled test message.
     * @param testName - The name of the test.
     */
    @Override
    public void disabledReport(String message, String testName) {
        logger.warn("Test: " + testName + " " + message);
        if (next != null) {
            next.disabledReport(message, testName);
        }
    }

    /**
     * Logs an aborted test report and forwards the report to the next handler.
     *
     * @param message  - The aborted test message.
     * @param testName - The name of the test.
     */
    @Override
    public void abortingReport(String message, String testName) {
        logger.warn("Test: " + testName + " " + message);
        if (next != null) {
            next.abortingReport(message, testName);
        }
    }

    /**
     * Sets the next handler in the chain of responsibility to {@link ExcelReportStrategy}.
     */
    @Override
    public void setNext() {
        this.next = new ExcelReportStrategy();
    }
}

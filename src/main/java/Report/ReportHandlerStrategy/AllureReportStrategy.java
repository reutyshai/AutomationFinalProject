package Report.ReportHandlerStrategy;

import Report.ReportWriters.Allure.AllureUtils;

/**
 * A reporting strategy implementation that integrates with Allure for enhanced test reporting.
 * It delegates non-Allure-specific reporting functionality to the next handler in the chain,
 * using the Chain of Responsibility pattern.
 */
public class AllureReportStrategy implements ReportHandler {
    private ReportHandler next;

    /**
     * Constructor initializes the next reporting strategy in the chain.
     */
    public AllureReportStrategy() {
        setNext();
    }

    /**
     * Sets the next reporting strategy in the chain.
     * This implementation uses the {@link LogReportStrategy} as the next handler.
     */
    @Override
    public void setNext() {
        this.next = new LogReportStrategy();
    }

    /**
     * Handles a successful test report.
     * Delegates the reporting to the next handler in the chain.
     *
     * @param message  - The success message.
     * @param testName - The name of the test.
     */
    @Override
    public void successReport(String message, String testName) {
        this.next.successReport(message, testName);
    }

    /**
     * Handles a failed test report.
     * Attaches a screenshot to Allure and delegates the reporting to the next handler in the chain.
     *
     * @param message   - The failure message.
     * @param testName  - The name of the test.
     * @param imagePath - The file path of the screenshot to attach.
     */
    @Override
    public void failureReport(String message, String testName, String imagePath) {
        // Attach screenshot to Allure
        AllureUtils.attachScreenshotToAllure(imagePath);
        // Delegate to the next handler
        this.next.failureReport(message, testName, imagePath);
    }

    /**
     * Handles a disabled test report.
     * Delegates the reporting to the next handler in the chain.
     *
     * @param message  - The disabled test message.
     * @param testName - The name of the test.
     */
    @Override
    public void disabledReport(String message, String testName) {
        this.next.disabledReport(message, testName);
    }

    /**
     * Handles an aborted test report.
     * Delegates the reporting to the next handler in the chain.
     *
     * @param message  - The aborted test message.
     * @param testName - The name of the test.
     */
    @Override
    public void abortingReport(String message, String testName) {
        this.next.abortingReport(message, testName);
    }
}

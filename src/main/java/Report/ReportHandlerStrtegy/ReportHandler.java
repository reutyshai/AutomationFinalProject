package Report.ReportHandlerStrtegy;

/**
 * Defines a reporting handler interface for managing test result reporting.
 * Handlers can be linked in a chain of responsibility to forward reports.
 */
public interface ReportHandler {

    /**
     * Sets the next handler in the chain of responsibility.
     */
    void setNext();

    /**
     * Handles a successful test report.
     *
     * @param message  - The success message.
     * @param testName - The name of the test.
     */
    void successReport(String message, String testName);

    /**
     * Handles a failed test report.
     *
     * @param message   - The failure message.
     * @param testName  - The name of the test.
     * @param imagePath - The file path of the failure screenshot or report image.
     */
    void failureReport(String message, String testName, String imagePath);

    /**
     * Handles a disabled test report.
     *
     * @param message  - The disabled test message.
     * @param testName - The name of the test.
     */
    void disabledReport(String message, String testName);

    /**
     * Handles an aborted test report.
     *
     * @param message  - The aborted test message.
     * @param testName - The name of the test.
     */
    void abortingReport(String message, String testName);
}

package Report.ReportHandlerStrtegy;

public interface ReportHandler {

    void setNext();

    void successReport(String message, String testName);

    void failureReport(String message, String testName);

    void disabledReport(String message, String testName);

    void abortingReport(String message, String testName);
}

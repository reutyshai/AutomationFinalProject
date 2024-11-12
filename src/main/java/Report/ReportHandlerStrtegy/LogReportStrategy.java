package Report.ReportHandlerStrtegy;

import Report.ReportWriters.Logger;

public class LogReportStrategy implements ReportHandler {
    ReportHandler next;
    Logger logger = Logger.getInstance();

    public LogReportStrategy() {
        setNext();
    }

    @Override
    public void successReport(String massage, String testName) {

        logger.info("Test: " + testName + " " + massage);
        this.next.successReport(massage, testName);

    }

    @Override
    public void failureReport(String massage, String testName, String imagePath) {
        logger.error("Test: " + testName + " " + massage + ". Path of image report: "
        + imagePath);
        this.next.failureReport(massage, testName,imagePath );
    }

    @Override
    public void disabledReport(String massage, String testName) {
        logger.warn("Test: " + testName + " " + massage);
        this.next.disabledReport(massage, testName);
    }

    @Override
    public void abortingReport(String massage, String testName) {
        logger.warn("Test: " + testName + " " + massage);
        this.next.abortingReport(massage, testName);
    }


    @Override
    public void setNext() {
        this.next = new ExcelReportStrategy();
    }
}

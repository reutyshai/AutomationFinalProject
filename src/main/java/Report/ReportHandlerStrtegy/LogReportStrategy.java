package Report.ReportHandlerStrtegy;

import Readers.Image;
import Report.ReportWriters.Logger;
import Report.ImageReport.ImageToLog;

public class LogReportStrategy implements ReportHandler {
    ReportHandler next;
    Logger logger = Logger.getInstance();
    Image image;
    ImageToLog imageToLog;

    public LogReportStrategy(Image image) {
        setNext();
        this.image = image;
        imageToLog = new ImageToLog();
    }

    @Override
    public void successReport(String massage, String testName) {

        logger.info("Test: " + testName + " " + massage);
        this.next.successReport(massage, testName);

    }

    @Override
    public void failureReport(String massage, String testName) {
        logger.error("Test: " + testName + " " + massage);
        imageToLog.addImageToReport(image);
        this.next.failureReport(massage, testName);
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
        this.next = new ExcelReportStrategy(image);
    }
}

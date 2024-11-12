package Report.ReportHandlerStrtegy;

import Readers.Image;
import Report.ImageReport.ImageReportManager;
import Report.ReportWriters.Allure.AllureUtils;

public class AllureReportStrategy implements ReportHandler {
    private ReportHandler next;



    public AllureReportStrategy(Image image) {
        setNext();
    }


    @Override
    public void setNext() {
        this.next = new LogReportStrategy();

    }

    @Override
    public void successReport(String message, String testName) {
        this.next.successReport(message, testName);
    }

    @Override
    public void failureReport(String message, String testName, String imagePath) {
        AllureUtils.attachScreenshotToAllure(imagePath);
        this.next.failureReport(message, testName, imagePath);
    }

    @Override
    public void disabledReport(String message, String testName) {
        this.next.disabledReport(message, testName);
    }

    @Override
    public void abortingReport(String message, String testName) {
        this.next.abortingReport(message, testName);
    }
}

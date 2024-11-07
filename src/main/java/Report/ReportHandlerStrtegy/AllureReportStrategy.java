package Report.ReportHandlerStrtegy;

import Readers.Image;
import Report.ImageReport.ImageReportManager;
import Report.ImageReport.ImageToAllure;

public class AllureReportStrategy implements ReportHandler {
    private ReportHandler next;
    private ImageReportManager imageReportManager;
    private ImageToAllure imageToAllure;
    Image image;

    public AllureReportStrategy(Image image) {
        setNext();
        imageToAllure=new ImageToAllure();
        this.image=image;
    }


    @Override
    public void setNext() {
        this.next = new LogReportStrategy(image);

    }

    @Override
    public void successReport(String message, String testName) {
        System.out.println("reporting to allure or another system");
        System.out.println("test succeeded");
        this.next.successReport(message, testName);
    }

    @Override
    public void failureReport(String message, String testName) {
        System.out.println("reporting to allure or another system");
        System.out.println("test failed");
        imageToAllure.addImageToReport(image);
        this.next.failureReport(message, testName);
    }

    @Override
    public void disabledReport(String message, String testName) {
        System.out.println("reporting to allure or another system");
        System.out.println("test disabled");
        this.next.disabledReport(message, testName);
    }

    @Override
    public void abortingReport(String message, String testName) {
        System.out.println("reporting to allure or another system");
        System.out.println("test aborted");
        this.next.abortingReport(message, testName);
    }
}

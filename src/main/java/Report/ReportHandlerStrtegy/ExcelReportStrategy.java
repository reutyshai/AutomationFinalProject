package Report.ReportHandlerStrtegy;

//import Report.ReportWriters.ExcelWriter;
import Readers.Image;
import Report.ReportWriters.Excel.ExcelReportManager;
//import Report.ImageReport.ImageToXlsx;


public class ExcelReportStrategy implements ReportHandler {
    private final ExcelReportManager excelReportManager;
    private ReportHandler next;
    Image image;
//    ImageToXlsx imageToXlsx;

    public ExcelReportStrategy() {
        this.excelReportManager = ExcelReportManager.getInstance();
//        imageToXlsx = new ImageToXlsx();

    }

    @Override
    public void setNext() {
        this.next = null;
    }

    @Override
    public void successReport(String message, String testName) {
        excelReportManager.reportTestResult(testName, "Succeeded", message);

    }

    @Override
    public void failureReport(String message, String testName, String imagePath) {
        //        imageToXlsx.addImageToReport(image);
        excelReportManager.reportTestResult(testName, "Failed", message,imagePath);
    }

    @Override
    public void disabledReport(String message, String testName) {

        excelReportManager.reportTestResult(testName, "Disabled", message);

    }

    @Override
    public void abortingReport(String message, String testName) {
        excelReportManager.reportTestResult(testName, "Aborted", message);

    }
}

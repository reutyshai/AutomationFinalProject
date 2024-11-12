package Report;


import Report.ReportWriters.Excel.ExcelReportManager;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifeCycleExtension implements BeforeAllCallback, AfterAllCallback {
    private static ExcelReportManager reportManager;

    @Override
    public void beforeAll(ExtensionContext context) {
        reportManager=ExcelReportManager.getInstance();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reportManager.saveAndClose();
    }
}

package Report;


import Report.ReportWriters.Excel.ExcelReportManager;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifeCycleExtension implements BeforeAllCallback, AfterAllCallback {
    private static ExcelReportManager reportManager;
public static String testClassName="";
    @Override
    public void beforeAll(ExtensionContext context) {
        testClassName=context.getTestClass().getClass().getName();
        reportManager=ExcelReportManager.getInstance();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reportManager.saveAndClose();
    }
}

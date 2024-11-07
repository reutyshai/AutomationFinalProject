package Report;

import Report.ReportWriters.ExcelWriter;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifeCycleExtension implements BeforeAllCallback, AfterAllCallback {
    private static ExcelWriter excelWriter;

    @Override
    public void beforeAll(ExtensionContext context) {
        excelWriter = ExcelWriter.getInstance();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        excelWriter.saveAndClose();
    }
}

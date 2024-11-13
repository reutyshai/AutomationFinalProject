package Report.Extensions;

import Report.ReportWriters.Excel.ExcelReportManager;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit 5 extension to manage the lifecycle of the test report.
 * This extension is responsible for initializing the report manager before
 * all tests and saving and closing the report after all tests are executed.
 *
 * It implements the {@link BeforeAllCallback} and {@link AfterAllCallback} interfaces
 * to hook into the test lifecycle and trigger actions before and after all tests.
 */
public class LifeCycleExtension implements BeforeAllCallback, AfterAllCallback {

    // Singleton instance of the ExcelReportManager for managing test results
    private static ExcelReportManager reportManager;

    // Name of the test class for the report
    public static String testClassName = "";

    /**
     * This method is called before all tests are executed.
     * It initializes the test class name and the report manager.
     *
     * @param context - The extension context providing information about the test execution.
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        // Get the test class name and store it
        testClassName = context.getTestClass().getClass().getName();
        // Initialize the report manager
        reportManager = ExcelReportManager.getInstance();
    }

    /**
     * This method is called after all tests are executed.
     * It saves and closes the report manager to finalize the test report.
     *
     * @param context - The extension context providing information about the test execution.
     */
    @Override
    public void afterAll(ExtensionContext context) {
        // Save and close the report manager to store the results
        reportManager.saveAndClose();
    }
}

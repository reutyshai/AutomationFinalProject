package Report.Extensions;

import DriverFactory.WebDriverBrowserManager;
import Readers.XmlFileReader;
import Report.ReportHandlerStrtegy.LogReportStrategy;
import Report.ReportHandlerStrtegy.ReportHandler;
import Report.ReportWriters.Allure.AllureUtils;
import Report.ReportWriters.ScreenshotUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

/**
 * JUnit 5 extension that implements the TestWatcher interface to handle test events.
 * This extension listens for the success, failure, abortion, and disablement of tests and generates reports accordingly.
 * It utilizes a reporting strategy and attaches screenshots on test failure using Allure.
 */
public class TestWatcherExtension implements TestWatcher {

    private ReportHandler reportStrategy;
    private WebDriver driver;

    /**
     * Constructor initializes the report strategy to log reports.
     */
    public TestWatcherExtension() {
        this.reportStrategy = new LogReportStrategy();
    }

    /**
     * Called when a test is successful.
     * Logs a success message for the test and its display name.
     *
     * @param context - The context of the test being executed.
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        reportStrategy.successReport("The test succeeded", context.getDisplayName());
    }

    /**
     * Called when a test fails.
     * Takes a screenshot of the failure, attaches it to Allure, and logs a failure report with the exception message.
     *
     * @param context - The context of the test being executed.
     * @param cause   - The exception thrown during the test execution.
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        // Get the WebDriver instance
        driver = WebDriverBrowserManager.getDriver("driver");

        // Take a screenshot and get the path
        String imagePath = ScreenshotUtil.takeScreenshot(driver,
                XmlFileReader.getData("src/main/resources/config.xml", "PathScreenShot"));

        // Attach the screenshot to Allure
        AllureUtils.attachScreenshotToAllure(imagePath);

        // Log a failure report with the cause and screenshot
        reportStrategy.failureReport("The test failed " + cause, context.getDisplayName(), imagePath);
    }

    /**
     * Called when a test is aborted.
     * Logs an abortion report with the exception message.
     *
     * @param context - The context of the test being executed.
     * @param cause   - The exception that caused the test to be aborted.
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        reportStrategy.abortingReport("The test aborted " + cause, context.getDisplayName());
    }

    /**
     * Called when a test is disabled.
     * Logs a disabled report with the reason for the test being disabled.
     *
     * @param context - The context of the test being executed.
     * @param reason  - The reason for the test being disabled.
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        reportStrategy.disabledReport("The test disabled " + reason.orElse("No reason provided"),
                context.getDisplayName());
    }
}

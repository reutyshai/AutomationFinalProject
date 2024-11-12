package Report;

import DriverFactory.WebDriverBrowserManager;
import Readers.Image;
import Readers.XmlFileReader;
import Report.ImageReport.ScreenshotUtil;
import Report.ReportHandlerStrtegy.AllureReportStrategy;
import Report.ReportHandlerStrtegy.LogReportStrategy;
import Report.ReportHandlerStrtegy.ReportHandler;
import Report.ReportWriters.Allure.AllureUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import javax.sql.rowset.spi.XmlReader;
import java.util.Optional;

public class TestWatcherExtension implements TestWatcher {
    ReportHandler reportStrategy;
    WebDriver driver;

    public TestWatcherExtension() {
        this.reportStrategy = new LogReportStrategy();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {

        reportStrategy.successReport("The test succeeded", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        driver=WebDriverBrowserManager.getDriver("driver");
        String imagePath = ScreenshotUtil.takeScreenshot(driver, XmlFileReader.getData("src/main/resources/config.xml",
                "PathScreenShot"));
        AllureUtils.attachScreenshotToAllure(imagePath);
        reportStrategy.failureReport("The test failed " + cause,
                context.getDisplayName(), imagePath);

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        reportStrategy.abortingReport("The test aborted " + cause,
                context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        reportStrategy.disabledReport("The test disabled " + reason,
                context.getDisplayName());
    }
}

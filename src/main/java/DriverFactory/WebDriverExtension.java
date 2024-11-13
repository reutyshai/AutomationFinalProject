package DriverFactory;

import Readers.XmlFileReader;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

/**
 * A JUnit 5 extension for managing WebDriver lifecycle during tests.
 * <p>
 * This extension ensures that a WebDriver instance is created before all tests run and
 * properly cleaned up after all tests are finished. It interacts with {@link WebDriverBrowserManager}
 * to get and release the WebDriver instance.
 * </p>
 */
public class WebDriverExtension implements BeforeAllCallback, AfterAllCallback {

    private WebDriver driver;

    /**
     * Initializes the WebDriver before any tests are run.
     * <p>
     * This method retrieves a WebDriver instance using {@link WebDriverBrowserManager}.
     * </p>
     *
     * @param context the extension context for the current test execution
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        driver = WebDriverBrowserManager.getDriver("driver");
    }

    /**
     * Cleans up the WebDriver after all tests have finished.
     * <p>
     * This method terminates the WebDriver session and releases the resources
     * by calling {@link WebDriverBrowserManager#cleanupDriver(String)}.
     * </p>
     *
     * @param context the extension context for the current test execution
     */
    @Override
    public void afterAll(ExtensionContext context) {
        WebDriverBrowserManager.cleanupDriver("driver");
    }
}


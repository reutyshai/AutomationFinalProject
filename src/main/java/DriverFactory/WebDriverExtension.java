package DriverFactory;


import Readers.XmlFileReader;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class WebDriverExtension implements BeforeAllCallback, AfterAllCallback {
    WebDriver driver;

    @Override
    public void beforeAll(ExtensionContext context) {
        driver = WebDriverBrowserManager.getDriver("driver");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        WebDriverBrowserManager.cleanupDriver("driver");
    }

}

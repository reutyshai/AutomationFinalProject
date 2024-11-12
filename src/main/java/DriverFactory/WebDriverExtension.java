package DriverFactory;


import Readers.XmlFileReader;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class WebDriverExtension implements BeforeAllCallback, AfterAllCallback {
    WebDriver driver;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        driver = WebDriverBrowserManager.getDriver();
        driver.manage().window().maximize();
        driver.get(XmlFileReader.getData("src/main/resources/config.xml","Url"));
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }
}

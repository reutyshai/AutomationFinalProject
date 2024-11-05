package DriverFactory;

import Readers.XmlFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static WebDriver driverManager;

    private WebDriverFactory() {
    }

    public static synchronized WebDriver createDriver() {

        switch (XmlFileReader.getData("src/main/resources/config.xml","Browser").toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driverManager = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driverManager = new FirefoxDriver();
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driverManager = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + XmlFileReader.getData("","Browser"));
        }
        return driverManager;
    }

    public static WebDriver getDriver() {
        if (driverManager == null)
            driverManager = createDriver();
        return driverManager;
    }

    public static void quitDriver() {
        if (driverManager != null) {
            driverManager.quit();
            driverManager = null; // Allow the driver to be recreated on the next request
        }
    }
}

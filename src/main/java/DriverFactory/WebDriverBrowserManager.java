package DriverFactory;

import Readers.XmlFileReader;
import org.openqa.selenium.WebDriver;

public class WebDriverBrowserManager {

    private static WebDriverFactory webDriverFactory;


    public static void initDriver() {
        String browserType = XmlFileReader.getData("src/main/resources/config.xml",
                "Browser").toLowerCase();
        switch (browserType) {
            case "chrome":
                webDriverFactory = new ChromeBrowserDriver();
                break;
            case "firefox":
                webDriverFactory = new FireFoxBrowserDriver();
                break;
            case "edge":
                webDriverFactory = new EdgeBrowserDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    public static WebDriver getDriver() {
        initDriver();
        return webDriverFactory.createDriver();
    }
}

package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * A factory class for initializing and managing a Chrome browser WebDriver.
 * <p>
 * This class extends {@link WebDriverFactory} and provides an implementation
 * for initializing a Chrome WebDriver using the {@code WebDriverManager}.
 * </p>
 */
public class ChromeBrowserDriver extends WebDriverFactory {

    /**
     * Initializes the Chrome WebDriver instance.
     * <p>
     * This method uses {@link WebDriverManager} to automatically set up the
     * correct version of the ChromeDriver for the operating system and browser.
     * Once set up, it creates a new instance of {@link ChromeDriver}.
     * </p>
     */
    @Override
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driverManager = new ChromeDriver();
    }
}

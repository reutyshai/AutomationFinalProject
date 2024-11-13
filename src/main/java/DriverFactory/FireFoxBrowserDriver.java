package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * A factory class for initializing and managing a Firefox browser WebDriver.
 * <p>
 * This class extends {@link WebDriverFactory} and provides an implementation
 * for initializing a Firefox WebDriver using the {@code WebDriverManager}.
 * </p>
 */
public class FireFoxBrowserDriver extends WebDriverFactory {

    /**
     * Initializes the Firefox WebDriver instance.
     * <p>
     * This method uses {@link WebDriverManager} to automatically set up the
     * correct version of the FirefoxDriver for the operating system and browser.
     * Once set up, it creates a new instance of {@link FirefoxDriver}.
     * </p>
     */
    @Override
    public void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driverManager = new FirefoxDriver();
    }
}

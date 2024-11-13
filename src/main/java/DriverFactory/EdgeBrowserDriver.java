package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * A factory class for initializing and managing a Microsoft Edge browser WebDriver.
 * <p>
 * This class extends {@link WebDriverFactory} and provides an implementation
 * for initializing an Edge WebDriver using the {@code WebDriverManager}.
 * </p>
 */
public class EdgeBrowserDriver extends WebDriverFactory {

    /**
     * Initializes the Edge WebDriver instance.
     * <p>
     * This method uses {@link WebDriverManager} to automatically set up the
     * correct version of the EdgeDriver for the operating system and browser.
     * Once set up, it creates a new instance of {@link EdgeDriver}.
     * </p>
     */
    @Override
    public void initDriver() {
        WebDriverManager.edgedriver().setup();
        driverManager = new EdgeDriver();
    }
}

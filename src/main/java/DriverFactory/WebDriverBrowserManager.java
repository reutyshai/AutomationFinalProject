package DriverFactory;

import Readers.XmlFileReader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * A manager class responsible for managing WebDriver instances for different browsers.
 * <p>
 * This class uses a caching mechanism to store WebDriver instances for reuse, improving
 * performance by avoiding redundant WebDriver initializations. It supports various browsers
 * (Chrome, Firefox, Edge) and automatically reads the desired browser type from an XML configuration file.
 * </p>
 */
public class WebDriverBrowserManager {

    private static WebDriverFactory webDriverFactory;
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(WebDriverBrowserManager.class);
    private static final Cache<String, WebDriver> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)  // Cache expires after 10 minutes
            .build();

    /**
     * Initializes the WebDriver according to the browser type specified in the configuration file.
     * <p>
     * The method reads the browser type from an XML file and instantiates the appropriate
     * {@link WebDriverFactory} subclass (Chrome, Firefox, or Edge).
     * </p>
     *
     * @throws IllegalArgumentException if the browser type is unsupported or invalid
     */
    private static void initDriver() {
        String browserType = XmlFileReader.getData("src/main/resources/config.xml", "Browser").toLowerCase();
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

    /**
     * Retrieves a WebDriver instance for the specified driver name.
     * <p>
     * If a WebDriver instance with the specified name is already cached, it is returned.
     * Otherwise, a new instance is created and cached for future use.
     * </p>
     *
     * @param driverName the name of the WebDriver instance (for example, "chromeDriver")
     * @return the WebDriver instance associated with the specified driver name
     */
    public static WebDriver getDriver(String driverName) {
        WebDriver driver = cache.getIfPresent(driverName);
        if (driver == null) {
            // If not cached, initialize the driver and cache it
            initDriver();
            driver = webDriverFactory.createDriver();
            cache.put(driverName, driver);
        }
        return driver;
    }

    /**
     * Cleans up the WebDriver instance associated with the specified driver name.
     * <p>
     * This method terminates the WebDriver session and invalidates it from the cache.
     * </p>
     *
     * @param driverName the name of the WebDriver instance to clean up
     */
    public static void cleanupDriver(String driverName) {
        WebDriver driver = cache.getIfPresent(driverName);
        if (driver != null) {
            driver.quit();
            cache.invalidate(driverName);
        }
    }
}

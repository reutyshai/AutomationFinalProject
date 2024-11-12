package DriverFactory;

import Readers.XmlFileReader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverBrowserManager {

    private static WebDriverFactory webDriverFactory;
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(WebDriverBrowserManager.class);
    private static final Cache<String, WebDriver> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)  // זמן פג תוקף
            .build();

    // פונקציה שמאתחלת את הדרייבר לפי סוג הדפדפן
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
        }}
    // פונקציה לקבלת ה-driver, אם לא קיים הוא ייווצר וישמר בסטור
    public static WebDriver getDriver(String driverName) {
        WebDriver driver = cache.getIfPresent(driverName);
        if (driver == null) {
            // אם לא קיים, אתחיל את ה-driver ואשמור אותו בסטור
            initDriver();
            driver = webDriverFactory.createDriver();
            cache.put(driverName, driver);

        }

        return driver;
    }

    // פונקציה לשחרור ה-driver (לסיים את החיבור)
    public static void cleanupDriver(String driverName) {
        WebDriver driver = cache.getIfPresent(driverName);
        if (driver != null) {
            driver.quit();
            cache.invalidate(driverName);
        }
    }
}

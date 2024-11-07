package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowserDriver extends WebDriverFactory {

    @Override
    public void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driverManager = new FirefoxDriver();
    }
}

package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeBrowserDriver extends WebDriverFactory {

    @Override
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driverManager = new ChromeDriver();
    }
}

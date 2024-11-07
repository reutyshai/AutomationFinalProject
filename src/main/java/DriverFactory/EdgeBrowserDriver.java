package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowserDriver extends WebDriverFactory {
    @Override
    public void initDriver() {
        WebDriverManager.edgedriver().setup();
        driverManager = new EdgeDriver();
    }
}

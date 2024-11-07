package DriverFactory;

import Readers.XmlFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;

public abstract class WebDriverFactory {
    protected WebDriver driverManager;


    public WebDriverFactory() {
    }

    public abstract void initDriver();

    public WebDriver createDriver() {
        initDriver();
        maximize();
        get();
        waitToElements();
        return driverManager;
    }


    private void maximize() {
        driverManager.manage().window().maximize();
    }

    private void waitToElements() {

    }

    private void get() {
        driverManager.get(XmlFileReader.getData("src/main/resources/config.xml", "Url"));
    }

}

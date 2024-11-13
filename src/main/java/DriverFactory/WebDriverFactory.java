package DriverFactory;

import Readers.XmlFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;

/**
 * A base class for creating and initializing WebDriver instances for different browsers.
 * <p>
 * This class provides an abstract implementation for initializing WebDriver and managing
 * common browser configurations such as maximizing the window, navigating to a specified URL,
 * and waiting for elements to load.
 * </p>
 */
public abstract class WebDriverFactory {

    protected WebDriver driverManager;

    /**
     * Default constructor for WebDriverFactory.
     * Initializes the WebDriverFactory class.
     */
    public WebDriverFactory() {
    }

    /**
     * Abstract method for initializing the WebDriver.
     * <p>
     * This method should be implemented by subclasses to initialize the WebDriver instance
     * specific to the desired browser (e.g., Chrome, Firefox, Edge).
     * </p>
     */
    public abstract void initDriver();

    /**
     * Creates and returns a WebDriver instance after performing initialization and common setup.
     * <p>
     * This method calls {@link WebDriverFactory#initDriver()} to initialize the WebDriver, maximizes
     * the browser window, navigates to the URL defined in the configuration file, and waits for elements to load.
     * </p>
     *
     * @return the initialized WebDriver instance
     */
    public WebDriver createDriver() {
        initDriver();
        maximize();
        get();
        waitToElements();
        return driverManager;
    }

    /**
     * Maximizes the browser window.
     * <p>
     * This method ensures that the WebDriver window is maximized for better visibility and interaction during tests.
     * </p>
     */
    private void maximize() {
        driverManager.manage().window().maximize();
    }

    /**
     * Waits for elements to load on the page.
     * <p>
     * This method can be extended to implement custom wait logic to ensure that elements are loaded before interaction.
     * Currently, it is a placeholder method and does not perform any actions.
     * </p>
     */
    private void waitToElements() {
        // Custom wait logic can be added here if needed
    }

    /**
     * Navigates the WebDriver to the URL specified in the configuration file.
     * <p>
     * This method retrieves the URL from the configuration file using {@link XmlFileReader}
     * and directs the WebDriver to load that URL.
     * </p>
     */
    private void get() {
        driverManager.get(XmlFileReader.getData("src/main/resources/config.xml", "Url"));
    }
}

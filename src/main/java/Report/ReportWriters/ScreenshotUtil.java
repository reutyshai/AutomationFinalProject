package Report.ReportWriters;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots using a WebDriver.
 * Provides a method to take a screenshot and save it to a specified file path with a timestamp.
 */
public class ScreenshotUtil {

    /**
     * Takes a screenshot of the current state of the WebDriver and saves it to the specified file path.
     * The screenshot file is named with a timestamp to ensure uniqueness.
     *
     * @param driver   - The WebDriver instance used to capture the screenshot.
     * @param filePath - The directory path where the screenshot will be saved.
     * @return The full path of the saved screenshot file, including the file name.
     */
    public static String takeScreenshot(WebDriver driver, String filePath) {
        String fullPath = "";

        // Check if the driver supports screenshot functionality
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            // Capture the screenshot
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Generate a timestamp for the screenshot file name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fullPath = filePath + "/screenshot_" + timestamp + ".png";

            try {
                // Save the screenshot to the specified file path
                FileUtils.copyFile(screenshotFile, new File(fullPath));
                System.out.println("Screenshot saved at: " + fullPath);
            } catch (IOException e) {
                // Log an error if saving the screenshot fails
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        } else {
            // If the driver does not support screenshots, log an error
            System.err.println("Driver does not support taking screenshots.");
        }
        return fullPath;
    }
}

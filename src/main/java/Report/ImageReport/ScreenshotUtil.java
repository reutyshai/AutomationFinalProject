package Report.ImageReport;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String filePath) {
        String fullPath="";
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fullPath = filePath + "/screenshot_" + timestamp + ".png";

            try {
                FileUtils.copyFile(screenshotFile, new File(fullPath));
                System.out.println("Screenshot saved at: " + fullPath);
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        } else {
            System.err.println("Driver does not support taking screenshots.");
        }
        return fullPath;
    }
}


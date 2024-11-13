package Report.ReportWriters.Allure;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class for integrating Allure reporting functionality.
 * Provides methods to enhance Allure reports with additional attachments, such as screenshots.
 */
public class AllureUtils {

    /**
     * Attaches a screenshot to an Allure report.
     *
     * @param screenshotPath - The file path of the screenshot to be attached.
     *                       This should be a valid path to an image file (e.g., PNG or JPEG).
     */
    public static void attachScreenshotToAllure(String screenshotPath) {
        try (FileInputStream fis = new FileInputStream(new File(screenshotPath))) {
            // Adds the screenshot as an attachment to the Allure report.
            Allure.addAttachment("Screenshot", fis);
        } catch (IOException e) {
            // Logs an error message in case of a failure in attaching the screenshot.
            System.err.println("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }
}

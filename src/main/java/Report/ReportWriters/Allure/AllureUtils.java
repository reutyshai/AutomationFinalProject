package Report.ReportWriters.Allure;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AllureUtils {
    public static void attachScreenshotToAllure(String screenshotPath) {
        try (FileInputStream fis = new FileInputStream(new File(screenshotPath))) {
            Allure.addAttachment("Screenshot", fis);
        } catch (IOException e) {
            System.err.println("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }
}

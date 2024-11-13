package Report.ReportWriters.Excel;

/**
 * A singleton class that manages the overall Excel report, integrating ExcelFileManager and ExcelDataManager.
 * It is responsible for reporting test results and saving or closing the report.
 */
public class ExcelReportManager {
    private static ExcelReportManager instance;
    private final ExcelFileManager fileManager;
    private final ExcelDataManager dataManager;

    /**
     * Private constructor to initialize the report manager with the file and data managers.
     */
    private ExcelReportManager() {
        this.fileManager = new ExcelFileManager();
        this.dataManager = new ExcelDataManager(fileManager);
    }

    /**
     * Retrieves the singleton instance of ExcelReportManager.
     *
     * @return The instance of ExcelReportManager.
     */
    public static synchronized ExcelReportManager getInstance() {
        if (instance == null) {
            instance = new ExcelReportManager();
        }
        return instance;
    }


    /**
     * Reports a test result by writing it to the sheet and saving the report.
     *
     * @param testName The name of the test.
     * @param status The status of the test.
     * @param message A message related to the test result.
     */
    public void reportTestResult(String testName, String status, String message) {
        dataManager.writeTestResult(testName, status, message);
        dataManager.save();
    }

    /**
     * Reports a test result along with an image path, saving the report afterward.
     *
     * @param testName The name of the test.
     * @param status The status of the test.
     * @param message A message related to the test result.
     * @param imagePath The path to an image to be included in the report.
     */
    public void reportTestResult(String testName, String status, String message, String imagePath) {
        dataManager.writeTestResult(testName, status, message, imagePath);

        dataManager.save();
    }
    /**
     * Saves the report to the file.
     */
    public void saveReport() {
        dataManager.save();
    }
    /**
     * Closes the report, saving and closing the workbook.
     */
    public void closeReport() {
        dataManager.close();
    }

    /**
     * Saves and closes the report in a single operation.
     */
    public void saveAndClose() {
        saveReport();
        closeReport();
    }
}

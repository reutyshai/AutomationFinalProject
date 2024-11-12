package Report.ReportWriters.Excel;

public class ExcelReportManager {
    private static ExcelReportManager instance;
    private final ExcelFileManager fileManager;
    private final ExcelDataManager dataManager;

    // קונסטרקטור פרטי לשימוש ב-Singleton
    private ExcelReportManager() {
        this.fileManager = new ExcelFileManager();
        this.dataManager = new ExcelDataManager(fileManager);
    }

    // שיטה סטטית לקבלת המופע היחיד
    public static synchronized ExcelReportManager getInstance() {
        if (instance == null) {
            instance = new ExcelReportManager();
        }
        return instance;
    }

    /**
     * דיווח על תוצאה של טסט.
     * @param testName שם הטסט.
     * @param status מצב הטסט (Passed/Failed).
     * @param message הודעה לתיעוד.
     */
    public void reportTestResult(String testName, String status, String message) {
        dataManager.writeTestResult(testName, status, message);
        dataManager.save();
    }
    public void reportTestResult(String testName, String status, String message,String imagePath) {
        dataManager.writeTestResult(testName, status, message);

        dataManager.save();
    }

    /**
     * שמירת כל הנתונים לקובץ.
     */
    public void saveReport() {
        dataManager.save();
    }

    /**
     * סגירת כל המשאבים.
     */
    public void closeReport() {
        dataManager.close();
    }

    public void saveAndClose(){
        dataManager.save();
        dataManager.close();
    }
}

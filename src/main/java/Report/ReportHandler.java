package Report;

public interface ReportHandler {
    void setNext();

    void successReport(String massage);

    void failureReport(String massage);

    void disabledReport(String massage);

    void abortingReport(String massage);
}

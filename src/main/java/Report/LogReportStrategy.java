package Report;

public class LogReportStrategy implements ReportHandler {
    ReportHandler next;

    @Override
    public void successReport(String massage) {

        this.next.successReport(massage);
    }

    @Override
    public void failureReport(String massage) {

        this.next.failureReport(massage);
    }

    @Override
    public void disabledReport(String massage) {

        this.next.disabledReport(massage);
    }

    @Override
    public void abortingReport(String massage) {

        this.next.abortingReport(massage);
    }

    @Override
    public void setNext() {
        this.next = new ExcelReportStrategy();
    }
}

package Report;

public class AllureReportStrategy implements ReportHandler {
    private ReportHandler next;

    @Override
    public void setNext() {
        this.next = new LogReportStrategy();
    }

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
}

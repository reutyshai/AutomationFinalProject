package Report;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestWatcherExtension implements TestWatcher {
    ReportHandler reportStrategy;

    public TestWatcherExtension() {
        this.reportStrategy = new AllureReportStrategy();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {

        reportStrategy.successReport( "The test succeeded",context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        reportStrategy.failureReport("The test failed "+ cause,
                context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        reportStrategy.abortingReport("The test aborted "+ cause,
                context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        reportStrategy.disabledReport( "The test disabled "+reason,
                context.getDisplayName());
    }
}

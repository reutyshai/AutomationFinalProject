package Report;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestWatcherExtension implements TestWatcher {
    ReportHandler reportStrategy;

    @Override
    public void testSuccessful(ExtensionContext context) {
        reportStrategy.successReport(context.getDisplayName()+" succeed");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
    }
}

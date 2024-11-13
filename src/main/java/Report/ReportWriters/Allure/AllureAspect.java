package Report.ReportWriters.Allure;

import Report.Extensions.LifeCycleExtension;
import io.qameta.allure.Allure;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for enhancing Allure reports with additional annotations and steps during test execution.
 * Integrates seamlessly with JUnit 5 tests to add dynamic metadata to Allure reports.
 */
@Aspect
public class AllureAspect {

    /**
     * Pointcut that matches the execution of methods annotated with JUnit's {@code @Test}.
     * Targets all test methods in the project.
     */
    @Pointcut("execution(@org.junit.jupiter.api.Test * *(..))")
    public void testMethods() {
        // Pointcut for methods annotated with @Test.
    }

    /**
     * Advice that runs before the execution of any method matched by the {@code testMethods} pointcut.
     * Adds a step to the Allure report using the test class name obtained from the lifecycle extension.
     */
    @Before("testMethods()")
    public void addAllureAnnotations() {
        // Logs the test class name and adds it as a step in the Allure report.
        System.out.println(LifeCycleExtension.testClassName);
        Allure.step(LifeCycleExtension.testClassName);
    }
}

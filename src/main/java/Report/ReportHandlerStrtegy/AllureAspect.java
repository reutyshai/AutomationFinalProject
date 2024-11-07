//package Report;
//
//import Extension.LifeCycleExtension;
//import io.qameta.allure.Allure;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//@Aspect
//public class AllureAspect {
//
//    @Pointcut("execution(@org.junit.jupiter.api.Test * *(..))")
//    public void testMethods() {}
//    @Before("testMethods()")
//    public void addAllureAnnotations() {
//        System.out.println(LifeCycleExtension.testClassName);
//        Allure.step(LifeCycleExtension.testClassName);
//    }
//}

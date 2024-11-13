import DriverFactory.WebDriverBrowserManager;
import DriverFactory.WebDriverExtension;
import Report.Extensions.LifeCycleExtension;
import Report.Extensions.TestWatcherExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ExtendWith({TestWatcherExtension.class, LifeCycleExtension.class, WebDriverExtension.class})

public class PracticeTest {
    static WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = WebDriverBrowserManager.getDriver("driver"); // משתמשים בשיטה מתוך המחלקה המנוהלת
    }


    @Test
    public void test01(){
        String placeholder=driver.findElement(By.id("user-name")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder,"Usernam");
    }

    @Test
    public void test02(){
        Assertions.assertEquals(1,1);
    }

}

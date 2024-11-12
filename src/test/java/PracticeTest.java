//import Report.LifeCycleExtension;
import Report.LifeCycleExtension;
import Report.TestWatcherExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TestWatcherExtension.class, LifeCycleExtension.class})

public class PracticeTest {

    @Test
    public void test01(){
        Assertions.assertEquals(1,2);
    }
    @Test
    public void test02(){
        Assertions.assertEquals(1,1);
    }
    @Test
    public void test03(){
        Assertions.assertEquals(1,1);
    }
    @Test
    public void test04(){
        Assertions.assertEquals(1,2);
    }
}

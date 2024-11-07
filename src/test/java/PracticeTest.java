import Report.TestWatcherExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestWatcherExtension.class)
public class PracticeTest {

    @Test
    public void test(){
        Assertions.assertEquals(1,1);
    }
}

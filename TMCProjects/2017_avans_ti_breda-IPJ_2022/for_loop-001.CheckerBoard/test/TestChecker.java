import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;



@Points("for_loop-1")
public class TestChecker {
    @Rule
    public MockStdio io = new MockStdio();

    
    @Test
    public void test() {
        callMain(Checkerboard.class);
        String out = io.getSysOut();

        assertTrue("You did not output the checkerboard", out.contains("# # # # # \n" +
" # # # # #\n" +
"# # # # # \n" +
" # # # # #\n" +
"# # # # # \n" +
" # # # # #\n" +
"# # # # # \n" +
" # # # # #\n" +
"# # # # # \n" +
" # # # # #"));

    }
    
    
    private void callMain(Class kl) {
        try {
            kl = ReflectionUtils.newInstanceOfClass(kl);
            String[] t = null;
            String x[] = new String[0];
            Method m = ReflectionUtils.requireMethod(kl, "main", x.getClass());
            ReflectionUtils.invokeMethod(Void.TYPE, m, null, (Object) x);
        } catch (NoSuchElementException e) {
            fail("remember to exit the loop when user enters -1");
        } catch (Throwable e) {
            fail("Something unexpected happened, more info: +e");
        }
    }

}

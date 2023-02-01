
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;


@Points("assignments-2-1")
public class MultipleTest {
    @Rule
    public MockStdio io = new MockStdio();

    @Test
    public void test()
    {
        testNumber(10, 23);
        testNumber(50, 534);
        testNumber(100, 2318);
        testNumber(1000, 233168);
    }
    
    public void testNumber(int number, int result) {
        io.disable();
        io.enable();
        io.setSysIn(number + "\n");
        Reflex.ClassRef<Object> clazz = Reflex.reflect("Multiples");
 
        try {
            clazz.staticMethod("main").returningVoid().taking(String[].class).invoke(null);
        } catch(Throwable e) {
            e.printStackTrace();
        }
        String out = io.getSysOut();
        
        Assert.assertTrue("Output is not correct. Expected output of " + result + " with input of " +number + ", but this was not found in " + out, out.contains(result+""));
    }

}

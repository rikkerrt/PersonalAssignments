
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

@Points("access-modifiers-1.2")
public class TestRobot {
    
    
    @Test
    public void testMouseClick()
    {
        TestHelper.testClassExists("actions.MouseClick");
        TestHelper.testMethodCount("actions.MouseClick", 1);
        Reflex.ClassRef<Object> action = Reflex.reflect("actions.MouseClick");
        assertTrue("Class MouseClick does not have a method perform", action.method("perform").returningVoid().takingNoParams().withNiceError().exists());
    }
    
    @Test
    public void testMouseMove()
    {
        TestHelper.testClassExists("actions.MouseMove");
        TestHelper.testMethodCount("actions.MouseMove", 1);
        Reflex.ClassRef<Object> action = Reflex.reflect("actions.MouseMove");
        assertTrue("Class MouseMove does not have a proper constructor", action.constructor().taking(int.class, int.class).withNiceError().exists());
        assertTrue("Class MouseMove does not have a method perform", action.method("perform").returningVoid().takingNoParams().withNiceError().exists());
    }
    @Test
    public void testWait()
    {
        TestHelper.testClassExists("actions.Wait");
        TestHelper.testMethodCount("actions.Wait", 1);
        Reflex.ClassRef<Object> action = Reflex.reflect("actions.Wait");
        assertTrue("Class Wait does not have a proper constructor", action.constructor().taking(int.class).withNiceError().exists());
        assertTrue("Class Wait does not have a method perform", action.method("perform").returningVoid().takingNoParams().withNiceError().exists());
    }
}

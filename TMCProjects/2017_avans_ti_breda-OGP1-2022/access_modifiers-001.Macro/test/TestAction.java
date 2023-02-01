
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Modifier;
import nl.avans.testhelper.TestHelper;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


@Points("access-modifiers-1.1")
public class TestAction {
    @Test
    public void testAction()
    {
        TestHelper.testClassExists("actions.Action");
        TestHelper.testMethodCount("actions.Action", 3);
        
        TestHelper.testAttribute("actions.Action", "delay", int.class);
        
        Reflex.ClassRef<Object> Action = Reflex.reflect("actions.Action");
        
        assertTrue("Class actions.Action should be abstract", Modifier.isAbstract(Action.cls().getModifiers()));

        assertTrue("Method actions.Action.perform was not found", Action.method("perform").returningVoid().takingNoParams().exists());
        assertTrue("Method actions.Action.perform should be protected", Action.method("perform").returningVoid().takingNoParams().isProtected());
        assertTrue("Method actions.Action.perform should be abstract", Modifier.isAbstract(Action.method("perform").returningVoid().takingNoParams().getMethod().getModifiers()));

        assertTrue("Method actions.Action.performAndWait was not found", Action.method("performAndWait").returningVoid().takingNoParams().exists());
        assertTrue("Method actions.Action.performAndWait should be public", Action.method("performAndWait").returningVoid().takingNoParams().isPublic());
        assertFalse("Method actions.Action.performAndWait should not be abstract", Modifier.isAbstract(Action.method("performAndWait").returningVoid().takingNoParams().getMethod().getModifiers()));
    }
}

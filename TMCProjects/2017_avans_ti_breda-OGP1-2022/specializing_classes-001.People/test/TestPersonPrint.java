
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


@Points("specializing-classes-1.2")
public class TestPersonPrint {
    Reflex.ClassRef<Object> Person;

    @Before
    public void init() {
        Person = Reflex.reflect("Person");
    }

    @Test
    public void testMethod()
    {
//        TestHelper.testMethodExists("Person", "printPerson");
        TestHelper.testMethodCount("Person", 1);

        Object person = null;
        try {
            person = this.Person.constructor().taking(String.class, int.class).invoke("Johan", 123);
        } catch (Throwable ex) {
            Logger.getLogger(TestPersonPrint.class.getName()).log(Level.SEVERE, null, ex);
            assertTrue("Coult not create object", false);
        }
        
        String[] output = TestHelper.callVoidMethod("Person", "printPerson", person);
        
        TestHelper.testContains(output, "Name:");
        TestHelper.testContains(output, "Johan");
        TestHelper.testContains(output, "Age:");
        TestHelper.testContains(output, "123");
    }
    
}

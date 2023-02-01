
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import java.util.Arrays;
import nl.avans.testhelper.TestHelper;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


@Points("specializing-classes-1.1")
public class TestPerson {
    Reflex.ClassRef<Object> Person;

    @Before
    public void init() {
        Person = Reflex.reflect("Person");
    }

    @Test
    public void testClass()
    {
        TestHelper.testClassExists("Person");
    }
    
    @Test
    public void testAttributes()
    {
        TestHelper.testAttribute("Person", "name", String.class);
        TestHelper.testAttribute("Person", "age", int.class);
        TestHelper.testAttributeCount("Person", 2);
    }    
    
    @Test
    public void testConstructor()
    {
        Reflex.MethodRef2<Object, Object, String, Integer> constructor = this.Person.constructor().taking(String.class, int.class);
        assertNotNull("Constructor for class Person not found", constructor);
    }
    
    
    
}

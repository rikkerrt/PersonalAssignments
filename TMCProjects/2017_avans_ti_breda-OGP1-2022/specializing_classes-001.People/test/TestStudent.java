
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


@Points("specializing-classes-1.3")
public class TestStudent {
    Reflex.ClassRef<Object> Student;

    @Before
    public void init() {
        Student = Reflex.reflect("Student");
    }

    @Test
    public void testClass()
    {
        TestHelper.testClassExists("Student");
    }
    
    @Test
    public void testAttributes()
    {
        TestHelper.testAttribute("Student", "studentNumber", int.class);
        TestHelper.testAttributeCount("Student", 1);
    }    
    
    @Test
    public void testConstructor()
    {
        Reflex.MethodRef3<Object, Object, String, Integer, Integer> constructor = this.Student.constructor().taking(String.class, int.class, int.class);
        assertNotNull("Constructor for class Student not found", constructor);
    }
    
    @Test
    public void testExtends()
    {
        assertTrue("Class Student should extend Person", this.Student.inherits(Reflex.reflect("Person").cls()));
    }
    
    
    
}

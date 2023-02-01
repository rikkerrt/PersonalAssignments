
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


@Points("specializing-classes-1.4")
public class TestStudentPrint {
    Reflex.ClassRef<Object> Student;

    @Before
    public void init() {
        Student = Reflex.reflect("Student");
    }

    @Test
    public void testMethod()
    {
//        TestHelper.testMethodExists("Person", "printPerson");
        TestHelper.testMethodCount("Student", 1);

        Object person = null;
        try {
            person = this.Student.constructor().taking(String.class, int.class, int.class).invoke("Johan", 123, 456);
        } catch (Throwable ex) {
            Logger.getLogger(TestStudentPrint.class.getName()).log(Level.SEVERE, null, ex);
            assertTrue("Coult not create object", false);
        }
        
        String[] output = TestHelper.callVoidMethod("Student", "printStudent", person);
        
        TestHelper.testContains(output, "Name:");
        TestHelper.testContains(output, "Johan");
        TestHelper.testContains(output, "Age:");
        TestHelper.testContains(output, "123");
        TestHelper.testContains(output, "Student number:");
        TestHelper.testContains(output, "456");
    }
    
}

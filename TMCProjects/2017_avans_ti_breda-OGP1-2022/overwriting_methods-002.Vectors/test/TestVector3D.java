
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
import static org.junit.Assert.assertEquals;

@Points("overwriting-methods-2.2")
public class TestVector3D {
    Reflex.ClassRef<Object> Vector3D;

    @Before
    public void init() {
        Vector3D = Reflex.reflect("Vector3D");
    }

    @Test
    public void testClass()
    {
        TestHelper.testClassExists("Vector3D");
    }
    
    @Test
    public void testAttributes()
    {
        TestHelper.testAttribute("Vector3D", "z", double.class);
        TestHelper.testAttributeCount("Vector3D", 1);
    }    
    
    @Test
    public void testConstructor()
    {
        Reflex.MethodRef3<Object, Object, Double, Double, Double> constructor = this.Vector3D.constructor().taking(double.class, double.class, double.class);
        assertNotNull("Constructor for class Vector3D not found", constructor);
    }
    
    @Test
    public void testLength() throws Throwable
    {
        Reflex.MethodRef3<Object, Object, Double, Double, Double> constructor = this.Vector3D.constructor().taking(double.class, double.class, double.class);
        Reflex.MethodRef0<Object, Double> getLength = this.Vector3D.method("getLength").returning(double.class).takingNoParams();


        Object v1 = constructor.invoke(10.0, 10.0, 10.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v1), 17.32050807568877, 0.1);

        Object v2 = constructor.invoke(0.0, 0.0, 0.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v2), 0, 0.1);        

        Object v3 = constructor.invoke(10.0, 0.0, 0.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v3), 10, 0.1);
    }
    
    
    
}

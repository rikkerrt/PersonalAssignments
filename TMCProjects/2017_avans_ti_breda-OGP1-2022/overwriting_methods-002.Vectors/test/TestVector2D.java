
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import java.util.Arrays;
import nl.avans.testhelper.TestHelper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


@Points("overwriting-methods-2.1")
public class TestVector2D {
    Reflex.ClassRef<Object> Vector2D;

    @Before
    public void init() {
        Vector2D = Reflex.reflect("Vector2D");
    }

    @Test
    public void testClass()
    {
        TestHelper.testClassExists("Vector2D");
    }
    
    @Test
    public void testAttributes()
    {
        TestHelper.testAttribute("Vector2D", "x", double.class);
        TestHelper.testAttribute("Vector2D", "y", double.class);
        TestHelper.testAttributeCount("Vector2D", 2);
    }    
    
    @Test
    public void testConstructor()
    {
        Reflex.MethodRef2<Object, Object, Double, Double> constructor = this.Vector2D.constructor().taking(double.class, double.class);
        assertNotNull("Constructor for class Vector2D not found", constructor);
    }
    
    @Test
    public void testLength() throws Throwable
    {
        Reflex.MethodRef2<Object, Object, Double, Double> constructor = this.Vector2D.constructor().taking(double.class, double.class);
        Reflex.MethodRef0<Object, Double> getLength = this.Vector2D.method("getLength").returning(double.class).takingNoParams();


        Object v1 = constructor.invoke(10.0, 10.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v1), 14.14213562373095, 0.1);

        Object v2 = constructor.invoke(0.0, 0.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v2), 0.0, 0.1);        

        Object v3 = constructor.invoke(10.0, 0.0);
        assertEquals("getLength does not return the right value", getLength.invokeOn(v3), 10.0, 0.1);
    }
    
    
    
}

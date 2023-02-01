
package Configuration;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Etienne
 */
public class ConfigurationTest {
    Reflex.ClassRef<Object> Configuration;
    private ArrayList<String> attributes = new ArrayList<>();

    @Before
    public void init() {
        Configuration = Reflex.reflect("Configuration.Configuration");
      
        attributes.add("cpu");
        attributes.add("gpu");
        attributes.add("memory");
        attributes.add("owner");
    }
    
    @Test
    @Points("gui_extra-4.1")
    public void testClass()
    {
        assertTrue("Class Configuratuin should be public", this.Configuration.isPublic());
    }
    
    @Test
    @Points("gui_extra-4.1")
    public void testAttributes() {
        Field[] fields = ReflectionUtils.findClass("Configuration.Configuration").getDeclaredFields();

        for(Field field : fields){
            if (!attributes.contains(field.getName().toString()))
                continue;
            
            assertTrue("field " + field.getName() + " in Configuration should be private", field.toString().contains("private"));
            assertFalse("field " + field.getName() + " in Configuration should be static", field.toString().contains("static"));
            assertFalse("field " + field.getName() + " in Configuration should be final", field.toString().contains("final"));
            assertTrue("field " + field.getName() + " in Configuration should be of datatype SimpleStringProperty", field.getType().toString().contains("SimpleStringProperty"));
        }
        
        // Check fields + datatype
        for (String neededField : attributes) {
            assertTrue("attribute " + neededField + " is missing",  Arrays.stream(fields).anyMatch(f -> f.toString().contains(neededField)));
        }
    }
    
        @Test
    @Points("gui_extra-4.1")
    public void testGettersSetters() throws Throwable {
        Object config = this.Configuration.constructor().taking(String.class, String.class, String.class, String.class).invoke("i7", "GT710", "8GB", "Me");
        assertNotNull("Custructor of class Configuration does not work", config);
        
        // getters
        assertTrue("getCpu in class Configuration is not working properly", this.Configuration.method("getCpu").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("i7"));
        assertTrue("getGpu in class Configuration is not working properly", this.Configuration.method("getGpu").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("GT710"));
        assertTrue("getMemory in class Configuration is not working properly", this.Configuration.method("getMemory").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("8GB"));
        assertTrue("getOwner in class Configuration is not working properly", this.Configuration.method("getOwner").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("Me"));
        
        // Setters
        this.Configuration.method("setCpu").returningVoid().taking(String.class).invokeOn(config, "i9");
        this.Configuration.method("setGpu").returningVoid().taking(String.class).invokeOn(config, "GTX990");
        this.Configuration.method("setMemory").returningVoid().taking(String.class).invokeOn(config, "16GB");
        this.Configuration.method("setOwner").returningVoid().taking(String.class).invokeOn(config, "Etienne");
        
        assertTrue("setCpu in class Configuration is not working properly", this.Configuration.method("getCpu").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("i9"));
        assertTrue("setGpu in class Configuration is not working properly", this.Configuration.method("getGpu").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("GTX990"));
        assertTrue("setMemory in class Configuration is not working properly", this.Configuration.method("getMemory").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("16GB"));
        assertTrue("setOwner in class Configuration is not working properly", this.Configuration.method("getOwner").returning(String.class).takingNoParams().withNiceError().invokeOn(config).equals("Etienne"));
    }
    
    @Test
    @Points("gui_extra-4.1")
    public void testClassParameters()
    {
        assertTrue("Class Person should be public", this.Configuration.isPublic());
    }
}

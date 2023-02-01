
import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;

@Points("extra1")
public class TestOpgave1 {

    @Rule
    public MockStdio io = new MockStdio();

    Reflex.ClassRef<Object> huurderKlasse;

    @Before
    public void init() {
        huurderKlasse = Reflex.reflect("Huurder");

    }

    public void classPublic() {
        assertTrue("class Huurder should be public, define it as follows\npublic class Huurder{...\n}", huurderKlasse.isPublic());
    }

    @Test
    public void noExtraVariables() {
        sanityCheck();
    }
    
    @Test
    public void checkAttributes()
    {
        List<Field> fields = Arrays.asList(ReflectionUtils.findClass("Huurder").getDeclaredFields());

        
        assertTrue("Class Huurder should contain an attribute called naam", 
                    fields.stream().anyMatch(f -> f.getName().equals("naam")));
        assertTrue("Class Huurder should contain an attribute called maxHuur", 
                    fields.stream().anyMatch(f -> f.getName().equals("maxHuur")));
        
        assertTrue("Attribute Huurder.naam should be String", 
                    fields.stream().anyMatch(f -> f.getName().equals("naam") && f.getType().equals(String.class)));
        assertTrue("Attribute Huurder.maxHuur should be double", 
                    fields.stream().anyMatch(f -> f.getName().equals("maxHuur") && f.getType().equals(Double.TYPE)));
        
    }
    

    private void sanityCheck() throws SecurityException {
        Field[] kentat = ReflectionUtils.findClass("Huurder").getDeclaredFields();

        for (Field field : kentat) {
            assertFalse("does not need \"static variables\", remove " + kentta(field.toString()), field.toString().contains("static") && !field.toString().contains("final"));
            assertTrue("all the instance variables should be private, please change " + kentta(field.toString()), field.toString().contains("private"));
        }

        if (kentat.length > 1) {
            int var = 0;
            for (Field field : kentat) {
                if (!field.toString().contains("final")) {
                    var++;
                }
            }
            assertTrue("The class Huurder"
                    + " does not need other object variables than those for naam and maxHuur, "
                    + "remove others", var < 3);
        }
    }

    private String kentta(String toString) {
        return toString.replace("Huurder.", "").replace("java.lang.", "");
    }

}


import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extra4")
public class TestOpgave4 {

    @Rule
    public MockStdio io = new MockStdio();

    Reflex.ClassRef<Object> kamerKlasse;

    @Before
    public void init() {
        kamerKlasse = Reflex.reflect("Kamer");

    }

    
    @Test
    public void methodsAvailable()
    {
        assertTrue("Kamer has to have a verhoogPercentage method with a double parameter", kamerKlasse.method("verhoogPercentage").returningVoid().taking(Double.TYPE).withNiceError().isPublic());
        assertTrue("Kamer has to have a verhoogVast method with a double parameter", kamerKlasse.method("verhoogVast").returningVoid().taking(Double.TYPE).withNiceError().isPublic());
    }
    
    @Test
    public void testVerhoogVast() throws Throwable
    {
        Kamer kamer = (Kamer)kamerKlasse.constructor().taking(double.class, double.class, double.class, double.class).invoke(10.0,10.0,10.0,100.0);
        try {
            kamerKlasse.method(kamer, "verhoogVast")
                    .returningVoid()
                    .taking(double.class).invoke(10.0);
            assertTrue("verhoogVast is not increasing the price properly", Math.abs(kamer.getPrijs() - 110.0) < 0.01);
            kamerKlasse.method(kamer, "verhoogVast")
                    .returningVoid()
                    .taking(double.class).invoke(10.0);
            assertTrue("verhoogVast is not increasing the price properly", Math.abs(kamer.getPrijs() - 120.0) < 0.01);
        } catch (AssertionError ae) {
            fail("Fail: " + ae + "\n add verhoogVast method to Kamer");
        }
        
    }
    
    @Test
    public void testVerhoogPercentage() throws Throwable
    {
        Kamer kamer = (Kamer)kamerKlasse.constructor().taking(double.class, double.class, double.class, double.class).invoke(10.0,10.0,10.0,100.0);
        try {
            kamerKlasse.method(kamer, "verhoogPercentage")
                    .returningVoid()
                    .taking(double.class).invoke(10.0);
            assertTrue("verhoogPercentage is not increasing the price properly", Math.abs(kamer.getPrijs() - 110.0) < 0.01);
            kamerKlasse.method(kamer, "verhoogPercentage")
                    .returningVoid()
                    .taking(double.class).invoke(100.0);
            assertTrue("verhoogPercentage is not increasing the price properly", Math.abs(kamer.getPrijs() - 220.0) < 0.01);
        } catch (AssertionError ae) {
            fail("Fail: " + ae + "\n add verhoogPercentage method to Kamer");
        }
        
    }

}

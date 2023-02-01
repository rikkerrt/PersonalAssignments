
import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extra5")
public class TestOpgave5 {

    @Rule
    public MockStdio io = new MockStdio();

    Reflex.ClassRef<Object> huurderKlasse;

    @Before
    public void init() {
        huurderKlasse = Reflex.reflect("Huurder");

    }

    @Test
    public void testOutput() throws Throwable {
        Object huurder = huurderKlasse.constructor().taking(String.class, double.class).invoke("Johan", 500.0);

        huurderKlasse.method("print").returningVoid().takingNoParams().invokeOn(huurder);
        String out = io.getSysOut();
        
        assertTrue("De output should contain the name of the Huurder", out.contains("Johan"));
        assertTrue("De output should contain the maxHuur of the Huurder", out.contains("500"));
    }

}


import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extra3")
public class TestOpgave3 {

    @Rule
    public MockStdio io = new MockStdio();

    Reflex.ClassRef<Object> huurderKlasse;

    @Before
    public void init() {
        huurderKlasse = Reflex.reflect("Huurder");

    }

    @Test
    public void stConstructor() throws Throwable {
        Reflex.MethodRef2<Object, Object, String, Double> cc = huurderKlasse.constructor().taking(String.class, double.class).withNiceError();
        assertTrue("Add a constructor to class Huurder"
                + ": public Huurder(String naam, double maxHuur)", cc.isPublic());
        cc.invoke("Johan", 500.0);
    }
    
    @Test
    public void emptyConstructor() throws Throwable {
        Reflex.MethodRef0<Object, Object> cc = huurderKlasse.constructor().takingNoParams().withNiceError();
        assertTrue("Add a constructor to class Huurder"
                + ": public Huurder()", cc.isPublic());
        Object huurder = cc.invoke();
        
        assertNotNull("Huurder constructor is not setting the naam attribute", huurderKlasse.method("getNaam").returning(String.class).takingNoParams().invokeOn(huurder));
        assertTrue("Huurder constructor is not setting the maxHuur, or set it to 0", huurderKlasse.method("getMaxHuur").returning(double.class).takingNoParams().invokeOn(huurder) != 0);
    }


}

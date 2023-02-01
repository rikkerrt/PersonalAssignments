
import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extra6")
public class TestOpgave6 {

    Reflex.ClassRef<Object> huurderKlasse;

    @Before
    public void init() {
        huurderKlasse = Reflex.reflect("Huurder");
    }

    @Test
    public void hasMethod() throws Throwable {
        assertTrue("Methode kanHurenhas not been added", huurderKlasse.method("kanHuren").returning(boolean.class).taking(Kamer.class).withNiceError().isPublic());
    }

    @Test
    public void methodWorks() throws Throwable {
        Kamer kamer1 = new Kamer(10,10,10,550);
        Kamer kamer2 = new Kamer(10,10,10,500);
        Kamer kamer3 = new Kamer(10,10,10,450);
        
        Object huurder = huurderKlasse.constructor().taking(String.class, double.class).invoke("Johan", 500.0);
        try
        {
            assertFalse("Method kanHuren is not giving the proper result", huurderKlasse.method("kanHuren").returning(boolean.class).taking(Kamer.class).invokeOn(huurder, kamer1));
            assertTrue("Method kanHuren is not giving the proper result", huurderKlasse.method("kanHuren").returning(boolean.class).taking(Kamer.class).invokeOn(huurder, kamer2));
            assertTrue("Method kanHuren is not giving the proper result", huurderKlasse.method("kanHuren").returning(boolean.class).taking(Kamer.class).invokeOn(huurder, kamer3));
        }catch(Exception e)
        {
            fail("Methode kanHuren has not been added");
        }
        
    }

}

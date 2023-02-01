
import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extra2")
public class TestOpgave2 {

    @Rule
    public MockStdio io = new MockStdio();

    Reflex.ClassRef<Object> huurderKlasse;

    @Before
    public void init() {
        huurderKlasse = Reflex.reflect("Huurder");

    }

    @Test
    public void testGetters()
    {
        assertTrue("Huurder has to have a getNaam() getter", huurderKlasse.method("getNaam").returning(String.class).takingNoParams().withNiceError().isPublic());
        assertTrue("Huurder has to have a getMaxHuur() getter", huurderKlasse.method("getMaxHuur").returning(double.class).takingNoParams().withNiceError().isPublic());
    }
}

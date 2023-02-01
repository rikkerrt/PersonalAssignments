
import org.junit.Test;
import org.junit.Rule;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Before;

@Points("extras7")
public class TestOpgave7 {

    @Rule
    public MockStdio io = new MockStdio();

    @Test
    public void testOutput() {
        Main.main(new String[0]);
        String[] out = io.getSysOut().trim().split("\n");
        assertTrue("Your program should output 3 lines", out.length == 3);
        assertTrue("First line of output should be 'De huurder kan de kamer niet huren'", out[0].equals("De huurder kan de kamer niet huren"));
        assertTrue("Second line of output should be 'De huurder kan de kamer huren'", out[1].equals("De huurder kan de kamer huren"));
        assertTrue("Third line of output should be 'De huurder kan de kamer niet huren'", out[2].equals("De huurder kan de kamer niet huren"));
    }

}

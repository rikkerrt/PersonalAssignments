import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

public class Opgave1a {
    private Reflex.ClassRef<Object> Song;

    @Before
    public void init() {
        Song = Reflex.reflect("Song");
    }

    @Test
    @Points("1")
    public void testClass()
    {
        TestHelper.testClassExists("Song");
    }
    @Test
    @Points("1")
    public void testPrivate()
    {
        Field[] fields = this.Song.cls().getDeclaredFields();
        for(Field field : fields) {
            assertTrue( Modifier.isPrivate(field.getModifiers()) );
        }
    }
    @Test
    @Points("1")
    public void testName()
    {
        TestHelper.testAttributeExists("Song", "title");
        TestHelper.testAttributeExists("Song", "length");
        TestHelper.testAttributeExists("Song", "genre");
    }
    @Test
    @Points("1")
    public void testType()
    {
        TestHelper.getAttribute("Song", "title", String.class);
        TestHelper.getAttribute("Song", "length", int.class);
        TestHelper.getAttribute("Song", "genre", String.class);
    }

}

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class Opgave1b {
    private Reflex.ClassRef<Object> Song;

    @Before
    public void init() {
        Song = Reflex.reflect("Song");
    }

    @Test
    @Points("2")
    public void testConstructorWithoutParameters()
    {
        Constructor<?>[] constructors = this.Song.cls().getConstructors();
        boolean found = false;

        for(Constructor<?> constructor : constructors)
            if(constructor.getParameterCount() == 0)
                found = true;

        Assert.assertTrue( found);
    }

    @Test
    @Points("1")
    public void testConstructorParameterTypes()
    {
        Reflex.MethodRef0 constructor = this.Song.constructor().takingNoParams();
        Assert.assertTrue( constructor.exists());
    }

    @Test
    @Points("2")
    public void testConstructorInitialisation() throws Throwable {
        Reflex.MethodRef0 constructor = this.Song.constructor().takingNoParams();
        Assert.assertTrue( constructor.exists());

        Object song = constructor.invoke();
        TestHelper.testAttributeValue("Song", "title", String.class, song, "");
        TestHelper.testAttributeValue("Song", "length", int.class, song, -1);
        TestHelper.testAttributeValue("Song", "genre", String.class, song, "");
    }

}

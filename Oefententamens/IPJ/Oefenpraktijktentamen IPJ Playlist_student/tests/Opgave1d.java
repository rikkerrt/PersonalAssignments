import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Before;
import org.junit.Test;

public class Opgave1d {
	private Reflex.ClassRef<Object> Song;
	@Before
	public void init() {
		Song = Reflex.reflect("Song");
	}

	@Test
	@Points("1")
	public void testGetters()
	{
		TestHelper.testGetter("Song", "title", String.class);
		TestHelper.testGetter("Song", "length", int.class);
		TestHelper.testGetter("Song", "genre", String.class);
	}


}

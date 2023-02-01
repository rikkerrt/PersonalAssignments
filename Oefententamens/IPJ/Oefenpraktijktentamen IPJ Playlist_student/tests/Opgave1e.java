import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class Opgave1e {
	private Reflex.ClassRef<Object> Song;
	@Before
	public void init() {
		Song = Reflex.reflect("Song");
	}


	@Points("2")
	@Test
	public void testToStringExist()
	{
		Method[] methods = this.Song.cls().getDeclaredMethods();
		boolean found = false;
		for(Method method : methods) {
			if(	method.getName().equals("toString") &&
				method.getReturnType().equals(String.class) &&
				method.getParameterCount() == 0)
				found = true;
		}
		Assert.assertTrue( found);
	}

	@Points("3")
	@Test
	public void testToStringReturn() throws Throwable {
		Object song = this.Song.constructor().taking(String.class, int.class, String.class).invoke("IPJ", 123, "OGP");

		String toString = song.toString();
		Assert.assertTrue( toString.contains("IPJ"));
		Assert.assertTrue( toString.contains("123"));
		Assert.assertTrue( toString.contains("OGP"));
	}
}

import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Helper {

	static Object getParlement() throws Throwable {
		Reflex.ClassRef<Object> Coalitie = Reflex.reflect("Coalitie");
		Reflex.ClassRef<Object> Partij = Reflex.reflect("Partij");
		Reflex.ClassRef<Object> Parlement = Reflex.reflect("Parlement");

		Object parlement = Parlement.constructor().takingNoParams().invoke();
		Object fr1 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR1", 14, 0);
		Object fr2 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR2", 12, 1);
		Object fr3 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR3", 11, 2);
		Object fr4 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR4", 10, 3);
		Object fr5 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR5", 10, 4);
		Object fr6 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR6", 9, 5);
		Object fr7 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR7", 6, 6);
		Object fr8 = Partij.constructor().taking(String.class, int.class, int.class).invoke("FR8", 3, 7);

		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr1);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr2);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr3);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr4);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr5);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr6);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr7);
		Parlement.method("voegPartijToe").returningVoid().taking(Partij.cls()).invokeOn(parlement, fr8);

		return parlement;
	}

	static Object getPartij(Object parlement, int index) throws IllegalAccessException
	{
		Reflex.ClassRef<Object> Parlement = Reflex.reflect("Parlement");
		Field partijenAttr = TestHelper.getAttribute("Parlement", "partijen", ArrayList.class);
		partijenAttr.setAccessible(true);
		ArrayList partijen = (ArrayList) partijenAttr.get(parlement);
		partijenAttr.setAccessible(false);
		return partijen.get(index);
	}
	static Object getCoalitie(Object parlement, int index) throws IllegalAccessException
	{
		Reflex.ClassRef<Object> Parlement = Reflex.reflect("Parlement");
		Field partijenAttr = TestHelper.getAttribute("Parlement", "coalities", ArrayList.class);
		partijenAttr.setAccessible(true);
		ArrayList partijen = (ArrayList) partijenAttr.get(parlement);
		partijenAttr.setAccessible(false);
		return partijen.get(index);
	}

}

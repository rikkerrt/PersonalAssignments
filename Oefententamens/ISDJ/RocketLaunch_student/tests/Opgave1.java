import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class Opgave1
{
    private Reflex.ClassRef<Object> StudentNummer;

    @Before
    public void init() {
        StudentNummer = Reflex.reflect("StudentNummer");
    }


    @Points("0")
    @Test
    public void testNumberFilledIn()
    {
        int number = 0;
        try {
            Field[] fields = this.StudentNummer.cls().getDeclaredFields();
            for(Field field : fields) {
                if(field.getName().equals("studentNummer")) {
                    number = field.getInt(null);
                }
            }
        } catch (IllegalAccessException e) {
            Assert.fail(e.toString());
        }
        Assert.assertNotEquals("Student number not filled in", number, 123456);
    }
}

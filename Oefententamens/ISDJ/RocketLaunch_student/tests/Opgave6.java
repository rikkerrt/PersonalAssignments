import fi.helsinki.cs.tmc.edutestutils.Reflex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Opgave6
{
    private Reflex.ClassRef<Object> Stage;
    private Reflex.ClassRef<Object> AcceleratingObject;

    @Before
    public void init() {
        Stage = Reflex.reflect("Stage");
        AcceleratingObject = Reflex.reflect("AcceleratingObject");
    }

    @Test
    public void testInterface()
    {
        Assert.assertTrue( Stage.cls().isInterface());
    }

    @Test
    public void testMethods()
    {
        Assert.assertTrue( Stage.method("fire").returningVoid().taking(this.AcceleratingObject.cls()).exists());
        Assert.assertTrue( Stage.method("isExhausted").returning(boolean.class).takingNoParams().exists());
    }
}

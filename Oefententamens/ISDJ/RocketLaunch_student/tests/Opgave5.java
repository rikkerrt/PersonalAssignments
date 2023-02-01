import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class Opgave5
{

    private Reflex.ClassRef<Object> AcceleratingObject;

    @Before
    public void init() {
        AcceleratingObject = Reflex.reflect("AcceleratingObject");
    }


    @Test
    public void classExists()
    {
        Assert.assertNotNull( this.AcceleratingObject);
    }

    @Points("2")
    @Test
    public void checkAttributes()
    {
        TestHelper.testAttribute("AcceleratingObject", "velocity", double.class);
        TestHelper.testAttribute("AcceleratingObject", "height", double.class);
        TestHelper.testAttributeCount("AcceleratingObject", 2);
    }

    @Test
    public void testConstructor()
    {
        Assert.assertTrue( this.AcceleratingObject.constructor().takingNoParams().exists());
    }


    @Points("3")
    @Test
    public void testMethods() throws Throwable
    {
        testSetVelocity();
        testIncreaseVelocity();
        testGetHeight();
        testReset();
        testUpdate();
    }

    public void testSetVelocity() throws Throwable
    {
        TestHelper.testSetter("AcceleratingObject", "velocity", double.class);

        Object acceleratingObject = this.AcceleratingObject.constructor().takingNoParams().invoke();
        this.AcceleratingObject.method("setVelocity").returningVoid().taking(double.class).invokeOn(acceleratingObject, 10.0);
        Field velocity = TestHelper.getAttribute(this.AcceleratingObject.cls(), "velocity", double.class);
        velocity.setAccessible(true);
        Assert.assertEquals( 10.0, velocity.get(acceleratingObject));
        velocity.setAccessible(false);
    }

    public void testIncreaseVelocity() throws Throwable
    {
        TestHelper.testSetter("AcceleratingObject", "velocity", double.class);

        Object acceleratingObject = this.AcceleratingObject.constructor().takingNoParams().invoke();
        this.AcceleratingObject.method("increaseVelocity").returningVoid().taking(double.class).invokeOn(acceleratingObject, 10.0);
        Field velocity = TestHelper.getAttribute(this.AcceleratingObject.cls(), "velocity", double.class);
        velocity.setAccessible(true);
        Assert.assertEquals( 10.0, velocity.get(acceleratingObject));

        this.AcceleratingObject.method("increaseVelocity").returningVoid().taking(double.class).invokeOn(acceleratingObject, 10.0);
        Assert.assertEquals( 20.0, velocity.get(acceleratingObject));

        velocity.setAccessible(false);
    }


    public void testGetHeight() throws Throwable
    {
        TestHelper.testGetter("AcceleratingObject", "height", double.class);

        Object acceleratingObject = this.AcceleratingObject.constructor().takingNoParams().invoke();

        Field height = TestHelper.getAttribute(this.AcceleratingObject.cls(), "height", double.class);
        height.setAccessible(true);

        double returnedValue = this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 0.0, returnedValue, 0.0001);

        height.set(acceleratingObject, 10.0);
        returnedValue = this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 10.0, returnedValue, 0.0001);

        height.setAccessible(false);
    }

    public void testReset() throws Throwable
    {
        Object acceleratingObject = this.AcceleratingObject.constructor().takingNoParams().invoke();
        Field velocity = TestHelper.getAttribute(this.AcceleratingObject.cls(), "velocity", double.class);
        velocity.setAccessible(true);
        velocity.set(acceleratingObject, 10.0);

        Field height = TestHelper.getAttribute(this.AcceleratingObject.cls(), "height", double.class);
        height.setAccessible(true);
        height.set(acceleratingObject, 20.0);

        this.AcceleratingObject.method("reset").returningVoid().takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 0.0, velocity.get(acceleratingObject));
        Assert.assertEquals( 0.0, height.get(acceleratingObject));
        velocity.setAccessible(false);
        height.setAccessible(false);
    }

    public void testUpdate() throws Throwable
    {
        Object acceleratingObject = this.AcceleratingObject.constructor().takingNoParams().invoke();
        this.AcceleratingObject.method("setVelocity").returningVoid().taking(double.class).invokeOn(acceleratingObject, 5.0);

        this.AcceleratingObject.method("update").returningVoid().takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 5.0, (double)this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject), 0.001);
        this.AcceleratingObject.method("update").returningVoid().takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 9.5, (double)this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject), 0.001);
        this.AcceleratingObject.method("update").returningVoid().takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 13.5, (double)this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject), 0.001);
        this.AcceleratingObject.method("update").returningVoid().takingNoParams().invokeOn(acceleratingObject);
        Assert.assertEquals( 17.0, (double)this.AcceleratingObject.method("getHeight").returning(double.class).takingNoParams().invokeOn(acceleratingObject), 0.001);
    }

}

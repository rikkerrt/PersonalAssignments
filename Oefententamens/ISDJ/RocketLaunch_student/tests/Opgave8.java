import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class Opgave8
{
    private Reflex.ClassRef<Object> Stage;
    private Reflex.ClassRef<Object> AcceleratingObject;
    private Reflex.ClassRef<Object> Rocket;
    private Reflex.ClassRef<Object> LiquidFuelStage;
    private Reflex.ClassRef<Object> SolidFuelStage;

    @Before
    public void init() {
        Stage = Reflex.reflect("Stage");
        AcceleratingObject = Reflex.reflect("AcceleratingObject");
        Rocket = Reflex.reflect("Rocket");
        LiquidFuelStage = Reflex.reflect("LiquidFuelStage");
        SolidFuelStage = Reflex.reflect("SolidFuelStage");
    }

    @Points("5")
    @Test
    public void testLiquidStage() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Field velocity = TestHelper.getAttribute(this.Rocket.cls(), "velocity", double.class);
        velocity.setAccessible(true);

        TestHelper.testClassExists("LiquidFuelStage");
        TestHelper.testImplements("LiquidFuelStage", "Stage");

        Object liquidFuelStage = this.LiquidFuelStage.constructor().taking(double.class).invoke(100.0);
        Field fuel = TestHelper.getAttribute(this.LiquidFuelStage.cls(), "fuel", double.class);
        fuel.setAccessible(true);
        Assert.assertEquals( 100.0, fuel.get(liquidFuelStage));
        this.LiquidFuelStage.method("fire").returningVoid().taking(this.AcceleratingObject.cls()).invokeOn(liquidFuelStage, rocket);
        Assert.assertEquals( 90.0, fuel.get(liquidFuelStage));
        Assert.assertEquals( 10.0, velocity.get(rocket));
        this.LiquidFuelStage.method("fire").returningVoid().taking(this.AcceleratingObject.cls()).invokeOn(liquidFuelStage, rocket);
        Assert.assertEquals( 81.0, fuel.get(liquidFuelStage));
        Assert.assertEquals( 10.0, velocity.get(rocket));
        fuel.setAccessible(false);
    }


    @Points("5")
    @Test
    public void testSolidStage() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Field velocity = TestHelper.getAttribute(this.Rocket.cls(), "velocity", double.class);
        velocity.setAccessible(true);


        TestHelper.testClassExists("SolidFuelStage");
        TestHelper.testImplements("SolidFuelStage", "Stage");

        Object liquidFuelStage = this.SolidFuelStage.constructor().taking(int.class).invoke(100);
        Field fuel = TestHelper.getAttribute(this.SolidFuelStage.cls(), "fuel", int.class);
        fuel.setAccessible(true);
        Assert.assertEquals( 100, fuel.get(liquidFuelStage));
        this.SolidFuelStage.method("fire").returningVoid().taking(this.AcceleratingObject.cls()).invokeOn(liquidFuelStage, rocket);
        Assert.assertEquals( 99, fuel.get(liquidFuelStage));
        Assert.assertEquals( 1.0, velocity.get(rocket));
        this.SolidFuelStage.method("fire").returningVoid().taking(this.AcceleratingObject.cls()).invokeOn(liquidFuelStage, rocket);
        Assert.assertEquals( 98, fuel.get(liquidFuelStage));
        Assert.assertEquals( 2.0, velocity.get(rocket));
        fuel.setAccessible(false);
    }
}

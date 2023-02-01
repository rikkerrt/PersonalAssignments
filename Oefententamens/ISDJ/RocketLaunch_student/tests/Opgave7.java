import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import nl.avans.testhelper.TestHelper;
import nl.avans.testhelper.parser.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Opgave7
{
    private Reflex.ClassRef<Object> Stage;
    private Reflex.ClassRef<Object> AcceleratingObject;
    private Reflex.ClassRef<Object> Rocket;

    @Before
    public void init() {
        Stage = Reflex.reflect("Stage");
        AcceleratingObject = Reflex.reflect("AcceleratingObject");
        Rocket = Reflex.reflect("Rocket");
    }

    @Test
    public void testExtends()
    {
        TestHelper.testExtends("Rocket", "AcceleratingObject");
    }

    @Test
    public void testAttributes() throws Throwable
    {
        TestHelper.testAttribute("Rocket", "stages", ArrayList.class);

        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Field stages = TestHelper.getAttribute(this.Rocket.cls(), "stages", ArrayList.class);
        stages.setAccessible(true);
        Assert.assertNotNull( stages.get(rocket));
        stages.setAccessible(false);
    }

    @Test
    public void testAdd() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Field stages = TestHelper.getAttribute(this.Rocket.cls(), "stages", ArrayList.class);
        stages.setAccessible(true);

        Object stage = createStageImplementation();
        this.Rocket.method("add").returningVoid().taking(Stage.cls()).invokeOn(rocket, stage);
        ArrayList stagesValue = (ArrayList) stages.get(rocket);
        Assert.assertEquals( 1, stagesValue.size());
    }


    @Test
    public void testReset() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Field stages = TestHelper.getAttribute(this.Rocket.cls(), "stages", ArrayList.class);
        stages.setAccessible(true);

        Field height = TestHelper.getAttribute(this.Rocket.cls(), "height", double.class);
        height.setAccessible(true);

        Object stage = createStageImplementation();
        this.Rocket.method("add").returningVoid().taking(Stage.cls()).invokeOn(rocket, stage);
        ArrayList stagesValue = (ArrayList) stages.get(rocket);
        Assert.assertEquals( 1, stagesValue.size());
        height.set(rocket, 10.0);

        this.Rocket.method("reset").returningVoid().takingNoParams().invokeOn(rocket);
        Assert.assertEquals( 0, stagesValue.size());
        Assert.assertEquals( 0.0, height.get(rocket));

        TestHelper.parser().getClass("Rocket").getMethod("reset").assertContains(Statement.Super);
    }

    @Test
    public void testLaunch() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();

        boolean hadException = false;
        try
        {
            this.Rocket.method("launch").returningVoid().takingNoParams().invokeOn(rocket);
        } catch(Exception e)
        {
            hadException = true;
        }
        Assert.assertTrue( hadException);

        Object stage = createStageImplementation();
        this.Rocket.method("add").returningVoid().taking(Stage.cls()).invokeOn(rocket, stage);

        methodsCalled.clear();
        firstBurn = true;
        this.Rocket.method("launch").returningVoid().takingNoParams().invokeOn(rocket);
        Assert.assertEquals( 3, methodsCalled.size());
    }

    @Test
    public void testBurnStage() throws Throwable
    {
        Object rocket = this.Rocket.constructor().takingNoParams().invoke();
        Object stage = createStageImplementation();
        methodsCalled.clear();
        firstBurn = true;
        this.Rocket.method("burnStage").returningVoid().taking(Stage.cls()).invokeOn(rocket, stage);
        Assert.assertTrue( this.Rocket.method("burnStage").returningVoid().taking(Stage.cls()).isPrivate());
        Assert.assertEquals( 3, methodsCalled.size());
    }



    ArrayList<Method> methodsCalled = new ArrayList<>();
    boolean firstBurn = true;

    private Object createStageImplementation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        ProxyFactory factory = new ProxyFactory();
        factory.setInterfaces(new Class<?>[] { this.Stage.cls() });
        factory.setFilter(
                new MethodFilter() {
                    @Override
                    public boolean isHandled(Method method) {
                        return Modifier.isAbstract(method.getModifiers());
                    }
                }
        );

        MethodHandler handler = new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
               // System.out.println("Handling " + thisMethod + " via the method handler");
                methodsCalled.add(thisMethod);

                if(thisMethod.getName().equals("isExhausted"))
                {
                    firstBurn = !firstBurn;
                    return firstBurn;
                }

                return null;
            }
        };

        return factory.create(new Class<?>[] { }, new Object[] { }, handler);
    }

}

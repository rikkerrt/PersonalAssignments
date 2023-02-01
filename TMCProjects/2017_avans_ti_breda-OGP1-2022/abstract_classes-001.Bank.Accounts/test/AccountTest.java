
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Points("abstract-classes-1.1")
public class AccountTest {
    
    Reflex.ClassRef<Object> Account;

    @Before
    public void init() {
        Account = Reflex.reflect("Account");
    }
    
    @Test
    public void testClass() {
        TestHelper.testClassExists("Account");
    }
    
    @Test
    public void testAttributes() {
        TestHelper.testAttribute("Account", "name", String.class);
        TestHelper.testAttribute("Account", "amount", double.class);
    }
    
    @Test
    public void testMethods() {
        TestHelper.testMethodCount("Account", 4);
        
        Assert.assertNotNull("Method Account.calculateNextYear not found", this.Account.method("calculateNextYear").returningVoid().takingNoParams().withNiceError());
        Assert.assertNotNull("Method Account.getAmount not found", this.Account.method("getAmount").returning(double.class).takingNoParams().withNiceError());
        Assert.assertNotNull("Method Account.setAmount not found", this.Account.method("setAmount").returningVoid().taking(double.class).withNiceError());
        Assert.assertNotNull("Method Account.toString not found", this.Account.method("toString").returning(String.class).takingNoParams().withNiceError());
        Assert.assertTrue("Method Account.calculateNextYear should be abstract", Modifier.isAbstract(this.Account.method("calculateNextYear").returningVoid().takingNoParams().getMethod().getModifiers()));
       
    }
    
}

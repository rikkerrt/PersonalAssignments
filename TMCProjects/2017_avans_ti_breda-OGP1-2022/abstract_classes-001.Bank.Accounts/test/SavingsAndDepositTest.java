
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import nl.avans.testhelper.TestHelper;
import org.junit.Assert;
import org.junit.Test;


@Points("abstract-classes-1.2")
public class SavingsAndDepositTest {
    
    
    
    @Test
    public void testSavings() throws Throwable
    {
        TestHelper.testClassExists("SavingsAccount");
        TestHelper.testMethodCount("SavingsAccount", 1);
        
        Reflex.ClassRef<Object> Savings = Reflex.reflect("SavingsAccount");
        
        Assert.assertTrue("SavingsAccount should extend Account", Savings.inherits(Reflex.reflect("Account")));
        
        Reflex.MethodRef1<Object, Object, Double> constructor = Savings.constructor().taking(double.class);
        Assert.assertNotNull("SavingsAccount constructor not found. Should have a constructor with a double parameter", constructor);
        
        Object saving1 = constructor.invoke(100.0);
        
        double amount = Savings.method("getAmount").returning(double.class).takingNoParams().invokeOn(saving1);
        Assert.assertEquals("getAmount is not working properly, not returning the right amount", amount, 100.0, 0.1);

        Savings.method("calculateNextYear").returningVoid().takingNoParams().invokeOn(saving1);

        amount = Savings.method("getAmount").returning(double.class).takingNoParams().invokeOn(saving1);
        Assert.assertEquals("getAmount is not working properly, not returning the right amount", amount, 52.50, 0.1);
    }
    
    @Test
    public void testDeposits() throws Throwable
    {
        TestHelper.testClassExists("DepositAccount");
        TestHelper.testMethodCount("DepositAccount", 1);
        
        Reflex.ClassRef<Object> Deposit = Reflex.reflect("DepositAccount");
        Assert.assertTrue("DepositAccount should extend Account", Deposit.inherits(Reflex.reflect("Account")));
        Reflex.MethodRef1<Object, Object, Double> constructor = Deposit.constructor().taking(double.class);
        Assert.assertNotNull("DepositAccount constructor not found. Should have a constructor with a double parameter", constructor);
        
        Object saving1 = constructor.invoke(100.0);
        
        double amount = Deposit.method("getAmount").returning(double.class).takingNoParams().invokeOn(saving1);
        Assert.assertEquals("getAmount is not working properly, not returning the right amount", amount, 100.0, 0.1);

        Deposit.method("calculateNextYear").returningVoid().takingNoParams().invokeOn(saving1);

        amount = Deposit.method("getAmount").returning(double.class).takingNoParams().invokeOn(saving1);
        Assert.assertEquals("getAmount is not working properly, not returning the right amount", amount, 95.0, 0.1);
    }

}

import fi.helsinki.cs.tmc.edutestutils.Points;
import nl.avans.testhelper.TestHelper;
import org.junit.Test;

@Points("abstract-classes-1.3")
public class TestUsage {
    @Test
    public void testOutput() throws Throwable
    {
        String[] output = TestHelper.callMain("Main");
        TestHelper.testContains(output).contains("Year 1").then("Savings").line().then("initial 500.0").then("472.5");
        TestHelper.testContains(output).contains("Year 1").then("Savings").line().then("initial 1500.0").then("1522.5");
        TestHelper.testContains(output).contains("Year 1").then("Deposit").line().then("initial 500.0").then("495.0");
        TestHelper.testContains(output).contains("Year 1").then("Deposit").line().then("initial 1500.0").then("1495.0");
        
        TestHelper.testContains(output).contains("Year 2").then("Savings").line().then("initial 500.0").then("443.63");
        TestHelper.testContains(output).contains("Year 2").then("Savings").line().then("initial 1500.0").then("1546.13");
        TestHelper.testContains(output).contains("Year 2").then("Deposit").line().then("initial 500.0").then("490.0");
        TestHelper.testContains(output).contains("Year 2").then("Deposit").line().then("initial 1500.0").then("1490.0");
        
        TestHelper.testContains(output).contains("Year 3").then("Savings").line().then("initial 500.0").then("413.31");
        TestHelper.testContains(output).contains("Year 3").then("Savings").line().then("initial 1500.0").then("1570.94");
        TestHelper.testContains(output).contains("Year 3").then("Deposit").line().then("initial 500.0").then("485.0");
        TestHelper.testContains(output).contains("Year 3").then("Deposit").line().then("initial 1500.0").then("1485.0");
        
    }
}

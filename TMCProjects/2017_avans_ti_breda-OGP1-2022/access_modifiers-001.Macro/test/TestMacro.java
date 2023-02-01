
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import nl.avans.testhelper.TestHelper;
import org.junit.Test;


@Points("access-modifiers-1.3")
public class TestMacro {
    
    @Test
    public void testMacro()
    {
        TestHelper.testClassExists("Macro");
        TestHelper.testAttribute("Macro", "actions", ArrayList.class);
        
        TestHelper.testMethodCount("Macro", 2);
        //TODO: test if the add method actually adds to the arraylist...
    }
    
}

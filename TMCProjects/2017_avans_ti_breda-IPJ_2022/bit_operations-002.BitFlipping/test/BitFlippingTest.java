
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;

@Points("bit_operations-2")
public class BitFlippingTest {
    @Rule
    public MockStdio io = new MockStdio();

    @Test
    public void testNumbers() {
        int[] numbers = new int[] { 123456, 123 };
        int[] flipped = new int[] { 38240256, -570425344 };
        for(int i = 0; i < numbers.length; i++)
        {
            io.disable();
            io.enable();
            ReflectionUtils.newInstanceOfClass(BitFlipping.class);
            io.setSysIn(numbers[i] + "\n");
            BitFlipping.main(new String[0]);

            String out = io.getSysOut();
            assertTrue("You did not ask anything!", out.trim().length() > 0);
            assertTrue("Your calculator did not go well, flipped value of " + numbers[i] + " is " + flipped[i] + ", you outputted " + out, out.contains(flipped[i]+""));
        }
    }


}

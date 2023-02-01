
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

@Points("bit_operations-3")
public class BitOperationsTest {
    
    class BitResult {
        public int input;
        public int bit;
        public int result;
        
        public BitResult(int input, int bit, int result) {
            this.input = input;
            this.bit = bit;
            this.result = result;
        }
    };
       
    
    @Points("bit_operations-3.1")
    @Test
    public void testBitCheck() {
        int[] numbers = new int[] { 93, 11, 54, 110};
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < 32; j++) {
                boolean result = BitOperations.isBitSet(numbers[i], j);
                boolean expectedResult = ((numbers[i] & (1 << j)) != 0);
                assertTrue("Bit " + j + " should be " + expectedResult + " with number: " + numbers[i] + " now: " + result, result == expectedResult);
                        
            }
        }
    }
     
    // TODO: setBit & clearBit
    @Points("bit_operations-3.2")
    @Test
    public void testBitSet() {
        ArrayList<BitResult> bitTests = new ArrayList<>();
        bitTests.add(new BitResult(93, 1, 95));
        bitTests.add(new BitResult(93, 0, 93));
        bitTests.add(new BitResult(93, 5, 125));
        
        
        for (BitResult bitTest : bitTests) {
            assertTrue("Number: " + bitTest.input + " with bit " + bitTest.bit + " set should be " + bitTest.result, 
                    BitOperations.setBit(bitTest.input, bitTest.bit) == bitTest.result);
        }

    }
    
    @Points("bit_operations-3.2")
    @Test
    public void testBitClear() {
        ArrayList<BitResult> bitTests = new ArrayList<>();
        bitTests.add(new BitResult(93, 1, 93));
        bitTests.add(new BitResult(255, 7, 127));
        bitTests.add(new BitResult(255, 3, 247));
        
        
        for (BitResult bitTest : bitTests) {
            assertTrue("Number: " + bitTest.input + " with bit " + bitTest.bit + " cleared should be " + bitTest.result, 
                    BitOperations.clearBit(bitTest.input, bitTest.bit) == bitTest.result);
        }

    }

}


import java.util.Scanner;

public class BitOperations {
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        // Implement your program here. Remember to ask the input from user.
        System.out.print("Type a number: ");
        int number = Integer.parseInt(reader.nextLine());
        System.out.print("Which bit do you want to check: ");
        int bitCheck = Integer.parseInt(reader.nextLine());
        System.out.print("Which bit do you want to set: ");
        int bitOn = Integer.parseInt(reader.nextLine());
        System.out.print("Which bit do you want to clear: ");
        int bitOff = Integer.parseInt(reader.nextLine());

        System.out.println("Is bit " + bitCheck + " set?: " + isBitSet(number, bitCheck));
        System.out.println("With bit " + bitOn + " set, result becomes: " + setBit(number, bitOn));
        System.out.println("With bit " + bitOff + " clear, result becomes: " + clearBit(number, bitOff));
       
    }
    
    public static boolean isBitSet(int number, int bit) {
        return false;
    }
    
    public static int setBit(int number, int bit) {
        return number;
    }
    
    public static int clearBit(int number, int bit) {
        return number;
    }

}

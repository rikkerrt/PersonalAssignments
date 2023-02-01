
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        
        accounts.add(new SavingsAccount(500));
        accounts.add(new SavingsAccount(1500));

        accounts.add(new DepositAccount(500));
        accounts.add(new DepositAccount(1500));
        
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Year " + (i+1));
            for(Account account : accounts)
            {
                account.calculateNextYear();
                System.out.println(account);
            }
            System.out.println("");            
        }
    }
}

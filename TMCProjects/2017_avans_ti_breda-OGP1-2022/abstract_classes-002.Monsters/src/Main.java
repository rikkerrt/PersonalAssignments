
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args)
    {
        Field field = new Field(30,15);
        field.addCharacter(new Hero(15,7));
        
        for(int i = 0; i < 30; i++) {
            int x = (int) (Math.random() * field.getWidth());
            int y = (int) (Math.random() * field.getHeight());
            if(field.canMoveTo(x, y))
                field.addCharacter(new RandomEnemy(x,y));
        }

        
        
        while(!field.isDone())
        {
            field.display();
            System.out.println("");
            System.out.println("");
            System.out.flush();
            try {
                field.update();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}

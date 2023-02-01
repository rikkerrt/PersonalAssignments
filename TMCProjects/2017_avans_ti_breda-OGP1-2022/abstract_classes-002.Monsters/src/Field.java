
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Field {
    private ArrayList<Character> characters = new ArrayList<>();
    private int width;
    private int height;
    
    
    Field(int width, int height ) {
        this.width = width;
        this.height = height;
    }
    
    
    
    public boolean isDone()
    {
        boolean allSame = characters.get(0).isEnemy();
        for(Character c : characters) {
            if(c.isEnemy() != allSame) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canMoveTo(int x, int y)
    {
        for(Character character : characters) {
            if(character.isAt(x,y) && !character.isDead())
                return false;
        }
        return true;
    }

    void addCharacter(Character character) {
        characters.add(character);
        character.setField(this);
    }

    void display() {
        System.out.print("/");
        for(int x = 0; x < this.width; x++) {
            System.out.print("-");
        }
        System.out.println("\\");
        for(int y = 0; y < this.height; y++) {
            System.out.print("|");
            for(int x = 0; x < this.width; x++) {
                
                boolean hasCharacter = false;
                for(Character character : characters) {
                    if(character.isAt(x,y)) {
                        character.display();
                        hasCharacter = true;
                    }
                }
                if(!hasCharacter)
                    System.out.print("·");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.print("\\");
        for(int x = 0; x < this.width; x++) {
            System.out.print("-");
        }
        System.out.println("/");
    }

    ArrayList<Character> getCharactersNear(Character character, int range) {
        ArrayList<Character> nearby = new ArrayList<>();
        
        for(Character c : this.characters) {
            if(c != character && c.distanceTo(character) <= range) {
                nearby.add(c);
            }
        }
        return nearby;
    }
    ArrayList<Character> getCharactersNear(Character character, int range, boolean isEnemy) {
        ArrayList<Character> nearby = new ArrayList<>();
        
        for(Character c : this.characters) {
            if(c != character && c.distanceTo(character) <= range && c.isEnemy() == isEnemy) {
                nearby.add(c);
            }
        }
        return nearby;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    void update() {
        for(Character c : characters) {
            c.update();
        }
        
        updateAttack(false);
        updateAttack(true);
    }

    private void updateAttack(boolean isEnemy) {
        Iterator<Character> iterator = characters.iterator();
        while(iterator.hasNext()) {
            Character character = iterator.next();
            if(character.isEnemy() == isEnemy && !character.isDead())
                character.updateAttack();
            
            if(character.isDead()) {
                iterator.remove();
            }
        }
    }
    
    
    
}

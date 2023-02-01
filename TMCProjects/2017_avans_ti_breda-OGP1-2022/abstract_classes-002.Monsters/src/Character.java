
import java.util.ArrayList;

public abstract class Character {
    private int x;
    private int y;
    private char display;
    private boolean moved;
    private Field field;
    private int health;
    private int strength;
    
    
    public Character(int x, int y, char display, int health, int strength)
    {
        this.x = x;
        this.y = y;
        this.display = display;
        this.health = health;
        this.strength = strength;
    }   

    public abstract void update();
    public abstract boolean isEnemy();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void damage(Character source, int damage)
    {
        if(source.distanceTo(this) > 2) {
            return;
        }
        this.health -= damage;
        if(this.health < 0)
            this.health = 0;
    }
    
    public void spawn(Character character)
    {
        if(character.distanceTo(this) < 2)
            this.field.addCharacter(character);
    }
    
    
    public ArrayList<Character> getCharactersNear(int range, boolean isEnemy)
    {
        if(range > 9)
            range = 9;
        return field.getCharactersNear(this, range, isEnemy);
    }
    
    public ArrayList<Character> getCharactersNear(int range)
    {
        if(range > 9)
            range = 9;
        return field.getCharactersNear(this, range);
    }
    
    public boolean moveTo(int x, int y)
    {
        if(x < 0 || x >= field.getWidth() ||
           y < 0 || y >= field.getHeight()) {
            return false;
        }
        
        if(!field.canMoveTo(x, y)) {
            return false;
        }
        
        if(this.distanceTo(x,y) > 3) {
            return false;
        }
        if(moved) {
            return false;
        }
        moved = true;
        this.x = x;
        this.y = y;
        return true;
    }
    
    public void updateAttack()
    {
        ArrayList<Character> near = getCharactersNear(1, !isEnemy());
        for(Character c : near)
            c.damage(this, strength);
    }
    
    public void suicide()
    {
        this.health = 0;
    }
    
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }


    public void setField(Field field)
    {
        this.field = field;
    }

    public void display() {
        System.out.print(display);
        moved = false;
    }

    public double distanceTo(Character character) {
        return Math.sqrt((character.x - this.x)*(character.x - this.x) + (character.y - this.y)*(character.y - this.y));
    }

    public double distanceTo(int x, int y) {
        return Math.sqrt((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y));
    }

    public boolean isDead() {
        return this.health <= 0;
    }
    
    
}


import java.util.ArrayList;
import java.util.Comparator;

public class Hero extends Character {

    public Hero(int x, int y) {
        super(x, y, 'H', 50, 1);
    }

    @Override
    public void update() {
        ArrayList<Character> characters = getCharactersNear(10, true);
        characters.sort(new Comparator<Character>(){
            @Override
            public int compare(Character o1, Character o2) {
                return (int)(o1.distanceTo(o2) * 10);
            }
    
        });
        
        
        if(characters.isEmpty()) {
            int[][] offsets = { { -1,0}, { 1, 0 }, { 0, 1 }, { 0, -1 } };
            int direction = (int) (Math.random() * 4);
            moveTo(this.getX() + offsets[direction][0], this.getY() + offsets[direction][1]);
        } else {
            int offsetX = characters.get(0).getX() - this.getX();
            int offsetY = characters.get(0).getY() - this.getY();
            
            if(Math.abs(offsetX) > Math.abs(offsetY)) {
                moveTo((int) (this.getX() + Math.signum(offsetX)), this.getY());
            } else {
                moveTo(this.getX(), (int) (this.getY() + Math.signum(offsetY)));
            }
        }        
    }

    @Override
    public boolean isEnemy() {
        return false;
    }
    
}

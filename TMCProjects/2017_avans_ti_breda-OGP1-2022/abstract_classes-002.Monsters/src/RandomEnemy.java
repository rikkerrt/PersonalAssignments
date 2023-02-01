
import java.util.ArrayList;

public class RandomEnemy extends Enemy {

    public RandomEnemy(int x, int y) {
        super(x, y, '@', 1);
    }

    @Override
    public void update() {
        int[][] offsets = { { -1,0}, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int direction = (int) (Math.random() * 4);
        moveTo(this.getX() + offsets[direction][0], this.getY() + offsets[direction][1]);
    }
    
}

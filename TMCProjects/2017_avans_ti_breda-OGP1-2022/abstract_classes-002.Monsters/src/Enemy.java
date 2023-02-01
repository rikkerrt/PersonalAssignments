public abstract class Enemy extends Character {
    
    public Enemy(int x, int y, char display, int health) {
        super(x, y, display, health, 1);
    }
    
    @Override
    public boolean isEnemy() {
        return true;
    }
    
}

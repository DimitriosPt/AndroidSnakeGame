package dimitri.towerdefense;

import java.util.List;

public interface AttackStrategy {
    public void attack(Tower tower, List<Enemy> enemies);
    public void drawProjectiles();

}

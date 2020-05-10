package dimitri.towerdefense;

import java.util.ArrayList;
import java.util.List;

public interface AttackStrategy {
    public void attack(Tower tower, List<Enemy> enemies);
    public List<TowerProjectile> spawnProjectiles();

}

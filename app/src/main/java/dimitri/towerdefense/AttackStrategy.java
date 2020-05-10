package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public interface AttackStrategy {
    public void attack(Tower tower, List<Enemy> enemies);
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, float range);

}

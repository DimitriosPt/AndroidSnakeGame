package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public interface AttackStrategy {
    public List<TowerProjectile> attack(Tower tower, List<Enemy> enemies);
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, double heading, float range, String bitmapName);

}

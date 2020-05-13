package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class AreaOfEffectAttackStrategy implements AttackStrategy {
    @Override

    public List<TowerProjectile> attack(Tower tower, List<Enemy> enemies) {

        ArrayList projectiles = new ArrayList<TowerProjectile>();

        for(Enemy enemy:enemies) {
            if (tower.isInRange(enemy))
            {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
                projectiles = (ArrayList) spawnProjectiles(tower.getLocation(),
                        0, tower.getRange(), "fireball");
            }
        }

        tower.setTimeOfLastAttack(System.currentTimeMillis());
        return projectiles;
    }
    @Override
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, double heading, float range, String bitmapName){
        System.out.println("Spawning AOE Projectiles");
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();

        int numOfProjectilesPerBurst = 10;

        for (int i = 0; i < numOfProjectilesPerBurst; i++) {
            projectiles.add(new TowerProjectile(20, 360/numOfProjectilesPerBurst * i, range, bitmapName));
        }

        for(TowerProjectile projectile : projectiles )
        {
            projectile.spawn(new PointF(towerLocation.x, towerLocation.y));
        }
        return (List<TowerProjectile>) projectiles;
    }
}

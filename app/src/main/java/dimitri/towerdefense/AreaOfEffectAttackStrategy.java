package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class AreaOfEffectAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Tower tower, List<Enemy> enemies) {

        spawnProjectiles(tower.getLocation());

        for(Enemy enemy:enemies) {

            if (tower.isInRange(enemy))
            {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
            }
        }
        tower.setTimeOfLastAttack(System.currentTimeMillis());
    }

    @Override
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation){
        System.out.println("Spawning AOE Projectiles");
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();
        TowerProjectile projectile1 = new TowerProjectile(20, 90);
        projectile1.setLocation(towerLocation);
        projectile1.movementStrategy = new ProjectileMovementStrategy();
        projectiles.add(projectile1);
        return (List<TowerProjectile>) projectiles;
    }
}

package dimitri.towerdefense;

import java.util.ArrayList;
import java.util.List;

public class AreaOfEffectAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Tower tower, List<Enemy> enemies) {

        spawnProjectiles();

        for(Enemy enemy:enemies) {
           // double xDistance = (double) (tower.getLocation().x - enemy.getLocation().x);
            //double yDistance = (double) (tower.getLocation().y - enemy.getLocation().y);
            //double distanceFromTowerToEnemy =
             //       Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
            if (tower.isInRange(enemy))
            {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
            }

            //    tower.setTimeOfLastAttack(System.currentTimeMillis());
        }
        tower.setTimeOfLastAttack(System.currentTimeMillis());
    }

    @Override
    public List<TowerProjectile> spawnProjectiles(){
        System.out.println("Spawning AOE Projectiles");
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();
        TowerProjectile projectile1 = new TowerProjectile(20, 90);
        projectile1.movementStrategy = new ProjectileMovementStrategy();
        projectiles.add(projectile1);
        return (List<TowerProjectile>) projectiles;
    }
}

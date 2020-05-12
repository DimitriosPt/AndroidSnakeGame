package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

class SingleTargetAttackStrategy implements AttackStrategy {


    @Override
    public List<TowerProjectile> attack(Tower tower, List<Enemy> enemies) {

        List projectiles = new ArrayList<TowerProjectile>();
        for(Enemy enemy:enemies) {

            if (tower.isInRange(enemy))
            {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
                tower.setTimeOfLastAttack(System.currentTimeMillis());
                projectiles = spawnProjectiles(
                        tower.getLocation(),
                        tower.getDirectionToNearestEnemy(tower.getNearestEnemy(enemies)),
                        tower.getDistanceFrom(tower.getNearestEnemy(enemies)), "iceball");
                break;
            }
        }
        return projectiles;
    }
    @Override
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, double heading, float range, String bitmapName) {

        System.out.println("Spawning Single target Projectile");
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();

        //the math for projectile movement converts the heading assumes the input is radians
        //so to make it clear we are spawning every 60 degrees we show it here
        TowerProjectile projectile= new TowerProjectile(80, (int)heading, range, bitmapName);

        projectile.spawn(new PointF(towerLocation.x, towerLocation.y));

        projectiles.add(projectile);

        return (List<TowerProjectile>) projectiles;
    }
}

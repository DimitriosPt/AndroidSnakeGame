package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

class SingleTargetAttackStrategy implements AttackStrategy {


    @Override
    public void attack(Tower tower, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (tower.canAttack() && tower.isInRange(enemy)) {
                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
                tower.setTimeOfLastAttack(System.currentTimeMillis());
                break;
            }
        }
    }
    @Override
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, double heading, float range) {
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();

        //the math for projectile movement converts the heading assumes the input is radians
        //so to make it clear we are spawning every 60 degrees we show it here
        TowerProjectile projectile= new TowerProjectile(20, (int) Math.toRadians(0), range);

        projectile.spawn(new PointF(towerLocation.x, towerLocation.y));

        projectiles.add(projectile);



        return (List<TowerProjectile>) projectiles;
    }
}

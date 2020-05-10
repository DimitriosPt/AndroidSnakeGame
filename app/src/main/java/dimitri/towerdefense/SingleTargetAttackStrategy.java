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
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation) {

        System.out.println("Spawning Single Target Projectiles");
        ArrayList<TowerProjectile> projectiles = new ArrayList<>();
        return projectiles;
    }
}

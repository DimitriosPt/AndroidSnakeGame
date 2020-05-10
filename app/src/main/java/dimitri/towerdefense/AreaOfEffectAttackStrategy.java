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

        //the math for projectile movement converts the heading assumes the input is radians
        //so to make it clear we are spawning every 60 degrees we show it here
        TowerProjectile projectile1 = new TowerProjectile(20, (int) Math.toRadians(0));
        TowerProjectile projectile2 = new TowerProjectile(20, (int) Math.toRadians(60));
        TowerProjectile projectile3 = new TowerProjectile(20, (int) Math.toRadians(120));
        TowerProjectile projectile4 = new TowerProjectile(20, (int) Math.toRadians(180));
        TowerProjectile projectile5 = new TowerProjectile(20, (int) Math.toRadians(240));
        TowerProjectile projectile6 = new TowerProjectile(20, (int) Math.toRadians(300));

        projectile1.setLocation(new PointF(towerLocation.x, towerLocation.y));
        projectile2.setLocation(new PointF(towerLocation.x, towerLocation.y));
        projectile3.setLocation(new PointF(towerLocation.x, towerLocation.y));
        projectile4.setLocation(new PointF(towerLocation.x, towerLocation.y));
        projectile5.setLocation(new PointF(towerLocation.x, towerLocation.y));
        projectile6.setLocation(new PointF(towerLocation.x, towerLocation.y));

        projectiles.add(projectile1);
        projectiles.add(projectile2);
        projectiles.add(projectile3);
        projectiles.add(projectile4);
        projectiles.add(projectile5);
        projectiles.add(projectile6);

        return (List<TowerProjectile>) projectiles;
    }
}

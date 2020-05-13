package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

class ConeAttackStrategy implements AttackStrategy {


    @Override
    public List<TowerProjectile> attack(Tower tower, List<Enemy> enemies) {

        ConeTurret coneTurret = (ConeTurret) tower;
        List projectiles = new ArrayList<TowerProjectile>();
        for(Enemy enemy:enemies) {

            if (tower.isInRange(enemy))
            {
                double directionToNearestTarget =
                        tower.getDirectionToNearestEnemy(tower.getNearestEnemy(enemies));

                enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
                tower.setTimeOfLastAttack(System.currentTimeMillis());
                projectiles = spawnProjectiles(
                        tower.getLocation(),
                        directionToNearestTarget,
                        tower.getDistanceFrom(tower.getNearestEnemy(enemies)), "iceball");

                coneTurret.heading = (float) directionToNearestTarget;
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

        int degreesInCone = 60;

        // every ten degrees, add another projectile
        int numOfProjectilesPerBurst = degreesInCone / 10;

        /* Since we are going to be creating a cone, we will need projectiles "above" and "below"
         the straight line angle to our target. To make this dynamic we will be creating projectiles
          starting in the at the lower bound of our cone and incrementing up*/
        for (int i = 0 - numOfProjectilesPerBurst/2; i < numOfProjectilesPerBurst/2; i++) {
            projectiles.add(new TowerProjectile(40, (int) heading + (i*degreesInCone/numOfProjectilesPerBurst), range, bitmapName));
        }


        for(TowerProjectile projectile : projectiles )
        {
            projectile.spawn(new PointF(towerLocation.x, towerLocation.y));
        }

        return (List<TowerProjectile>) projectiles;
    }
}

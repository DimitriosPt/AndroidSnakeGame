package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

class ConeAttackStrategy implements AttackStrategy {

    //changing this changes the size of the cone which this turret attacks in
    int degreesInAttackCone = 80;

    @Override
    public List<TowerProjectile> attack(Tower tower, List<Enemy> enemies) {

        ConeTurret coneTurret = (ConeTurret) tower;
        ArrayList projectiles = new ArrayList<TowerProjectile>();
        List<Enemy> enemiesInSpray = new ArrayList<Enemy>();
        //get angle to nearest target in degrees
        double directionToNearestTarget =
                tower.getDirectionToEnemy(tower.getNearestEnemy(enemies));

        Enemy nearestEnemy = tower.getNearestEnemy(enemies);
        for(Enemy enemy:enemies) {

            if (tower.isInRange(enemy))
            {
                double lowerEndOfConeRange = tower.getDirectionToEnemy(nearestEnemy) - degreesInAttackCone/2.0;
                double upperEndOfConeRange = tower.getDirectionToEnemy(nearestEnemy) + degreesInAttackCone/2.0;

                //if the enemy is within the angle of the attack cone.
                if((lowerEndOfConeRange <= tower.getDirectionToEnemy(enemy)
                        && tower.getDirectionToEnemy(enemy) <= upperEndOfConeRange))
                {
                    enemiesInSpray.add(enemy);
                }
                break;
            }
        }
        for(Enemy enemy : enemiesInSpray )
        {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - tower.getDamage());
            tower.setTimeOfLastAttack(System.currentTimeMillis());

        }
        if(enemiesInSpray.size() > 0)
        {
            projectiles = (ArrayList) spawnProjectiles(
                    tower.getLocation(),
                    directionToNearestTarget,
                    tower.getDistanceFrom(tower.getNearestEnemy(enemies)), "poisonball");
        }

        coneTurret.heading = (float) directionToNearestTarget;
        return projectiles;
    }

    @Override
    public List<TowerProjectile> spawnProjectiles(PointF towerLocation, double heading, float range, String bitmapName) {

        System.out.println("Spawning Single target Projectile");
        List<TowerProjectile> projectiles = new ArrayList<TowerProjectile>();

        //the math for projectile movement converts the heading assumes the input is radians
        //so to make it clear we are spawning every 60 degrees we show it here



        // every ten degrees, add another projectile
        int numOfProjectilesPerBurst = degreesInAttackCone / 10;

        /* Since we are going to be creating a cone, we will need projectiles "above" and "below"
         the straight line angle to our target. To make this dynamic we will be creating projectiles
          starting in the at the lower bound of our cone and incrementing up*/
        for (int i = 0 - numOfProjectilesPerBurst/2; i < numOfProjectilesPerBurst/2; i++) {
            projectiles.add(new TowerProjectile(40, (int) heading + (i*degreesInAttackCone/numOfProjectilesPerBurst), range, bitmapName));
        }


        for(TowerProjectile projectile : projectiles )
        {
            projectile.spawn(new PointF(towerLocation.x, towerLocation.y));
        }

        return (List<TowerProjectile>) projectiles;
    }
}

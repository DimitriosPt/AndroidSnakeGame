package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

abstract class Tower extends StaticGameObject {
    private int range;
    private int damage;
    private long attackSpeed;
    private int cost;
    private long timeOfLastAttack;
    public AttackStrategy attackStrategy;

    private enum damageType {
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }



    public long getTimeOfLastAttack() {
        return timeOfLastAttack;
    }

    public void setTimeOfLastAttack(long timeOfLastAttack) {
        this.timeOfLastAttack = timeOfLastAttack;
    }

    //this will be a record in milliseconds of when the tower last attacked

    protected boolean isInRange(Enemy enemy) {
            double xDistance = (double) (this.getLocation().x - enemy.getLocation().x);
            double yDistance = (double) (this.getLocation().y - enemy.getLocation().y);
            double distanceFromTowerToEnemy = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
            return(distanceFromTowerToEnemy <= this.range);
        }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    void attack(List<Enemy> enemies)
    {
        attackStrategy.attack(this, enemies);
    }

    public Enemy getNearestEnemy(List<Enemy> enemies)
    {
        Enemy closestEnemy = enemies.get(0);
        double xDistance = (double) (this.getLocation().x - closestEnemy.getLocation().x);
        double yDistance = (double) (this.getLocation().y - closestEnemy.getLocation().y);
        double distanceToClosestEnemy = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

        for(Enemy enemy : enemies)
        {
            xDistance = (double) (this.getLocation().x - enemy.getLocation().x);
            yDistance = (double) (this.getLocation().y - enemy.getLocation().y);
            double distanceFromTowerToEnemy = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

            if(distanceFromTowerToEnemy < distanceToClosestEnemy)
            {
                distanceToClosestEnemy = distanceFromTowerToEnemy;
                closestEnemy = enemy;
            }
        }

        return closestEnemy;
    }

    //returns angle to the nearest enemy in radians
    public double getDirectionToNearestEnemy(Enemy enemy)
    {
        double xDistance = (double) (this.getLocation().x - enemy.getLocation().x);
        double yDistance = (double) (this.getLocation().y - enemy.getLocation().y);
        double angleBetweenTurretAndEnemy =
                Math.atan(Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)));
        return Math.toRadians(angleBetweenTurretAndEnemy);
    }
    //determines if enough time has passed in milliseconds to allow the turret to attack again
    public boolean canAttack()
    {
        return(timeOfLastAttack + attackSpeed < System.currentTimeMillis());

    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
}

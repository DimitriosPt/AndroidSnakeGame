package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.List;

class Tower extends GameObject {
    private int range;
    private int damage;
    private long attackSpeed;
    private int cost;
    private long timeOfLastAttack;
    private AttackStrategy attackStrategy;

//    public Tower(int range, int damage, long attackSpeed, int cost, AttackStrategy attackStrategy) {
//
//        this.range = range;
//        this.damage = damage;
//        this.attackSpeed = attackSpeed;
//        this.cost = cost;
//        this.attackStrategy = attackStrategy;
//    }
//
//    private enum damageType {
//        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
//    }

    public long getTimeOfLastAttack() {
        return timeOfLastAttack;
    }

    public void setTimeOfLastAttack(long timeOfLastAttack) {
        this.timeOfLastAttack = timeOfLastAttack;
    }

    //this will be a record in milliseconds of when the tower last attacked

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

    //abstract void attack(List<Enemy> enemies);

    //determines if enough time has passed in milliseconds to allow the turret to attack again
    public boolean canAttack() {
        return (timeOfLastAttack + attackSpeed < System.currentTimeMillis());
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

    public double calculateDistanceToTower(Tower tower, GameObject enemy)
    {
        double tower_x = tower.getTransform().getLocation().x;
        double tower_y = tower.getTransform().getLocation().y;
        double enemy_x = enemy.getTransform().getLocation().x;
        double enemy_y = enemy.getTransform().getLocation().y;

        return Math.sqrt(Math.pow((tower_x - enemy_x), 2)
                + Math.pow((tower_y - enemy_y), 2));

    }
}

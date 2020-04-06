package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

abstract class Tower extends StaticGameObject{
    private int range;
    private int damage;
    private long attackSpeed;
    private int cost;

    public long getTimeOfLastAttack() {
        return timeOfLastAttack;
    }

    public void setTimeOfLastAttack(long timeOfLastAttack) {
        this.timeOfLastAttack = timeOfLastAttack;
    }

    //this will be a record in milliseconds of when the tower last attacked
    private long timeOfLastAttack;

    private enum damageType{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
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

    abstract void attack(int damage, int damageType);

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

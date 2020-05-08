package dimitri.towerdefense;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends MoveableGameObject {
    enum damageResistances{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }
    private List<damageResistances> resistances;
    public MovementStrategy movementStrategy;
    private int health;

    public Enemy(Context context, int health, int speed, List<damageResistances> resistances) {
        super(speed);
        this.health = health;
        this.resistances = resistances;
    }

    public void setResistances(List<damageResistances> resistances) {
        this.resistances = resistances;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<damageResistances> getResistances() {
        return resistances;
    }

    public int getHealth() {
        return health;
    }
}

package dimitri.towerdefense;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends MoveableGameObject {
    public Enemy(int health, int speed) {
        super(speed);
        this.health = health;
    }

    enum damageResistances{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }
    public List<damageResistances> resistances = new ArrayList<>();

    private int health;

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

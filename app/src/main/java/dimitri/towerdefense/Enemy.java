package dimitri.towerdefense;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends MoveableGameObject {
    enum damageResistances{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }
    private List<damageResistances> resistances;
    public MovementStrategy movementStrategy;
    private int currentHealth=20;
    private int maxHealth;

    public Enemy(Context context, int maxHealth, int speed, List<damageResistances> resistances) {
        super(speed);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.resistances = resistances;
    }

    public void setResistances(List<damageResistances> resistances) {
        this.resistances = resistances;
    }

    public List<damageResistances> getResistances() {
        return resistances;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    void spawn(Point location) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int blockSize = displayMetrics.widthPixels / 40;
        // How many blocks of the same size will fit into the height
        int mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Reset the heading
        this.setHeading(EAST);

        // Start with a single snake segment
        this.setLocation(location);
    }
}

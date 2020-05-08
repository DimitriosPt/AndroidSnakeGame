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

    void spawn(Point location) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int blockSize = displayMetrics.widthPixels / 40;
        // How many blocks of the same size will fit into the height
        int mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Reset the heading
        this.setHeading(EAST);

        // Start with a single snake segment
        this.setLocation(new Point(30, mNumBlocksHigh / 2));
    }
}

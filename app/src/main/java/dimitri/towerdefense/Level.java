package dimitri.towerdefense;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

public class Level {
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_PLAYER_LASER = 2;
    public static final int LAST_PLAYER_LASER = 3;
    public static final int mNextPlayerLaser = 4;
    public static final int FIRST_ENEMY = 5;

    private ArrayList<GameObject> objects;


    public Level(PointF mScreenSize, GameEngine ge) {
        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(TowerDefense.getContext(), mScreenSize, ge);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory) {

        objects.clear();
// Spawn the player's lasers
        for (int i = FIRST_PLAYER_LASER;
             i != LAST_PLAYER_LASER + 1; i++) {
            objects.add(i, factory
                    .create(new TowerProjectileSpec()));
        }

// Create some aliens
// Create some alien lasers
        return objects;
    }

    ArrayList<GameObject> getGameObjects() {
        return objects;
    }
}
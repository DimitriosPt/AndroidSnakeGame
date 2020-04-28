package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;

public class Level {
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_TOWER_PROJECTILE = 2;
    public static final int LAST_TOWER_PROJECTILE = 3;
    public static int NextTowerProjectile;
    public static final int FIRST_ENEMY = 5;

    private ArrayList<GameObject> objects;


    public Level(PointF mScreenSize, GameEngine ge) {
        objects = new ArrayList<>();

        GameObjectFactory factory = new GameObjectFactory(TowerDefense.getContext(), mScreenSize, ge);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory) {
        objects.clear();

// Spawn the tower projectiles
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));

//        objects.add(factory.create(new AOETowerSpec()));
//        objects.add(factory.create(new HumanSpec()));
//        for (int i = FIRST_TOWER_PROJECTILE;
//             i != LAST_TOWER_PROJECTILE + 1; i++) {
//            objects.add(factory
//                    .create(new TowerProjectileSpec()));
//        }
// Create some alien lasers
        return objects;
    }

    ArrayList<GameObject> getGameObjects() {
        return objects;
    }
}
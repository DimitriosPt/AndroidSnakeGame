package dimitri.towerdefense;

import android.graphics.PointF;

import java.util.ArrayList;

public class Level {
    public static final int BACKGROUND_INDEX = 0;
    public static final int HUMAN_INDEX = 1;
    public static final int FIRST_TOWER_PROJECTILE = 2;
    public static final int LAST_TOWER_PROJECTILE = 3;
    public static int NextTowerProjectile;
    public static final int FIRST_ENEMY = 5;

    private ArrayList<GameObject> objects;


    public Level(PointF mScreenSize, GameEngine gameEngine) {
        objects = new ArrayList<>();

        GameObjectFactory factory = new GameObjectFactory(gameEngine);

        buildGameObjects(factory);
        for (GameObject object: objects)
        {
            System.out.println(object.getTransform().getLocation().x);
        }
    }

    void buildGameObjects(GameObjectFactory factory) {
        objects.clear();

        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));

        //objects.add(factory.create(new AOETowerSpec()));
        GameObject human = factory.create(new HumanSpec());
        System.out.printf("Location %f, %f", human.getTransform().getLocation().x, human.getTransform().getLocation().y);
        objects.add(HUMAN_INDEX, human);
//        for (int i = FIRST_TOWER_PROJECTILE;
//             i != LAST_TOWER_PROJECTILE + 1; i++) {
//            objects.add(factory
//                    .create(new TowerProjectileSpec()));
//        }
// Create some alien lasers
        //return objects;
    }

    ArrayList<GameObject> getGameObjects() {
        return objects;
    }
}
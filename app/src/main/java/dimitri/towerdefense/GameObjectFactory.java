package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

public class GameObjectFactory {

    private Context context;
    private Point screenSize;
    private GameEngine gameEngineReference;

    GameObjectFactory(GameEngine gameEngine) {

        context = TowerDefense.getContext();
        screenSize = TowerDefense.getScreenSize();
        gameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec) {
        GameObject object = new GameObject();
        int numComponents = spec.getComponents().length;
        final float HIDDEN = 50f;

// Configure the speed relative to the screen size
        float speed = (float) screenSize.x / spec.getSpeed();
// Configure the object size relative to screen size
        PointF objectSize =
                new PointF(screenSize.x / spec.getSizeScale().x,
                        screenSize.y / spec.getSizeScale().y);
// Set the location to somewhere off-screen
        PointF location = new PointF(HIDDEN, HIDDEN);
        object.setTransform(new Transform(speed, objectSize.x, objectSize.y, location));

// More code here next...
// Loop through and add/initialize all the components
        for (String component : spec.getComponents()) {
            System.out.printf("Checking component %s\n", component);
            switch (component) {
                case "TowerSpawnComponent":
                    object.setSpawner(new TowerSpawnComponent());
                    break;
                case "StandardGraphicsComponent":
                    object.setGraphics(new StandardGraphicsComponent(), spec, objectSize);
                    break;
                case "EnemyMovementComponent":
                    object.setMovement(new EnemyMovementComponent());
                    break;
                case "ProjectileMovementcomponent":
                    object.setMovement(new ProjectileMovementComponent());
                    break;
                case "EnemySpawnComponent":
                    object.setSpawner(new EnemySpawnComponent());
                    break;
                case "TowerProjectileSpawnComponent":
                    object.setSpawner(new TowerProjectileSpawnComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(), spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;

                case "BaseSpawnComponent":
                    object.setSpawner(new BaseSpawnComponent());
                    break;

                default:
                    System.out.printf("unable to determine component: %s\n", component);
                    break;
            }
        }
        return object;
    }
}

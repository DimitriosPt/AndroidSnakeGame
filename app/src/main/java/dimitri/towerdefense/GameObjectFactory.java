package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;

public class GameObjectFactory {
    private Context context;
    private Point screenSize;
    private GameEngine gameEngineReference;

    GameObjectFactory(Context context, PointF screenSize, GameEngine gameEngine)
    {
        this.context = context;
        this.screenSize = TowerDefense.getScreenSize();
        gameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec)
    {
        GameObject object = new GameObject();

        int
    }
}

package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<GameObject> gameObjectList;

    GameWorld() {
        gameObjectList = new ArrayList<>();
    }

    void draw(Canvas canvas, Paint paint)
    {
        for (GameObject gameObject:gameObjectList) {
            gameObject.draw(canvas, paint);
        }
    }
    List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    void addGameObjectToList(GameObject gameObject)
    {
        this.gameObjectList.add(gameObject);
    }

    List<Tower> getTowers()
    {
        List<Tower> towerList = new ArrayList<>();

        for (GameObject object: gameObjectList) {
            if(object instanceof Tower)
            {
                towerList.add((Tower)object);
            }
        }
        return towerList;
    }

    List<Enemy> getEnemies()
    {
        List<Enemy> enemyList = new ArrayList<>();

        for (GameObject object: gameObjectList) {
            if(object instanceof Enemy)
            {
                enemyList.add((Enemy) object);
            }
        }
        return enemyList;
    }

    void removeGameObjectFromList(GameObject gameObject)
    {
        this.gameObjectList.remove(gameObject);
    }

}
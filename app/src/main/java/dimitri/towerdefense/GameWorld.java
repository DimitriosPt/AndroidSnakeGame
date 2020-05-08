package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameWorld  {
    private List<GameObject> gameObjectList;

    GameWorld() {
        gameObjectList = new ArrayList<>();
    }

    void draw(Canvas canvas, Paint paint)
    {
        for (GameObject gameObject:gameObjectList) {
            if(gameObject instanceof  Enemy)
            {
                System.out.println("drawing an enemy");
            }
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

    void clear(){
        this.gameObjectList.clear();
    }

    void removeGameObjectFromList(GameObject gameObject)
    {
        this.gameObjectList.remove(gameObject);
    }



}

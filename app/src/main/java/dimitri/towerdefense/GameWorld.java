package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld  {
    private CopyOnWriteArrayList<GameObject> gameObjectList;

    GameWorld() {
        gameObjectList = new CopyOnWriteArrayList<>();
    }

    void draw(Canvas canvas, Paint paint)
    {
        for (GameObject gameObject:gameObjectList) {

            gameObject.draw(canvas, paint);
        }
       // canvas.drawText("Some Text", 20, 120, paint);

    }

    void draw(Canvas canvas, Paint paint, GameController controller)
    {

        for (GameObject gameObject:gameObjectList) {

            gameObject.draw(canvas, paint);
        }
         canvas.drawText("Score: " + controller.getscore(), 40, 120, paint);
         canvas.drawText("Lives: " + controller.getLives(), 1300, 120, paint);

    }

    List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(CopyOnWriteArrayList<GameObject> gameObjectList) {
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
    List<MoveableGameObject> getMoveableGameObjects()
    {
        List<MoveableGameObject> moveableObjectList = new ArrayList<>();

        for (GameObject object: gameObjectList) {
            if(object instanceof MoveableGameObject)
            {
                moveableObjectList.add((MoveableGameObject) object);
            }
        }
        return moveableObjectList;
    }
    void clear(){
        this.gameObjectList.clear();
    }



    void removeGameObjectFromList(GameObject gameObject)
    {
        this.gameObjectList.remove(gameObject);
    }


     List<TowerProjectile> getProjectiles() {
        List<TowerProjectile> projectileList = new ArrayList<>();

        for (GameObject object: gameObjectList) {
            if(object instanceof TowerProjectile)
            {
                projectileList.add((TowerProjectile) object);
            }
        }
        return projectileList;
    }

}

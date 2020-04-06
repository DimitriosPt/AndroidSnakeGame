package dimitri.towerdefense;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<GameObject> gameObjectList;

    public GameWorld() {
        gameObjectList = new ArrayList<>();
    }
    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    public void addGameObjectToList(GameObject gameObject)
    {
        this.gameObjectList.add(gameObject);
    }

    public List<Tower> getTowers()
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

    public List<Enemy> getEnemies()
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
    public void removeGameObjectFromList(GameObject gameObject)
    {
        this.gameObjectList.remove(gameObject);
    }

}

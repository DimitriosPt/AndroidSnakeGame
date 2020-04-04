package dimitri.towerdefense;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private List<GameObject> gameObjectList;

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    public GameWorld(List<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }
}

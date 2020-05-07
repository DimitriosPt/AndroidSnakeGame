package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class TowerSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {
        PointF screenSize = TowerDefense.getScreenSizeF();
        PointF spawnPoint = new PointF(screenSize.x/2, screenSize.y/2);
        transform.setLocation(spawnPoint);
    }
}

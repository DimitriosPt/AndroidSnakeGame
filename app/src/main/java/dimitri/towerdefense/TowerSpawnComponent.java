package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class TowerSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {
        Point screenSize = TowerDefense.getScreenSize();
        PointF spawnPoint = new PointF((float)(screenSize.x / 2), (float) screenSize.y/2);

        transform.setLocation(spawnPoint);
    }
}

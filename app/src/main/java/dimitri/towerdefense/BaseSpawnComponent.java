package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class BaseSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {
        PointF screenSize = TowerDefense.getScreenSizeF();
        PointF spawnPoint = new PointF(screenSize.x * .95f, screenSize.y * .20f);
        transform.setLocation(spawnPoint);
    }
}

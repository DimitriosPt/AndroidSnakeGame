package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class EnemySpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {

        Point screenSize = TowerDefense.getScreenSize();
        PointF spawnPoint = new PointF(0f, (float) 3*screenSize.y / 5 );
        transform.setLocation(spawnPoint);
    }
}

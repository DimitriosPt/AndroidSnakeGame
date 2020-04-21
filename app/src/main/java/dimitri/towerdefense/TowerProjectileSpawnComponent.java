package dimitri.towerdefense;

import android.graphics.PointF;

public class TowerProjectileSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {

        PointF startPosition = transform.getLocation();
    }
}

package dimitri.towerdefense;

import android.graphics.PointF;

public class BackgroundSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(Transform transform) {

        transform.setLocation(new PointF(50f,50f));
    }
}

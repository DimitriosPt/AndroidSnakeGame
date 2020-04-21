package dimitri.towerdefense;

import android.graphics.PointF;

public class TowerProjectileSpec extends ObjectSpec {
    private static final String bitmapName = "arrow";
    private static final int speed = 20;
    private static final PointF sizeScale = new PointF(5f, 5f);

    private static final String[] components = new String[]
            {
                    "StdGraphicsComponent",
                    "ProjectileMovementComponent",
                    "ProjectileSpawnComponent"
            };

    TowerProjectileSpec() {
        super(bitmapName, speed, sizeScale, components);
    }

}

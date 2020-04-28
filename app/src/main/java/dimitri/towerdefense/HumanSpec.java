package dimitri.towerdefense;

import android.graphics.PointF;

public class HumanSpec extends ObjectSpec {
    private static final String bitmapName = "human";
    private static final int speed = 10;
    private static final PointF sizeScale = new PointF(15f, 15f);

    private static final String[] components = new String[]
            {
                    "StandardGraphicsComponent",
                    "EnemySpawnComponent",
                    "EnemyMovementComponent"
            };

    HumanSpec() {
        super(bitmapName, speed, sizeScale, components);
    }

}

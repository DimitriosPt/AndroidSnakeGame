package dimitri.towerdefense;

import android.graphics.PointF;

public class AOETowerSpec extends ObjectSpec{
    private static final String bitmapName = "basic_tower";
    private static final int speed = 0;
    private static final PointF relativeScale =
            new PointF(10f, 10f);
    private static final String[] components = new String [] {
            "PlayerInputComponent",
            "StandardGraphicsComponent",
            "BackgroundMovementComponent",
            "TowerSpawnComponent"};
    AOETowerSpec() {
//        Tower
        super(bitmapName,
                speed, relativeScale,
                components);

    }
}

package dimitri.towerdefense;

import android.graphics.PointF;

public class AOETowerSpec extends ObjectSpec{
    private static final String bitmapName = "basic_tower";
    private static final int speed = 0;
    private static final PointF relativeScale =
            new PointF(15f, 15f);
    private static final String[] components = new String [] {
            "PlayerInputComponent",
            "StdGraphicsComponent",
            "TowerSpawnComponent"};
    AOETowerSpec() {
        super(bitmapName,
                speed, relativeScale,
                components);
    }
}

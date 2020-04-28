package dimitri.towerdefense;

import android.graphics.PointF;
class BackgroundSpec extends ObjectSpec {
    // This is all the unique specifications
    // for the background
    private static final String bitmapName = "background";
    private static final int speed = 0;
    private static final PointF relativeScale =
            new PointF(1f, 1f);
    private static final String[] components = new String [] {
            "BackgroundGraphicsComponent",
            "BackgroundSpawnComponent"};
    BackgroundSpec() {
        super(bitmapName, speed, relativeScale,
                components);
    }
}
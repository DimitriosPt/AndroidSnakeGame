package dimitri.towerdefense;

import android.graphics.PointF;

public class BaseSpec extends ObjectSpec{
    private static final String bitmapName = "base";
    private static final int speed = 20;
    private static final PointF relativeScale =
            new PointF(10f, 5f);
    private static final String[] components = new String [] {
            "StandardGraphicsComponent",
            "BackgroundMovementComponent",
            "BaseSpawnComponent"};

    BaseSpec()
    {
        super(bitmapName, speed, relativeScale, components);

    }

    BaseSpec(String mBitmapName, int mSpeed, PointF mSizeScale, String[] mComponents) {
        super(mBitmapName, mSpeed, mSizeScale, mComponents);
    }
}

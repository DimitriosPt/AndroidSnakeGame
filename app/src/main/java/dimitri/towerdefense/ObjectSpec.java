package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

abstract class ObjectSpec {
    private String bitmapName;
    private int speed;
    private PointF sizeScale;
    private String[] components;

    ObjectSpec(String mBitmapName, int mSpeed, PointF mSizeScale, String[] mComponents)
    {
        bitmapName = mBitmapName;
        speed = mSpeed;
        sizeScale = mSizeScale;
        components = mComponents;
    }

    public String getBitmapName() {
        return bitmapName;
    }

    public int getSpeed() {
        return speed;
    }

    public PointF getSizeScale() {
        return sizeScale;
    }

    public String[] getComponents() {
        return components;
    }


}

package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

abstract class ObjectSpec {
    private String bitmapName;
    private int speed;
    private PointF sizeScale;
    private String[] components;

    ObjectSpec(String bitmapName, int speed, PointF sizeScale, String[] components)
    {
        this.bitmapName = bitmapName;
        this.speed = speed;
        this.sizeScale = sizeScale;
        this.components = components;
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

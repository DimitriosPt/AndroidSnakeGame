package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

abstract class GameObject{
    private int size;

    private Bitmap objectBitmap;
    private PointF location;

    abstract void spawn(PointF location);
    abstract void draw(Canvas canvas, Paint paint);

    public int getSize() {
        return size;
    }

    public Bitmap getObjectBitmap() {
        return objectBitmap;
    }

    public void setObjectBitmap(Bitmap objectBitmap) {
        this.objectBitmap = objectBitmap;
    }

    PointF getLocation() {
        return location;
    }

    void setLocation(PointF location) {
        this.location = location;
    }

    void setSize(int size) {
        this.size = size;
    }

    float getDistanceFrom(GameObject object2)
    {
        double xDistance = (double) (this.getLocation().x - object2.getLocation().x);
        double yDistance = (double) (this.getLocation().y - object2.getLocation().y);
        double distanceBetweenObjects = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        return (float)distanceBetweenObjects;
    }


}

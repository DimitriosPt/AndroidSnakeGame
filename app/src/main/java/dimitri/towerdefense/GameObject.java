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

    void getDistanceFrom(GameObject object2)
    {

    }


}

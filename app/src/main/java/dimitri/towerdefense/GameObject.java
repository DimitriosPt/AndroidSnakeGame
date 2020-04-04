package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class GameObject{
    private int size;

    private Bitmap objectBitmap;
    private Point location;

    abstract void spawn();
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

    Point getLocation() {
        return location;
    }

    void setLocation(Point location) {
        this.location = location;
    }
}

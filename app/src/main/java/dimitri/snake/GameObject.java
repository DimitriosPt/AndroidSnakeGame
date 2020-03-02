package dimitri.snake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class GameObject{
    private int size;
    private Point gridSize;
    private Bitmap objectBitmap;
    private Point location;

    abstract void spawn();
    abstract void draw(Canvas canvas, Paint paint);

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getGridSize() {
        return gridSize;
    }

    public void setGridSize(Point gridSize) {
        this.gridSize = gridSize;
    }

    public Bitmap getObjectBitmap() {
        return objectBitmap;
    }

    public void setObjectBitmap(Bitmap objectBitmap) {
        this.objectBitmap = objectBitmap;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

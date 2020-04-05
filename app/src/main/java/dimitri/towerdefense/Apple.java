package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

class Apple extends GameObject{



    // The location of the normal_apple on the grid
    // Not in pixels


    // The range of values we can choose from
    // to spawn an normal_apple
    private Point mSpawnRange;
    private int mSize;
    boolean isGood;
    int pointValue;

    // An image to represent the normal_apple
    private Bitmap appleBitmap;


    public static class AppleBuilder
    {
        private Point location;
        private Point gridSize;
        private int size;
        private boolean isGood;
        private Bitmap appleBitmap;
        private int pointValue;

        AppleBuilder(Point location) {
            this.location = location;
        }

        AppleBuilder pointValue(int pointValue)
        {
            this.pointValue = pointValue;
            return this;
        }

        AppleBuilder spawnRange(Point gridSize) {
            this.gridSize = gridSize;
            return this;
        }

        AppleBuilder size(int size) {
            this.size = size;
            return this;
        }

        AppleBuilder location(Point location) {
            this.location = location;
            return this;
        }

        AppleBuilder bitmap(Bitmap appleBitmap) {
            this.appleBitmap = appleBitmap;
            return this;
        }
        AppleBuilder isGood(boolean isGood) {
            this.isGood = isGood;
            return this;
        }

        Apple build()
        {
            return new Apple(location, gridSize, size, isGood, appleBitmap, pointValue);

        }
    }



    /// Set up the normal_apple in the constructor


    private Apple(Point location, Point mSpawnRange, int mSize, boolean isGood,
                  Bitmap appleBitmap, int pointValue) {
        this.setLocation(location);
        this.mSpawnRange = mSpawnRange;
        this.mSize = mSize;
        this.isGood = isGood;
        this.appleBitmap = appleBitmap;
        this.pointValue =pointValue;
    }

    // This is called every time an normal_apple is eaten
    void spawn(Point location){
        // Choose two random values and place the normal_apple
        Random random = new Random();
        Point tempPoint = new Point();
        tempPoint.x = random.nextInt(this.mSpawnRange.x) + 1;
        tempPoint.y = random.nextInt(this.mSpawnRange.y - 1) + 1;
        this.setLocation(tempPoint);
    }


    // Draw the normal_apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.appleBitmap,
                this.getLocation().x * this.mSize, this.getLocation().y * this.mSize, paint);

    }

}

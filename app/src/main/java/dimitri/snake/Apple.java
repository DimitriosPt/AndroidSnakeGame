package dimitri.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

class Apple extends GameObject{



    // The location of the apple on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;
    boolean isGood;

    // An image to represent the apple
    private Bitmap appleBitmap;


    public static class AppleBuilder
    {
        private Point location;
        private Point gridSize;
        private int size;
        private boolean isGood;
        private Bitmap appleBitmap;

        AppleBuilder(Point location) {
            this.location = location;
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
            return new Apple(location, gridSize, size, isGood, appleBitmap);

//            apple.location = this.location;
//            apple.mSpawnRange = this.gridSize;
//            apple.mSize = this.size;
//            apple.isGood = this.isGood;
//            apple.appleBitmap = this.appleBitmap;

//            return apple;
        }
    }



    /// Set up the apple in the constructor


    private Apple(Point location, Point mSpawnRange, int mSize, boolean isGood, Bitmap appleBitmap) {
        this.location = location;
        this.mSpawnRange = mSpawnRange;
        this.mSize = mSize;
        this.isGood = isGood;
        this.appleBitmap = appleBitmap;
    }

    // This is called every time an apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        this.location.x = random.nextInt(this.mSpawnRange.x) + 1;
        this.location.y = random.nextInt(this.mSpawnRange.y - 1) + 1;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return this.location;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(this.appleBitmap,
                this.location.x * this.mSize, this.location.y * this.mSize, paint);

    }

}

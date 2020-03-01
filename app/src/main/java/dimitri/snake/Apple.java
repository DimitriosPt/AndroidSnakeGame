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

        public AppleBuilder(Point location) {
            this.location = location;
        }

        public AppleBuilder spawnRange(Point gridSize) {
            this.gridSize = gridSize;
            return this;
        }

        public AppleBuilder size(int size) {
            this.size = size;
            return this;
        }

        public AppleBuilder location(Point location) {
            this.location = location;
            return this;
        }

        public AppleBuilder bitmap(Bitmap appleBitmap) {
            this.appleBitmap = appleBitmap;
            return this;
        }
        public AppleBuilder isGood(boolean isGood) {
            this.isGood = isGood;
            return this;
        }

        public Apple build()
        {
            Apple apple = new Apple();
            apple.location = this.location;
            apple.mSpawnRange = this.gridSize;
            apple.mSize = this.size;
            apple.isGood = this.isGood;
            apple.appleBitmap = this.appleBitmap;

            return apple;
        }
    }



    /// Set up the apple in the constructor
    private Apple(){
    }


    // This is called every time an apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return this.location;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(appleBitmap,
                location.x * mSize, location.y * mSize, paint);

    }

}

package dimitri.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

abstract class Apple extends GameObject{

    // The location of the apple on the grid
    // Not in pixels
    Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    //private Point mSpawnRange;
    //private int mSize;

    // An image to represent the apple
    //private Bitmap mBitmapApple;

    /// Set up the apple in the constructor
    Apple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        this.setGridSize(sr);
        // Make a note of the size of an apple
        this.setSize(s);
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Choose two random values and place the apple
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // This is called every time an apple is eaten
    @Override
    void spawn() {
        Random random = new Random();
        location.x = random.nextInt(this.getGridSize().x) + 1;
        location.y = random.nextInt(this.getGridSize().y - 1) + 1;

    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(),
                location.x * this.getSize(), location.y * this.getSize(), paint);

    }

}
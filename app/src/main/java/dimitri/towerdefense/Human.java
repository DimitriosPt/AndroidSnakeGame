package dimitri.towerdefense;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.util.ArrayList;

class Human extends Enemy {
    Human(Context context, int health, int speed) {
        super(context, health, speed);
        this.setSize(1);

        //humans have no resistances so the list remains empty
        this.setResistances(new ArrayList<Enemy.damageResistances>());
        this.setObjectBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.human));
        this.setHeading(EAST);
    }

    @Override
    void move() {
        Point newLocation = this.getLocation();
        switch (this.getHeading()) {
            case NORTH:
                newLocation.y += this.getSpeed();
                this.setLocation(newLocation);
                break;

            case EAST:
                newLocation.x += this.getSpeed();
                this.setLocation(newLocation);
                break;

            case SOUTH:
                newLocation.y -= this.getSpeed();
                this.setLocation(newLocation);
                break;

            case WEST:
                newLocation.x -= this.getSpeed();
                this.setLocation(newLocation);
                break;

        }
    }

    @Override
    void spawn() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int blockSize = displayMetrics.widthPixels / 40;
        // How many blocks of the same size will fit into the height
        int mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Reset the heading

        // Start with a single snake segment
        this.setLocation(new Point(30, mNumBlocksHigh / 2));
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getSize(), this.getSize(), paint);
    }
}

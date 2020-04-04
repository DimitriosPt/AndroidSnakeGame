package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class Human extends Enemy {
    public Human(int health, int speed) {
        super(health, speed);

        //humans have no resistances so the list remains empty
        this.setResistances(new ArrayList<Enemy.damageResistances>());
        this.setObjectBitmap();

    }

    @Override
    void move(int heading, int speed) {
        Point newLocation = this.getLocation();
        switch (heading) {
            case NORTH:
                newLocation.y += speed;
                this.setLocation(newLocation);
                break;

            case EAST:
                newLocation.x += speed;
                this.setLocation(newLocation);
                break;

            case SOUTH:
                newLocation.y -= speed;
                this.setLocation(newLocation);
                break;

            case WEST:
                newLocation.x -= speed;
                this.setLocation(newLocation);
                break;
        }
    }

    @Override
    void spawn() {

    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getSize(), this.getSize(), paint);
    }
}

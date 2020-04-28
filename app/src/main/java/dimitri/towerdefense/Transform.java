package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class Transform {
    private int xClipping;
    private boolean reversedFirst = false;

    private RectF hitbox;
    private PointF location;
    private float speed;
    private float height;
    private float width;

    Transform(float speed, float width, float height, PointF startingLocation)
    {
        hitbox = new RectF();
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.location = startingLocation;
    }

    void updateCollider(){
// Pull the borders in a bit (10%)
        hitbox.top = location.y + (height / 10);
        hitbox.left = location.x + (width /10);
        hitbox.bottom = (hitbox.top + height)
                - height/10;
        hitbox.right = (hitbox.left + width)
                - width/10;
    }

    public RectF getHitbox() {
        return hitbox;
    }

    public PointF getLocation() {
        return location;
    }

    public float getSpeed() {
        return speed;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setLocation(PointF location) {
        this.location = location;
    }

    public int getxClipping() {
        return xClipping;
    }
}

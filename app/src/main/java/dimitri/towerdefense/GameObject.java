package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

class GameObject {
    private int size;
    private Bitmap objectBitmap;
    private Point location;

    private Transform transform;
    private boolean isActive = false;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;

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

    void setSize(int size) {
        this.size = size;
    }

    void setSpawner(SpawnComponent s) {
        spawnComponent = s;
    }

    void setGraphics(GraphicsComponent g,
                     ObjectSpec spec, PointF objectSize) {
        graphicsComponent = g;
        g.initialize(spec, objectSize);
    }

    void setMovement(MovementComponent m) {
        movementComponent = m;
    }

    void setInput(InputComponent inputComponent) {
        inputComponent.setTransform(transform);
    }

    void setTransform(Transform t) {
        transform = t;
    }

    void draw(Canvas canvas, Paint paint) {
        graphicsComponent.draw(canvas, paint, transform);
    }
    void update(long fps, Transform playerTransform) {
        if (!(movementComponent.move(fps,
                transform))) {
// Component returned false
            isActive = false;
        }
    }
    boolean spawn(Transform playerTransform) {
// Only spawnComponent if not already active
        if (!isActive) {
            spawnComponent.spawn(transform);
            isActive = true;
            return true;
        }
        return false;
    }

    boolean checkActive() {
        return isActive;
    }

    void setInactive() {
        isActive = false;
    }
    Transform getTransform() {
        return transform;
    }
}
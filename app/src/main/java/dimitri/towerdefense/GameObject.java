package dimitri.towerdefense;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

class GameObject {

    private Transform transform;
    private boolean isActive = false;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;

    void setSpawner(SpawnComponent s) {
        spawnComponent = s;
    }

    void setGraphics(GraphicsComponent g, ObjectSpec spec, PointF objectSize) {

        graphicsComponent = g;
        g.initialize(spec, objectSize);
    }

    void setMovement(MovementComponent m) {
        movementComponent = m;
    }

    void setInput(InputComponent s) {
        s.setTransform(transform);
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

    boolean checkActive() {
        return isActive;
    }

    void setInactive() {
        isActive = false;
    }

    boolean spawn(Transform transform) {
        // Only spawnComponent if not already active
        if (!isActive) {
            spawnComponent.spawn(transform);
            isActive = true;
            return true;
        }
        return false;
    }

    Transform getTransform() {
        return transform;
    }
}
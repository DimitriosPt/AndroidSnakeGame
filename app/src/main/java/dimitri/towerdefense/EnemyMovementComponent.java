package dimitri.towerdefense;

import android.graphics.PointF;

public class EnemyMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        PointF location = transform.getLocation();

        Float speed = transform.getSpeed();

        if (location.x < 100) {
            transform.setLocation(new PointF(location.x += speed/fps, location.y));
        }

        else
        {
            location.y += speed/fps;
        }

        transform.updateCollider();

        return true;
    }
}

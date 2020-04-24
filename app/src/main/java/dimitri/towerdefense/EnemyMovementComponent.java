package dimitri.towerdefense;

import android.graphics.PointF;

public class EnemyMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        PointF currentLocation = transform.getLocation();

        if (transform.getLocation().y >= 100) {
            transform.setLocation(new PointF(currentLocation.x, currentLocation.y+= transform.getSpeed()));
        }

        else
        {
            transform.setLocation(new PointF(currentLocation.x+= transform.getSpeed(), currentLocation.y));
        }
        return true;
    }
}

package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class ProjectileMovementStrategy implements MovementStrategy {
    @Override
    public void move(MoveableGameObject self) {
        PointF location = self.getLocation();
        int speed = self.getSpeed();

        location.x += speed * Math.cos(self.getHeading());
        location.y += speed * Math.sin(self.getHeading());
    }
}

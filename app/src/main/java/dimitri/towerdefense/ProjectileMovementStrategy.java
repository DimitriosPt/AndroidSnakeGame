package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class ProjectileMovementStrategy implements MovementStrategy {
    @Override
    public void move(MoveableGameObject self) {
        PointF location = self.getLocation();
        int speed = self.getSpeed();

        //headings are passed around as degrees to maintain their int nature
        //converted to radians here in order to preserve precision
        location.x += speed * Math.cos(Math.toRadians(self.getHeading()));
        location.y += speed * Math.sin(Math.toRadians(self.getHeading()));
    }
}

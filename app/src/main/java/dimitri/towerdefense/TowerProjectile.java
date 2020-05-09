package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;

public class TowerProjectile extends MoveableGameObject {
    public TowerProjectile(int speed, int heading) {
        super(speed);
        this.setHeading(heading);
        this.movementStrategy = new ProjectileMovementStrategy();
    }

    @Override
    void spawn(PointF location) {

        this.setHeading(EAST);

        // Start with a single snake segment
        this.setLocation(location);
    }

    @Override
    void draw(Canvas canvas, Paint paint) {

    }

}

package dimitri.towerdefense;

import android.graphics.PointF;

public class EnemyMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        PointF location = transform.getLocation();
        PointF screenSize = TowerDefense.getScreenSizeF();

        Float speed = transform.getSpeed();

        if (location.y >= TowerDefense.getScreenSizeF().y/4 && location.x > screenSize.x * .40)
        {
            location.y -= speed/fps;
        }

        else if(location.y < (.95 * TowerDefense.getScreenSize().y) && location.x == screenSize.x *.70)
        {
            location.y += speed/fps;
        }

        else
        {
            location.x += speed/fps;
        }

        transform.updateCollider();
        return true;
    }
}

package dimitri.towerdefense;

import android.graphics.PointF;

public class EnemyMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        PointF location = transform.getLocation();
        PointF screenSize = TowerDefense.getScreenSizeF();

        Float speed = transform.getSpeed();

        if (location.x < TowerDefense.getScreenSizeF().x * .4) {
            location.x += speed/fps;
        }

        else if (location.y >= TowerDefense.getScreenSizeF().y/4)
        {
            location.y -= speed/fps;
        }

        else if (location.x < TowerDefense.getScreenSize().x * 75)
        {
            location.x += speed/fps;
        }

        else if(location.y < TowerDefense.getScreenSize().y)
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

package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class LevelOneEnemyMovement implements MovementStrategy {

        @Override
        public void move(Enemy enemy) {
        Point location = enemy.getLocation();
        PointF screenSize = TowerDefense.getScreenSizeF();
        float screenWidth = screenSize.x;
        float screenHeight = screenSize.y;

        if(isBetween(location.x, 0, screenWidth * .40f))
        {
            enemy.setHeading(EAST);
        }

        else if (isBetween(location.x, screenWidth *.38f, screenWidth *.42f)
                && isBetween(location.y, screenHeight * .25f, screenHeight *.65f))
        {
            enemy.setHeading(NORTH);
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.38f, screenHeight * .70f))
        {
            enemy.setHeading(EAST);
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.65f, screenHeight * .72f))
        {
            enemy.setHeading(SOUTH);
        }

        else
        {
            enemy.setHeading(EAST);
        }



            switch (enemy.getHeading()) {
            case NORTH:
                location.y -= enemy.getSpeed();
                enemy.setLocation(location);
                break;

            case EAST:
                location.x += enemy.getSpeed();
                enemy.setLocation(location);
                break;

            case SOUTH:
                location.y += enemy.getSpeed();
                enemy.setLocation(location);
                break;

            case WEST:
                location.x -= enemy.getSpeed();
                enemy.setLocation(location);
                break;
        }

    }
    private boolean isBetween(float value, float lower, float upper)
    {
        return ((value >= lower) && (value <= upper));
    }
}

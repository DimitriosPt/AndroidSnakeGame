package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;

public class LevelOneEnemyMovement implements MovementStrategy {

        @Override
        public void move(MoveableGameObject self) {
        PointF location = self.getLocation();
        PointF screenSize = TowerDefense.getScreenSizeF();
        float screenWidth = screenSize.x;
        float screenHeight = screenSize.y;

        if(isBetween(location.x, 0, screenWidth * .40f))
        {
            self.setHeading(EAST);
        }

        else if (isBetween(location.x, screenWidth *.35f, screenWidth *.45f)
                && isBetween(location.y, screenHeight * .25f, screenHeight *.65f))
        {
            self.setHeading(NORTH);
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.38f, screenHeight * .70f))
        {
            self.setHeading(EAST);
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.65f, screenHeight * .72f))
        {
            self.setHeading(SOUTH);
        }

        else
        {
            self.setHeading(EAST);
        }



            switch (self.getHeading()) {
            case NORTH:
                location.y -= self.getSpeed();
                self.setLocation(location);
                break;

            case EAST:
                location.x += self.getSpeed();
                self.setLocation(location);
                break;

            case SOUTH:
                location.y += self.getSpeed();
                self.setLocation(location);
                break;

            case WEST:
                location.x -= self.getSpeed();
                self.setLocation(location);
                break;
        }

    }
    private boolean isBetween(float value, float lower, float upper)
    {
        return ((value >= lower) && (value <= upper));
    }
}

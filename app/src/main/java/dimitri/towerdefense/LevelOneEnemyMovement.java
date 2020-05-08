package dimitri.towerdefense;

import android.graphics.Point;

public class LevelOneEnemyMovement implements MovementStrategy {

        @Override
        public void move(Enemy enemy) {
        Point newLocation = enemy.getLocation();

        if(newLocation.y >= 100)
        {
            enemy.setHeading(EAST);
        }

        else if(newLocation.x == 50)
        {
            enemy.setHeading(SOUTH);
            enemy.setLocation(new Point(newLocation.x +1, newLocation.y));
        }


        switch (enemy.getHeading()) {
            case NORTH:
                newLocation.y -= enemy.getSpeed();
                enemy.setLocation(newLocation);
                break;

            case EAST:
                newLocation.x += enemy.getSpeed();
                enemy.setLocation(newLocation);
                break;

            case SOUTH:
                newLocation.y += enemy.getSpeed();
                enemy.setLocation(newLocation);
                break;

            case WEST:
                newLocation.x -= enemy.getSpeed();
                enemy.setLocation(newLocation);
                break;
        }
    }
}

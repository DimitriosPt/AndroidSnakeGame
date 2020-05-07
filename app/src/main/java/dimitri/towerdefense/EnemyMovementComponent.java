package dimitri.towerdefense;

import android.graphics.PointF;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

public class EnemyMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {
        PointF location = transform.getLocation();
        PointF screenSize = TowerDefense.getScreenSizeF();
        float screenWidth = screenSize.x;
        float screenHeight = screenSize.y;

        float speed = transform.getSpeed();
        int x_location = (int) location.x;


            /* enemies spawn at x = 0, y = .60 max screen height,
            these cases represent break points along their travel path
             */
        if(isBetween(location.x, 0,  screenWidth * .40f))
        {
            location.x += speed/fps;
        }

        else if (isBetween(location.x, screenWidth *.38f, screenWidth *.42f)
                && isBetween(location.y, screenHeight * .25f, screenHeight *.65f))
        {
            location.y -= speed/fps;
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.38f, screenHeight * .70f))
        {
            location.x += speed/fps;
        }

        else if (isBetween(location.y, screenHeight*.23f, screenHeight *.27f) && isBetween(location.x, screenWidth *.65f, screenHeight * .72f))
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

    //I found this annoying to keep writing over and over again so I just turned it into a utility
    // function to make my code easier to read
    private boolean isBetween(float value, float lower, float upper)
    {
        return ((value >= lower) && (value <= upper));
    }
}

package dimitri.towerdefense;

import android.graphics.PointF;

public class ProjectileMovementComponent implements MovementComponent {
    @Override
    public boolean move(long fps, Transform transform) {


// Where is the laser
        PointF location = transform.getLocation();
// How fast is it going
        float speed = transform.getSpeed();

        location.x += speed / fps;

//        else if(t.headingLeft()){
//            location.x -= speed / fps;
//        }
// Has the laser gone out of range
        if(location.x < -100 || location.x > 100){
// disable the laser
            return false;
        }
        transform.updateCollider();
        return true;
    }
}

package dimitri.towerdefense;

import android.graphics.PointF;

class Particle {
    PointF velocity;
    PointF location;
    Particle(PointF direction)
    {
        velocity = new PointF();
        location = new PointF();
// Determine the direction
        velocity.x = direction.x;
        velocity.y = direction.y;
    }

    void update()
    {
    // Move the particle
        location.x += velocity.x;
        location.y += velocity.y;
    }
    void setPosition(PointF position)
    {
        location.x = position.x;
        location.y = position.y;
    }
    PointF getLocation()
    {
        return location;
    }
}
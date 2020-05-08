package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class Particle extends MoveableGameObject {
    private int damage;
    Context particle_context= TowerDefense.getContext();
    private Bitmap particleBitmap;
    public Particle(int speed, int heading) {
        super(speed);
        this.setHeading(heading);
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(particle_context.getResources(), R.drawable.normal_apple);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        20, 20, false);
        this.setObjectBitmap(scaledBitmap);
    }



    @Override
    void spawn(Point location)
    {

    }

    @Override
    void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);

    }
}
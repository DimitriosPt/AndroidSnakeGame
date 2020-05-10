package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        double scaleFactor = .08;

        Bitmap unscaledBitmap = BitmapFactory.decodeResource(
                TowerDefense.getContext().getResources(), R.drawable.bad_apple);

        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        (int) (TowerDefense.getScreenSize().x * scaleFactor),
                        (int) (TowerDefense.getScreenSize().y * scaleFactor), false);

        this.setObjectBitmap(scaledBitmap);
    }

    @Override
    void spawn(PointF location) {

        this.setHeading(EAST);
        // Start with a single snake segment
        this.setLocation(location);
    }

    @Override
    void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }

}

package dimitri.towerdefense;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;

class TowerProjectile extends MoveableGameObject {

    float maxRange;
    private PointF initialSpawningLocation;

    public TowerProjectile(int speed, int heading, float maxRange) {
        super(speed);
        this.setHeading(heading);
        this.maxRange = maxRange;
        this.movementStrategy = new ProjectileMovementStrategy();
        double scaleFactor = .04;


        Bitmap unscaledBitmap = BitmapFactory.decodeResource(
                TowerDefense.getContext().getResources(), R.drawable.arrow);

        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        (int) (TowerDefense.getScreenSize().x * scaleFactor),
                        (int) (TowerDefense.getScreenSize().y * scaleFactor), false);

        Matrix matrix = new Matrix();

        matrix.postRotate(heading - 90);

        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        this.setObjectBitmap(rotatedBitmap);
    }

    @Override
    void spawn(PointF location) {
        this.setLocation(location);
        this.initialSpawningLocation = new PointF(location.x, location.y);
    }

    @Override
    void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }

    float getDistanceTraveled()
    {
        float initialX = initialSpawningLocation.x;
        float initialY = initialSpawningLocation.y;

        float currentX = this.getLocation().x;
        float currentY = this.getLocation().y;

        float xDifference = initialX - currentX;
        float yDifference = initialY - currentY;
        return (float) Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }
}

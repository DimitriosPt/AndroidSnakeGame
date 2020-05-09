package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

public class Background extends GameObject {
    public Background() {
        Point screenSize = TowerDefense.getScreenSize();
        BitmapFactory bitmap = new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(TowerDefense.getContext().getResources(), R.drawable.background);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        screenSize.x, screenSize.y, false);

        this.setObjectBitmap(scaledBitmap);

    }

    @Override
    void spawn(PointF location) {
        this.setLocation(new PointF(0, 0));
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

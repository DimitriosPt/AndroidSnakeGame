package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

public class Base extends StaticGameObject {
    public Base()
    {
        Context newContext = TowerDefense.getContext();
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(newContext.getResources(), R.drawable.castle);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        300, 300, false);
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        this.setObjectBitmap(rotatedBitmap);

        PointF screenSize = TowerDefense.getScreenSizeF();
        this.setLocation(new PointF(screenSize.x * .90f,screenSize.y * .17f));

    }
    @Override
    void spawn(PointF location) {

    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

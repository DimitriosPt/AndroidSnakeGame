package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

public class BackgroundGraphicsComponent implements GraphicsComponent{
    private Bitmap bitmap;

    @Override
    public void initialize(ObjectSpec spec, PointF size) {
        int resourceID = TowerDefense.getContext().getResources().getIdentifier((spec.getBitmapName()),
                "drawable", TowerDefense.getContext().getPackageName());

        bitmap = BitmapFactory.decodeResource(TowerDefense.getContext().getResources(), resourceID);
        bitmap = Bitmap.createScaledBitmap(bitmap,
                TowerDefense.getScreenSize().x, TowerDefense.getScreenSize().y, false);

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform transform)
    {
        int xClipping = transform.getxClipping();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int startY = 0;
        int endY = TowerDefense.getScreenSize().y + 20;

        Rect fromRect1 = new Rect(0, 0, width - xClipping, height);
        Rect toRect1 = new Rect(xClipping, startY, width, endY);
// For the reversed background
        Rect fromRect2 = new Rect(width - xClipping, 0, width, height);
        Rect toRect2 = new Rect(0, startY, xClipping, endY);
//draw the two background bitmaps

        canvas.drawBitmap(bitmap,
                fromRect1, toRect1, paint);

    }
}

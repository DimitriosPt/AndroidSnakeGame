package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

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
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        canvas.drawBitmap(bitmap, 0 ,0, null);
    }
}

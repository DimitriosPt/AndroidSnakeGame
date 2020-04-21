package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

public class StandardGraphicsComponent implements GraphicsComponent {
    private Bitmap bitmap;
    private Bitmap bitmapReversed;
    private PointF objectSize;


    @Override
    public void initialize(ObjectSpec spec, PointF objectSize) {
        Context context = TowerDefense.getContext();
        int resID = context.getResources()
                .getIdentifier(spec.getBitmapName(),
                        "drawable",
                        context.getPackageName());
// Load the bitmap using the id
        bitmap = BitmapFactory.decodeResource(
                context.getResources(), resID);
// Resize the bitmap
        bitmap = Bitmap
                .createScaledBitmap(bitmap,
                        (int)objectSize.x,
                        (int)objectSize.y,
                        false);
// Create a mirror image of the bitmap if needed
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        bitmapReversed = Bitmap.createBitmap(bitmap,
                0, 0,
                bitmap.getWidth(),
                bitmap.getHeight(),
                matrix, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform transform) {
        canvas.drawBitmap(bitmap, transform.getLocation().x, transform.getLocation().y, paint);
    }
}

package dimitri.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class GoodApple extends Apple {
    GoodApple(Context context, Point sr, int s) {
        super(context, sr, s);

        // Load the image to the bitmap
        Bitmap mBitmapApple =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);

        this.setObjectBitmap(mBitmapApple);
    }

}

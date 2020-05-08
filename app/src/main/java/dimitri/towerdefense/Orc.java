package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;

class Orc extends Enemy {
    Orc(Context context, int health, int speed, List<damageResistances> resistances) {
        super(context, health, speed, resistances);

        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.human);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        60, 60, false);
        this.setObjectBitmap(scaledBitmap);
        this.setLocation(new Point(0,0));
    }
    @Override
    void move() {

    }

    @Override
    void spawn(Point location) {

    }

    @Override
    void draw(Canvas canvas, Paint paint) {

    }
}

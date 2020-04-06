package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class BasicTower extends Tower{
    public BasicTower(Context context){
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.basic_tower);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        200, 200, false);
        this.setObjectBitmap(scaledBitmap);

        this.setLocation(new Point(100,50));
        this.setRange(50);
        this.setDamage(3);
    }

    @Override
    void spawn(Point location) {
        this.setLocation(location);
    }

    @Override
    void attack(int damage, int damageType)
    {
        this.setTimeOfLastAttack(System.currentTimeMillis());
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

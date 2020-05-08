package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;

public class BasicGunTower extends Tower {
    AttackStrategy attackStrategy = new SingleTargetAttackStrategy();
    public BasicGunTower(){
        Context newContext = TowerDefense.getContext();
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(newContext.getResources(), R.drawable.single_target);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        200, 200, false);
        this.setObjectBitmap(scaledBitmap);

        this.setLocation(new Point(100,50));
        this.setRange(200);
        this.setDamage(5);
        this.setAttackSpeed(400);
    }

    @Override
    void attack(List<Enemy> enemies)
    {
        attackStrategy.attack(this, enemies);
    }

    @Override
    void spawn(Point location)
    {
        this.setLocation(location);
    }

    @Override
    void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

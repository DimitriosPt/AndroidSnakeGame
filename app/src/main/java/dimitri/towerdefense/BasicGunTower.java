package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.List;

public class BasicGunTower extends Tower {
    AttackStrategy attackStrategy = new SingleTargetAttackStrategy();
    public BasicGunTower(){
        Context newContext = TowerDefense.getContext();
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(newContext.getResources(), R.drawable.singletargetturret);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        200, 200, false);
        this.setObjectBitmap(scaledBitmap);

        this.setLocation(new PointF(100,50));
        this.setRange(800);
        this.setDamage(10);
        this.setAttackSpeed(400);
    }

    @Override
    List<TowerProjectile> attack(List<Enemy> enemies)
    {
        return attackStrategy.attack(this, enemies);
    }

    @Override
    void spawn(PointF location)
    {
        this.setLocation(location);
    }

    @Override
    void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

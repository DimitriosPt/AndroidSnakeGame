package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.SurfaceView;

import java.util.List;

public class BasicAOETower extends Tower  {
    public BasicAOETower(){
        Context newContext = TowerDefense.getContext();
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(newContext.getResources(), R.drawable.basic_tower);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        200, 200, false);
        this.setObjectBitmap(scaledBitmap);

        this.setLocation(new PointF(100,50));
        this.setRange(100);
        this.setDamage(3);
        this.setAttackSpeed(100);
        this.attackStrategy = new AreaOfEffectAttackStrategy();
    }

    @Override
    void spawn(PointF location) {
        this.setLocation(location);
    }

    @Override
    void attack(List<Enemy> enemies)
    {
        attackStrategy.attack(this, enemies);
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.List;

public class SingleTargetTurret extends Tower {

    //since single targets rotate, they need a heading
    float heading;

    AttackStrategy attackStrategy = new SingleTargetAttackStrategy();
    public SingleTargetTurret(){
        Context newContext = TowerDefense.getContext();
        new BitmapFactory();
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(newContext.getResources(), R.drawable.singletargetturret);
        Bitmap scaledBitmap = Bitmap
                .createScaledBitmap(unscaledBitmap,
                        200, 200, false);
        this.setObjectBitmap(scaledBitmap);


        this.setLocation(new PointF(100,50));
        this.setRange(800);
        this.setDamage(4);
        this.setAttackSpeed(100);
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
        //when you go to draw this turret, rotate it to match the heading which is set
        //when the turret aims at an enemy
        Bitmap currentBitmap = this.getObjectBitmap();
        Matrix matrix = new Matrix();
        float x_center = this.getLocation().x + ((float)this.getObjectBitmap().getWidth())/2;
        float y_center = this.getLocation().x + ((float)this.getObjectBitmap().getHeight())/2;
        matrix.setRotate(this.heading,  x_center, y_center);

        Bitmap rotatedBitmap = Bitmap.createBitmap(
                currentBitmap,
                0,
                0,
                currentBitmap.getWidth(),
                currentBitmap.getHeight(),
                matrix,
                true);

        canvas.drawBitmap(rotatedBitmap, this.getLocation().x, this.getLocation().y, paint);
    }
}

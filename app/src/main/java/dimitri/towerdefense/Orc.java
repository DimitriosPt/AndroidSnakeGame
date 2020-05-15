package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.List;
import java.util.logging.Level;

class Orc extends Enemy {
    Orc(Context context, int maxHealth, int speed, List<damageResistances> resistances) {
        super(context, maxHealth, speed, resistances);
        double scaleFactor = .09;
        this.pointValue =40;
        new BitmapFactory();

        for (int i = 0; i <= 3; i++) {
            //will iterate through all bitmaps and load them into the arraylist
            //bitmaps are named such that the i can be appended to the base name of the
            //bitmap. For example, knight_walk0, knight_walk1
            String imagefileName = "orc_walk" + Integer.toString(i);

            // Retrieving the local Resource ID from the name
            int bitmapID = context.getResources().getIdentifier(
                    imagefileName,
                    "drawable",
                    context.getPackageName());

            Bitmap unscaledBitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    bitmapID);

            Bitmap scaledBitmap = Bitmap
                    .createScaledBitmap(unscaledBitmap,
                            (int) (TowerDefense.getScreenSize().x * scaleFactor),
                            (int) (TowerDefense.getScreenSize().y * scaleFactor), false);

            this.getSpriteSheet().add(i, scaledBitmap);
        }
        //set starting bitmap to first thing in sprite sheet
        this.setObjectBitmap(this.getSpriteSheet().get(0));
        this.setLocation(new PointF(-500, 0));
//        this.movementStrategy = new LevelOneEnemyMovement();
    }
}

package dimitri.towerdefense;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiEnterpriseConfig;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

class Human extends Enemy {
    Human(Context context, int maxHealth, int speed, List<damageResistances> resistances){
        super(context, maxHealth, speed, resistances);
        double scaleFactor = .09;
        new BitmapFactory();

        for (int i = 0; i <= 5 ; i++) {
            //will iterate through all bitmaps and load them into the arraylist
            //bitmaps are named such that the i can be appended to the base name of the
            //bitmap. For example, knight_walk0, knight_walk1
            String imagefileName = "knight_walk" + Integer.toString(i);

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

            this.getSpriteSheet().add(i,scaledBitmap);
        }
        //set starting bitmap to first thing in sprite sheet
        this.setObjectBitmap(this.getSpriteSheet().get(0));
        this.setLocation(new Point(0,0));
        this.movementStrategy = new LevelOneEnemyMovement();
    }

    /*
    @Override
    void spawn(Point location) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int blockSize = displayMetrics.widthPixels / 40;
        // How many blocks of the same size will fit into the height
        int mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Reset the heading
        this.setHeading(EAST);

        // Start with a single snake segment
        this.setLocation(new Point(30, mNumBlocksHigh / 2));
    }*/

    @Override
    void draw(Canvas canvas, Paint paint) {

        //only change the sprite every 200 milliseconds instead of every single frame
        if (System.currentTimeMillis() - timeLastDrawn > 200)
        {
            int currentBitmapIndex = this.getSpriteSheet().indexOf(this.getObjectBitmap());
            int spriteSheetLength = this.getSpriteSheet().size();
            //find whichever bitmap we are currently on, then assign the next one in the array list
            //as our next bitmap. We must go back to the first bitmap in the case we are at the end
            // i.e the next bitmap after knight_walk5 in our case is knight_walk0

            Bitmap nextBitmap = this.getSpriteSheet().get((currentBitmapIndex + 1) % spriteSheetLength);

            this.setObjectBitmap(nextBitmap);
        }

        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

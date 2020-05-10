//Source for orc and human bitmaps here https://opengameart.org/content/knight-of-finlandia

package dimitri.towerdefense;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends MoveableGameObject {
    enum damageResistances{
        PHYSICAL, FIRE, FROST, LIGHTNING, RADIANT
    }
    private List<damageResistances> resistances;
    private int currentHealth=20;
    private int maxHealth;
    private ArrayList spriteSheet;
    public long timeLastDrawn;

    public Enemy(Context context, int maxHealth, int speed, List<damageResistances> resistances) {
        super(speed);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.resistances = resistances;
        this.spriteSheet = new ArrayList<Bitmap>();
    }

    public void setTimeLastDrawn(int timeLastDrawn) {
        this.timeLastDrawn = timeLastDrawn;
    }
    public void setResistances(List<damageResistances> resistances) {
        this.resistances = resistances;
    }

    public List<damageResistances> getResistances() {
        return resistances;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public ArrayList<Bitmap> getSpriteSheet() {
        return spriteSheet;
    }

    void spawn(PointF location) {

        this.setHeading(EAST);

        // Start with a single snake segment
        this.setLocation(location);
    }

    @Override
    void draw(Canvas canvas, Paint paint) {

        //only change the sprite every 200 milliseconds instead of every single frame
        if (System.currentTimeMillis() - timeLastDrawn > 300)
        {
            int currentBitmapIndex = this.getSpriteSheet().indexOf(this.getObjectBitmap());
            int spriteSheetLength = this.getSpriteSheet().size();
            //find whichever bitmap we are currently on, then assign the next one in the array list
            //as our next bitmap. We must go back to the first bitmap in the case we are at the end
            // i.e the next bitmap after knight_walk5 in our case is knight_walk0

            Bitmap nextBitmap = this.getSpriteSheet().get((currentBitmapIndex + 1) % spriteSheetLength);

            this.setObjectBitmap(nextBitmap);
            this.timeLastDrawn = System.currentTimeMillis();
        }

        canvas.drawBitmap(this.getObjectBitmap(), this.getLocation().x, this.getLocation().y, paint);
    }
}

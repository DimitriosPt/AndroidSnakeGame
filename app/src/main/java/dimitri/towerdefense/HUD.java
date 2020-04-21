package dimitri.towerdefense;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD {
    private ArrayList<Rect> controls;

    static int NEWTOWER = 0;
    HUD(){
        prepareControls();
    }

    private void prepareControls(){
        int buttonWidth = TowerDefense.getScreenSize().x /14;
        int buttonHeight = TowerDefense.getScreenSize().y / 12;
        int buttonPadding = TowerDefense.getScreenSize().x / 90;

        Rect newTower = new Rect(
                buttonPadding,
                TowerDefense.getScreenSize().y - (2 * buttonHeight) - (2* buttonPadding),
                buttonWidth + buttonPadding,
                TowerDefense.getScreenSize().y - buttonHeight - (2*buttonPadding));

        controls = new ArrayList<>();
        controls.add(NEWTOWER, newTower);
    }

    void draw(Canvas canvas, GameState gameState)
    {
        int textFormatting = TowerDefense.getScreenSize().x / 50;
        Paint paint = new Paint();

        paint.setColor(Color.argb(255,255,255,255));
        paint.setTextSize(textFormatting);
        canvas.drawText("Score: " + gameState.getMoney(), textFormatting, textFormatting, paint);

        if (gameState.getGameOver()){
            paint.setTextSize(textFormatting * 5);
            canvas.drawText("PRESS PLAY",
                    TowerDefense.getScreenSize().x / 4,
                    TowerDefense.getScreenSize().y / 2,
                    paint);
        }

        else if (gameState.getPaused()){
            paint.setTextSize(textFormatting * 5);
            canvas.drawText("PAUSED",
                    TowerDefense.getScreenSize().x / 4,
                    TowerDefense.getScreenSize().y / 2,
                    paint);
        }

        drawControls(canvas, paint);
    }

    private void drawControls(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(100, 255,255,255));

        for(Rect rectangle : controls)
        {
            canvas.drawRect(rectangle.left, rectangle.top, rectangle.right, rectangle.bottom, paint);
        }

        paint.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Rect> getControls()
    {
        return controls;
    }

}

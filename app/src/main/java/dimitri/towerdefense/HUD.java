package dimitri.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD extends GameObject {
    private int textFormatting;
    private int screenHeight;
    private int screenWidth;
    private ArrayList<Rect> controls;

    public static int reset = 0;
    public static int aoe = 1;
    /*
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 4;
    */

    HUD(Point size) {
        screenHeight = size.y;
        screenWidth = size.x;
        textFormatting = size.x/50;

        makecontrols();
    }


    private void makecontrols() {
        int buttonWidth = screenWidth / 5;
        int buttonHeight = screenHeight / 6;
        int buttonLength = screenWidth / 30;

        // RESET
        Rect RESET = new Rect((screenWidth)-(5*buttonLength),
                screenHeight - (int) (1.9*buttonHeight ),
                (screenWidth) - (buttonLength),
                screenHeight - buttonHeight);


        Rect AOE = new Rect((buttonLength),
                screenHeight - (int)(1.9*buttonHeight ), (int)(5*buttonLength), (int) (screenHeight - buttonHeight));
/*
        Rect AOE = new Rect( (buttonLength),
                screenHeight - (2*buttonHeight),
                 (2*buttonLength),
                screenHeight - buttonLength);

        /*
        Rect pause = new Rect(
                screenWidth - buttonLength -
                        buttonWidth,
                buttonLength,
                screenWidth - buttonLength,
                buttonLength + buttonHeight);
*/

        controls = new ArrayList<>();
        controls.add(0,RESET);
        controls.add(1,AOE);


    }
    /*
    void draw(Canvas c, Paint p, GameController gs) {
// Draw the HUD
        p.setColor(Color.argb(255, 255, 255, 255));
        p.setTextSize(textFormatting);
    }*/

    private void drawControls(Canvas c, Paint p) {
        p.setColor(Color.argb(100, 255, 255, 255));
        for (Rect r : controls) {
            c.drawRect(r.left, r.top, r.right, r.bottom, p);
        }
// Set the colors back
        p.setColor(Color.argb(255, 255, 255, 255));
    }

    ArrayList<Rect> getControls() {
        return controls;
    }

    @Override
    void spawn(PointF location) {

    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(100, 255, 255, 255));
        for (Rect r : controls) {
            canvas.drawRect(r.left, r.top, r.right, r.bottom, paint);

        }
    }

}

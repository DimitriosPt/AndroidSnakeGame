package dimitri.towerdefense;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class TowerDefense extends Application {
    private static TowerDefense instance;

    public static TowerDefense getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static Point getScreenSize(){
        WindowManager wm = (WindowManager) TowerDefense.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return new Point(metrics.widthPixels,
                metrics.heightPixels);
    }

    public static PointF getScreenSizeF(){
        WindowManager wm = (WindowManager) TowerDefense.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return new PointF((float)metrics.widthPixels,
                (float)metrics.heightPixels);
    }
}
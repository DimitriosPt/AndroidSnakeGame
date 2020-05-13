package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.List;
// ONLY ADDS BACKGROUND
public class Level2 extends  Levels
{
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Level2(int level_counter)
    {
        super(level_counter);
    }
}
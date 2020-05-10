package dimitri.towerdefense;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import java.util.List;
// ONLY ADDS BACKGROUND
public class Level1 extends  Levels
{
    Background background = new Background();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Level1(int level_counter)
    {
        super(level_counter);
    }
    }

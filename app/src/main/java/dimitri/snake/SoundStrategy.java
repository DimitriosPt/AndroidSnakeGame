package dimitri.snake;

import android.content.Context;
import android.media.SoundPool;

public interface SoundStrategy {

    public void playEatAppleSound(Context context);
    public void playCollisionSound(Context context);
    public void initializeSound(Context context);
}

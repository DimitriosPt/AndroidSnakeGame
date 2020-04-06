package dimitri.towerdefense;

import android.content.Context;

public interface SoundStrategy {
    public void playEatAppleSound(Context context);
    public void playCollisionSound(Context context);
    public void initializeSound(Context context);
}

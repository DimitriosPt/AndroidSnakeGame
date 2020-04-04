package dimitri.towerdefense;

import android.content.Context;

public class SoundContext {
    private final SoundStrategy strategy;

    public SoundContext(SoundStrategy strategy){
        this.strategy = strategy;
    }

    public void playEatAppleSound(Context context){
        strategy.playEatAppleSound(context);
    }

    public void playCollisionSound(Context context){
        strategy.playCollisionSound(context);
    }

    public void initializeSound(Context context)
    {
        strategy.initializeSound(context);
    }
}

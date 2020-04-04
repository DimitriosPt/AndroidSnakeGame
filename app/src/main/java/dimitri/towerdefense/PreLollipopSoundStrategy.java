package dimitri.towerdefense;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class PreLollipopSoundStrategy implements SoundStrategy{

    private SoundPool mSP;
    private int mEat_ID;
    private int mCrashID;

    @Override
    public void playEatAppleSound(Context context)
    {
        mSP.play(mEat_ID, 1, 1, 0, 0, 1);
    }

    @Override
    public void playCollisionSound(Context context) {
        mSP.play(mCrashID, 1, 1, 0, 0, 1);
    }

    @Override
    public void initializeSound(Context context) {

        mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        try{
            mEat_ID = -1;
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Prepare the sounds in memory
            descriptor = assetManager.openFd("get_apple.ogg");
            mEat_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_death.ogg");
            mCrashID = mSP.load(descriptor, 0);
        }
        catch (IOException e) {
            System.out.println("Sound files could not be loaded");;
        }
    }
}

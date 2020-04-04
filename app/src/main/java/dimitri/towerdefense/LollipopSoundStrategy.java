package dimitri.towerdefense;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LollipopSoundStrategy implements SoundStrategy {

    SoundPool mSP;
    int mEat_ID;
    int mCrashID;

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

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        mSP = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

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

package dimitri.snake;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // A snake ssss
    private dimitri.snake.Snake mSnake;
    // And an normal_apple

    public List<Apple> appleList = new CopyOnWriteArrayList<Apple>();
    private int additionalApples = 0;
    public SoundContext soundContext;
    public Context mContext;


    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);

        mContext = context;
        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // Initialize the SoundPool
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundContext = new SoundContext(new LollipopSoundStrategy());
            soundContext.initializeSound(context);
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .build();
//
//            mSP = new SoundPool.Builder()
//                    .setMaxStreams(5)
//                    .setAudioAttributes(audioAttributes)
//                    .build();
        } else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
//        try {
//            AssetManager assetManager = context.getAssets();
//            AssetFileDescriptor descriptor;
//
//            // Prepare the sounds in memory
//            descriptor = assetManager.openFd("get_apple.ogg");
//            mEat_ID = mSP.load(descriptor, 0);
//
//            descriptor = assetManager.openFd("snake_death.ogg");
//            mCrashID = mSP.load(descriptor, 0);
//
//        } catch (IOException e) {
//            // Error
//        }

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        Bitmap tempBitmap = new BitmapFactory().decodeResource(context.getResources(), R.drawable.normal_apple);

        //appleList = new ArrayList<dimitri.snake.Apple>();

        // initialize appleList with a good normal_apple as starting the game with a bad normal_apple
        // would be rude
        appleList.add(new Apple.AppleBuilder(new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh))
                .location(new Point(0, -10))
                .size(blockSize)
                .isGood(true)
                .pointValue(2)
                .spawnRange(new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh))
                .bitmap(Bitmap.createScaledBitmap(tempBitmap, blockSize, blockSize, false))
                .build());

        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

    }


    // Called to start a new game
    public void newGame() {

        // reset the snake
        mSnake.spawn();

        if(appleList.size() > 1) //removes everything but our default spawn normal_apple which
                                // will always be first in the list
        {
            appleList.subList(1, appleList.size()).clear();
        }

        // Get the normal_apple ready for dinner

        for (Apple apple:appleList)
        {
            apple.spawn();
        }

        // Reset the mScore
        mScore = 0;

        additionalApples = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }


    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }

            draw();
        }
    }


    // Check to see if it is time for an update
    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }


    // Update all the game objects
    public void update() {

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int blockSize = displayMetrics.widthPixels / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = displayMetrics.heightPixels / blockSize;
        // Move the snake
        mSnake.move();


        Random random = new Random();

        Bitmap tempBitmap;
        // Did the head of the snake eat an normal_apple?
        for (Apple apple : appleList) {
            if (mSnake.checkDinner(apple.getLocation())) {

                mScore = mScore + apple.pointValue;

                if (apple.isGood) {

                    // Play a sound
                    soundContext.playEatAppleSound(mContext);
//                    mSP.play(mEat_ID, 1, 1, 0, 0, 1);


                    //if the score passes a multiple of 5
                    //this should prevent dropping down in score and going up again
                    //from spawning extra apples
                    if (mScore / 5 > additionalApples) {
                        additionalApples = additionalApples + 1;
                        Random appleSpawner = new Random();
                        boolean appleState = true;
                        int appleValue = 2;
                        int mapToUse = R.drawable.normal_apple;

                        //get it? Because random seed?
                        int appleSeed = appleSpawner.nextInt(5);

                        if (appleSeed == 0) //normal_apple will be good unless
                                                            //the normal_apple spawner is 0, 20% chance
                        {
                            appleState = false;
                            mapToUse = R.drawable.bad_apple;
                            appleValue = -2;

                        }
                        else if (appleSeed < 3)
                        {
                            mapToUse = R.drawable.low_apple;
                            appleValue = 1;

                        }
                        else if (appleSeed == 3)
                        {
                            mapToUse = R.drawable.normal_apple;
                            appleValue = 2;
                        }
                        else
                        {
                            mapToUse = R.drawable.best_apple;
                            appleValue = 3;
                        }

                        new BitmapFactory();
                        tempBitmap = BitmapFactory.decodeResource(getContext().getResources(),
                                mapToUse);

                        Apple tempApple = new Apple.AppleBuilder(new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh))
                                .location(new Point(0, -10))
                                .size(blockSize)
                                .isGood(appleState)
                                .spawnRange(new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh))
                                .bitmap(Bitmap.createScaledBitmap(tempBitmap, blockSize, blockSize, false))
                                .pointValue(appleValue)
                                .build();

                        tempApple.spawn();

                        appleList.add(tempApple);
                    }
                }

                apple.spawn();
            }
        }

        // Did the snake die?
        if (mSnake.detectDeath()) {
            // Pause the game ready to start again
            soundContext.playCollisionSound(mContext);
//            mSP.play(mCrashID, 1, 1, 0, 0, 1);

            mPaused = true;
        }

    }

    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            // Objects for drawing
            Canvas mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);

            // Draw the normal_apple and the snake
            //mApple.draw(mCanvas, mPaint);
            for(Apple apple : appleList)
            {
                apple.draw(mCanvas, mPaint);
            }

            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mPaused){

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon
                //mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
                mCanvas.drawText(getResources().
                                getString(R.string.tap_to_play),
                        200, 700, mPaint);
            }


            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (mPaused) {
                    mPaused = false;
                    newGame();

                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
                break;

            default:
                break;

        }
        return true;
    }


    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}
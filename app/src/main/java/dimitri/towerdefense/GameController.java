package dimitri.towerdefense;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.SoundPool;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.GridLayoutAnimationController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController extends SurfaceView implements Runnable{

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
    private dimitri.towerdefense.Snake mSnake;
    private Human human;
    private BasicTower basicTower;
    // And an normal_apple

    private int additionalApples = 0;
    public SoundContext soundContext;
    public Context mContext;
    public boolean muteGameSound = true;

    private GameWorld world = new GameWorld();


    // This is the constructor method that gets called
    // from SnakeActivity
    public GameController(Context context, Point size) {
        super(context);

        mContext = context;
        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // Initialize the SoundPool
        //begin with mute option because it is the cheapest to implement
        //resource-wise
        if(muteGameSound)
        {
            soundContext = new SoundContext(new SilentSoundStrategy());
            soundContext.initializeSound(context);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundContext = new SoundContext(new LollipopSoundStrategy());
            soundContext.initializeSound(context);

        }
        else
        {
            soundContext = new SoundContext(new PreLollipopSoundStrategy());
            soundContext.initializeSound(context);
        }

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();


        //appleList = new ArrayList<dimitri.snake.Apple>();

        // initialize appleList with a good normal_apple as starting the game with a bad normal_apple
        // would be rude
        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        human = new Human(context, 10, 20, new ArrayList<Enemy.damageResistances>());
        basicTower = new BasicTower(context);


    }


    // Called to start a new game
    public void newGame() {

        world = new GameWorld();

        world.addGameObjectToList(human);
        world.addGameObjectToList(basicTower);

        mSnake.spawn(new Point(0, 0));
        human.spawn(new Point(0, 200));
        basicTower.spawn(new Point(600, 12));

        // Reset the mScore
        mScore = 0;

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
        //human.move();


        for (Tower tower:world.getTowers())
        {
            System.out.printf("Tower Location: %d %d", tower.getLocation().x, tower.getLocation().y);
//            if(tower.canAttack())
//            {
                for(Enemy enemy:world.getEnemies())
                {
                    double xDistance = (double) (tower.getLocation().x - enemy.getLocation().x);
                    double yDistance = (double) (tower.getLocation().y - enemy.getLocation().y);
                    double distanceFromTowerToEnemy =
                            Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

                    if(distanceFromTowerToEnemy < tower.getRange())
                    {
                        enemy.setHealth(enemy.getHealth() - tower.getDamage());
                    }

                    if (enemy.getHealth() <= 0)
                    {
                        world.removeGameObjectFromList(enemy);
                    }
                }
//            }

        }
        for (Enemy enemy: world.getEnemies()) {
            enemy.move();
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

//            new BitmapFactory();
//            Bitmap unscaledBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.background);
//            Bitmap scaledBitmap = Bitmap
//                    .createScaledBitmap(unscaledBitmap,
//                            1000, 720, false);
//            mCanvas.drawBitmap(scaledBitmap, 0, 0, mPaint);

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);

            for (GameObject object:world.getGameObjectList()) {
                object.draw(mCanvas, mPaint);
            }

            //human.draw(mCanvas, mPaint);
            mSnake.draw(mCanvas, mPaint);
            //basicTower.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mPaused){

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon
                //mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
//                mCanvas.drawText(getResources().
//                                getString(R.string.tap_to_play),
//                        200, 700, mPaint);
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
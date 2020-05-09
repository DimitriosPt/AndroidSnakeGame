package dimitri.towerdefense;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.SoundPool;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class GameController extends SurfaceView implements Runnable {

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
    ArrayList<GameObject> gameObjects;
    // How many points does the player have
    private int mScore;
    private Levels levels;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private ArrayList<Enemy> enemies;
    private Human human;
    private BasicAOETower basicAOETower;
    private BasicGunTower basicGunTower;
    private Background background;
    private int level_counter = 0;
    // And an normal_apple
    private GameObject gs1;
    private int additionalApples = 0;
    public SoundContext soundContext;
    public Context mContext;
    public boolean muteGameSound = true;

    private GameWorld world = new GameWorld();
    private Object GameObject;


    // This is the constructor method that gets called
    // from SnakeActivity
    public GameController(Point size) {
        super(TowerDefense.getContext());
        Context context = TowerDefense.getContext();
        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // Initialize the SoundPool
        //begin with mute option because it is the cheapest to implement
        //resource-wise
        if (muteGameSound) {
            soundContext = new SoundContext(new SilentSoundStrategy());
            soundContext.initializeSound(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundContext = new SoundContext(new LollipopSoundStrategy());
            soundContext.initializeSound(context);

        } else {
            soundContext = new SoundContext(new PreLollipopSoundStrategy());
            soundContext.initializeSound(context);
        }

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        //background=new Background();
       //gs1 = new Human(context, 10, 20, new ArrayList<Enemy.damageResistances>());
        //basicAOETower= new BasicAOETower();
        //basicGunTower = new BasicGunTower();
    }


    // Called to start a new game
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void newGame() {

        mPlaying = true;
        levels = new Level1(level_counter);
        System.out.println("===============new game =====================");
        System.out.printf("thread: %s\n playing: %s\n paused: %s\n", mThread.toString(), mPlaying, mPaused);
        System.out.printf("Num of World objects %d\n", world.getGameObjectList().size());

        Point screenSize = TowerDefense.getScreenSize();


        for (GameObject gs : levels.getObjects()) {
            world.addGameObjectToList(gs);
            if (gs instanceof Background) {
                gs.spawn(new Point(0, 0));
            }

            if (gs instanceof Enemy) {

                gs.spawn(new Point(0, (int) (TowerDefense.getScreenSize().y * .60)));
                System.out.println(gs.getLocation());
            }


            if (gs instanceof BasicGunTower) {
                gs.spawn(new Point(new Point(screenSize.x / 2, screenSize.y / 2)));
            }
            if (gs instanceof BasicAOETower) {
                gs.spawn(new Point(600, 112));
            }
        }
        System.out.printf("Num of World objects %d\n", world.getGameObjectList().size());


        //world.addGameObjectToList(background);

         // world.addGameObjectToList(gs1);
        //System.out.printf("Human hp: %d", gs1.getCurrentHealth());
       // world.addGameObjectToList(basicAOETower);
        //world.addGameObjectToList(basicGunTower);


       // background.spawn(new Point(0,0));
      //  gs1.spawn(new Point(0, (int) (TowerDefense.getScreenSize().y * .60)));
        //basicAOETower.spawn(new Point(600, 112));
        //basicGunTower.spawn(new Point(screenSize.x /2, screenSize.y/2));

        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }


    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if (!mPaused) {
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
        if (mNextFrameTime <= System.currentTimeMillis()) {
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime = System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }


    // Update all the game objects
    public void update() {
        System.out.println("In the update method");
        if (mPlaying) {
            for (Tower tower : world.getTowers()) {
                if (tower.canAttack()) {
                    System.out.println(tower.canAttack());
                    tower.attack(world.getEnemies());

                    for (Enemy enemy : world.getEnemies()) {
                        System.out.println(enemy.getCurrentHealth());
                        if (enemy.getCurrentHealth() <= 0) {
                            world.removeGameObjectFromList(enemy);
                        }
                    }
                }
            }

            for (Enemy enemy : world.getEnemies()) {
                enemy.movementStrategy.move(enemy);
            }

            //pause if all enemies in the wave are killed
            if (world.getEnemies().isEmpty()) {

                mPlaying = false;
                world.clear();
                newGame();
                level_counter++;
            }

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

            world.draw(mCanvas, mPaint);

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

                    //newGame();

                    return true;
                }

                break;

            default: {
                System.out.println(motionEvent.getX());
                System.out.println(motionEvent.getY());
            }
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
            System.out.println("Error in pause");
        }
    }

        // Start the thread
    public void resume() {
        System.out.println("calling resume");
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}
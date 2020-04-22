package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;

class GameEngine extends SurfaceView implements Runnable , GameStarter, GameEngineBroadcaster, TowerProjectileSpawner{
    private Thread thread = null;
    private long frameRate;
    private GameState gameState;
    private ArrayList<InputObserver> inputObservers = new ArrayList<>();
    Level level;

    UIController uiController;

    HUD hud;
    Renderer renderer;
    public GameEngine(Point size) {
        super(TowerDefense.getContext());
        gameState = new GameState(this);
        hud = new HUD();
        renderer = new Renderer(this);
        uiController = new UIController(this);
        level = new Level(TowerDefense.getScreenSizeF(), this);
    }

    @Override
    public void run() {

        while(gameState.getThreadRunning())
        {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> gameObjects = level.getGameObjects();

            if(!gameState.getPaused())
            {

            }

            renderer.draw(gameObjects, gameState, hud);
            long timeElapsed = System.currentTimeMillis() - frameStartTime;

            if(timeElapsed >= 1){
                final int MILLIS_IN_SECOND = 1000;
                frameRate = MILLIS_IN_SECOND / timeElapsed;
            }


        }


    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        for (InputObserver observer : inputObservers)
        {
            observer.handleInput(motionEvent, gameState, hud.getControls());
        }
        return true;
    }
    public void stopThread() {
// New code here soon

        gameState.stopEverything();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Exception","stopThread()"
                    + e.getMessage());
        }
    }
    public void startThread() {
// New code here soon
        gameState.startThread();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void despawnRespawn() {
        ArrayList<GameObject> objects = level.getGameObjects();
        for(GameObject o : objects){
            o.setInactive();
        }
//        objects.get(Level.PLAYER_INDEX)
//                .spawn(objects.get(Level.PLAYER_INDEX)
//                        .getTransform());
//        objects.get(Level.BACKGROUND_INDEX)
//                .spawn(objects.get(Level.PLAYER_INDEX)
//                        .getTransform());
    }


    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);

    }

    @Override
    public boolean spawnTowerProjectile(Transform transform) {
//        ArrayList<GameObject> objects = level.getGameObjects();
//        if (objects.get(Level.mNextPlayerLaser)
//                .spawn(transform)) {
//            Level.mNextPlayerLaser++;
//            if (Level.mNextPlayerLaser ==
//                    Level.LAST_PLAYER_LASER + 1) {
//// Just used the last laser
//                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER;
//            }
        //}
        return true;
    }
}

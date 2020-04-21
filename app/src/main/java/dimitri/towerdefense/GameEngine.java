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

    UIController uiController;

    HUD hud;
    Renderer renderer;
    public GameEngine(Point size) {
        super(TowerDefense.getContext());
        gameState = new GameState(this);
        hud = new HUD();
        renderer = new Renderer(this);
        uiController = new UIController(this);
    }

    @Override
    public void run() {

        while(gameState.getThreadRunning())
        {
            long frameStartTime = System.currentTimeMillis();
            if(!gameState.getPaused())
            {

            }

            long timeElapsed = System.currentTimeMillis() - frameStartTime;

            if(timeElapsed >= 1){
                final int MILLIS_IN_SECOND = 1000;
                frameRate = MILLIS_IN_SECOND / timeElapsed;
            }

            renderer.draw(gameState, hud);

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

    }


    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);

    }

    @Override
    public boolean spawnTowerProjectile(Transform transform) {
        return false;
    }
}

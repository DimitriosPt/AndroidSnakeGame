package dimitri.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

class GameEngine extends SurfaceView implements Runnable , GameStarter{
    private Thread thread = null;
    private long frameRate;
    private GameState gameState;

    public GameEngine(Point size) {
        super(TowerDefense.getContext());
        gameState = new GameState(this);
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

        }


    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
// Handle the player's input here
// But in a new way
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
}

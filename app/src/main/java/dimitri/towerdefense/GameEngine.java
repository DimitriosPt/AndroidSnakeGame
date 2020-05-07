package dimitri.towerdefense;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

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
    ParticleSystem particleSystem;
    PhysicsEngine physicsEngine;

    public GameEngine(Point size) {
        super(TowerDefense.getContext());
        uiController = new UIController(this);
        gameState = new GameState(this);

        hud = new HUD();
        renderer = new Renderer(this);
        physicsEngine = new PhysicsEngine();

        particleSystem = new ParticleSystem();
        particleSystem.init(1000);

        level = new Level(new PointF(size.x, size.y), this);
    }

    @Override
    public void run() {

        while(gameState.getThreadRunning())
        {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> gameObjects = level.getGameObjects();

            if(!gameState.getPaused())
            {
                if(physicsEngine.update(frameRate, gameObjects,gameState,particleSystem)) {
                    despawnRespawn();
                }
            }

            renderer.draw(gameObjects, gameState, hud, particleSystem);
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

//        particleSystem.emitParticles(
//                new PointF(500,500));
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
            System.out.println("Spawning Object");
            o.spawn(o.getTransform());
        }

//        objects.get(Level.BACKGROUND_INDEX)
//                .spawn(objects.get(Level.BACKGROUND_INDEX)
//                        .getTransform());
    }


    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);

    }

    @Override
    public boolean spawnTowerProjectile(Transform transform) {
        ArrayList<GameObject> objects = level.getGameObjects();
        if (objects.get(Level.NextTowerProjectile)
                .spawn(transform)) {
            Level.NextTowerProjectile++;
            if (Level.NextTowerProjectile ==
                    Level.LAST_TOWER_PROJECTILE + 1) {
// Just used the last laser
                Level.NextTowerProjectile = Level.FIRST_TOWER_PROJECTILE;
            }
        }
        return true;
    }
}

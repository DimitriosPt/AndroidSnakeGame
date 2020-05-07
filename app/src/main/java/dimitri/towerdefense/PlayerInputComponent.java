package dimitri.towerdefense;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

class PlayerInputComponent implements InputComponent, InputObserver {

    private Transform transform;
    //private PlayerLaserSpawner mPLS;

    PlayerInputComponent(GameEngine gameEngine) {
        gameEngine.addObserver(this);
        //mPLS = gameEngine;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    // Required method of InputObserver interface called from the onTouchEvent method
    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons) {
        // In each MotionEvent object, every active pointer is present. Therefore looping through them all for an event on only one of them is obviously wrong.
        // The getActionIndex returns the index in the array of the pointer that performed/trigged the action/method call. So using getX(i) and getY(i) only gets a true result
        // on the button that was actually pressed/removed
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//
//            case MotionEvent.ACTION_UP:
//                if (buttons.get(HUD.UP).contains(x,y)
//                        || buttons.get(HUD.DOWN).contains(x,y)) {
//
//                    // Player has released either up or down
//                    transform.stopVertical();
//                }
//                break;
//
//            case MotionEvent.ACTION_DOWN:
//                if (buttons.get(HUD.UP).contains(x,y)) {
//                    // Player has pressed up
//                    transform.headUp();
//                } else if (buttons.get(HUD.DOWN).contains(x,y)) {
//                    // Player has pressed down
//                    transform.headDown();
//                } else if (buttons.get(HUD.FLIP).contains(x,y)) {
//                    // Player has released the flip button
//                    transform.flip();
//                } else if (buttons.get(HUD.SHOOT).contains(x,y)) {
//                    mPLS.spawnPlayerLaser(transform);
//                }
//                break;
//
//            case MotionEvent.ACTION_POINTER_UP:
//                if (buttons.get(HUD.UP).contains(x, y)
//                        || buttons.get(HUD.DOWN).contains(x, y)) {
//                    // Player has released either up or down
//                    transform.stopVertical();
//                }
//                break;
//
//            case MotionEvent.ACTION_POINTER_DOWN:
//                if (buttons.get(HUD.UP).contains(x, y)) {
//                    // Player has pressed up
//                    transform.headUp();
//                } else if (buttons.get(HUD.DOWN).contains(x, y)) {
//                    // Player has pressed down
//                    transform.headDown();
//                } else if (buttons.get(HUD.FLIP).contains(x, y)) {
//                    // Player has released the flip button
//                    transform.flip();
//                } else if (buttons.get(HUD.SHOOT).contains(x, y)) {
//                    mPLS.spawnPlayerLaser(transform);
//                }
//                break;
//        }
    }
}
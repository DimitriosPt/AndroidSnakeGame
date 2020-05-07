package dimitri.towerdefense;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class UIController implements InputObserver {
    public UIController(GameEngineBroadcaster broadcaster)
    {
        broadcaster.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> controls)
    {
        int index = event.getActionIndex();
        int index_x = (int) event.getX(index);
        int index_y = (int) event.getY(index);

        int eventType = event.getAction() & MotionEvent.ACTION_MASK;

        if((eventType == MotionEvent.ACTION_UP) || (eventType == MotionEvent.ACTION_POINTER_UP))
        {
            if(controls.get(HUD.PAUSE).contains(index_x,index_y))
            {
                if(!gameState.getPaused())
                {
                    gameState.pause();
                }

                else if (gameState.getGameOver())
                {
                    gameState.startNewGame();
                }

                else if (gameState.getPaused() && !gameState.getGameOver())
                {
                    gameState.resume();
                }
            }

            if(controls.get(HUD.RESTART).contains(index_x,index_y))
            {
                gameState.startNewGame();
            }


        }
    }
}

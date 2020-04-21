package dimitri.towerdefense;


import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;


public class TDActivty extends Activity {

    // Declare an instance of SnakeGame
    GameController towerDefenseGame;

    // Set the game up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create a new instance of the SnakeEngine class
        towerDefenseGame = new GameController(size);


        // Make snakeEngine the view of the Activity
        setContentView(towerDefenseGame);
    }

    // Start the thread in snakeEngine
    @Override
    protected void onResume() {
        super.onResume();
        towerDefenseGame.resume();
    }

    // Stop the thread in snakeEngine
    @Override
    protected void onPause() {
        super.onPause();
        towerDefenseGame.pause();
    }

    public Point getDisplaySize(){
        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
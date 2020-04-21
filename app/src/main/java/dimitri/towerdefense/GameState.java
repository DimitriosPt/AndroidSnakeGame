package dimitri.towerdefense;

import android.content.Context;
import android.content.SharedPreferences;

final class GameState {
    private static volatile boolean threadRunning = false;
    private static volatile boolean paused = true;
    private static volatile boolean gameOver = true;
    private static volatile boolean drawing = false;

    private GameStarter gameStarter;

    private int highScore;
    private int score;
    private int money;
    private int wave;

    private SharedPreferences.Editor editor;

    GameState(GameStarter gameStarter)
    {
        gameStarter = gameStarter;

        SharedPreferences preferences;
        preferences = TowerDefense.getContext().getSharedPreferences("HighScore", Context.MODE_PRIVATE);

        editor = preferences.edit();

        highScore = preferences.getInt("HighScore", 0);
    }
}

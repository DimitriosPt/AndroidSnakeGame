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
    private int health;

    private SharedPreferences.Editor editor;

    GameState(GameStarter gameStarter)
    {
        gameStarter = gameStarter;

        SharedPreferences preferences;
        preferences = TowerDefense.getContext().getSharedPreferences("HighScore", Context.MODE_PRIVATE);

        editor = preferences.edit();

        highScore = preferences.getInt("HighScore", 0);
    }

    private void endGame()
    {
        gameOver = true;
        paused = true;
        if(score > highScore)
        {
            highScore = score;
            editor.putInt("HighScore", highScore);
            editor.commit();
        }
    }

    private void startNewGame()
    {
        score = 0;
        money = 200;

        stopDrawing();
        gameStarter.despawnRespawn();
        resume();

        startDrawing();
    }

    void loseHealth(){
        health--;
        if(health == 0)
        {
            paused = true;
            endGame();
        }
    }

    private void resume()
    {
        gameOver = false;
        paused = false;
    }

    void stopEverything(){
        paused = true;
        gameOver = true;
        threadRunning = false;
    }
    boolean getThreadRunning(){
        return threadRunning;
    }
    void startThread(){
        threadRunning = true;
    }
    private void stopDrawing(){
        drawing = false;
    }
    private void startDrawing(){
        drawing = true;
    }
    boolean getDrawing() {
        return drawing;
    }
    boolean getPaused(){
        return paused;
    }
    boolean getGameOver(){
        return gameOver;
    }

}

package Game.States;

import java.awt.event.KeyEvent;

public class GameState {

    public enum State {MENU, LEVEL, GAMEOVER}
    private static GameState currentState;
    private static StartMenu startMenu;
    private static Level level;
    private static GameOver gameOver;

    public GameState() {}

    public GameState(State state) {
        loadState(state);
    }

    public static GameState getCurState(){
        return currentState;
    }

    public static StartMenu getstartMenu(){
        return startMenu;
    }

    public static Level getlevel(){
        return level;
    }

    public static GameOver getgameOver(){
        return gameOver;
    }

    public void loadState(State state) {
        if (state == State.MENU) {
            startMenu = new StartMenu(this);
            currentState = startMenu;
        }

        if (state == State.LEVEL) {
            level = new Level(this);
            currentState = level;
        }

        if (state == State.GAMEOVER) {
            gameOver = new GameOver(this);
            currentState = gameOver;
        }
    }

    public void loadNewLevel(){
        level.loadNew();
        currentState = level;
    }

    public void update(long time) {
        if(currentState == level)
            level.update(time);
    }

    public void keyPressed(KeyEvent key) {
        currentState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        currentState.keyReleased(key);
    }
}
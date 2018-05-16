package Game.States;

import java.awt.event.KeyEvent;

public class GameState {

    private static State[] gameStates;
    private static int current;

    public static final int STARTMENU = 0;
    public static final int LEVEL = 1;
    public static final int GAMEOVER = 2;

    private static StartMenu startMenu;
    private static Level level;
    private static GameOver gameOver;

    public static int getCurState(){
        return current;
    }

    public GameState() {
        gameStates = new State[3];

        current = STARTMENU;
        loadState(current);
    }

    public void loadState(int state) {
        if (state == STARTMENU) {
            startMenu = new StartMenu(this);
            gameStates[state] = startMenu;
            current = STARTMENU;
        }

        if (state == LEVEL) {
            level = new Level(this);
            gameStates[state] = level;
            current = LEVEL;
        }

        if (state == GAMEOVER) {
            gameOver = new GameOver(this);
            gameStates[state] = gameOver;
            current = GAMEOVER;
        }
    }

    public void reloadState(int state){
        current = state;
        gameStates[current].reload();
    }

    public void update(long time) {
        if(gameStates[current] != null) gameStates[current].update(time);
    }

    public void keyPressed(KeyEvent key) {
        gameStates[current].keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameStates[current].keyReleased(key);
    }

    public static Level getlevel(){
        return level;
    }
}
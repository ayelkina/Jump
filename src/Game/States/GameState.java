package Game.States;

import Game.GameManagement.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState {

    public enum State {MENU, LEVEL, GAMEOVER}
    private  static  GameState currentState;
    private  static  StartMenu startMenu;
    private static   Level level;
    private  static  GameOver gameOver;

    public GameState() {}

    public GameState(State state) {
        loadState(state);
    }

    public static GameState getCurState(){
        return currentState;
    }

    public static GameState getstartMenu(){
        return startMenu;
    }

    public static GameState getlevel(){
        return level;
    }

    public static GameState getgameOver(){
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

    public static State getCurrentState() {
        if(currentState == startMenu)
            return State.MENU;

        if(currentState == level)
            return State.LEVEL;

        else
            return State.GAMEOVER;
    }

    public void update(long time) {
        if(currentState == level)
            level.update(time);
    }
    public void draw(Graphics2D g) {
        /*if(currentState == startMenu)
            startMenu.draw(g);

        if(currentState == level)
            level.draw(g);

        else
            gameOver.draw(g);*/
        currentState.draw(g);
    }

    public void keyPressed(KeyEvent key) {
        currentState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        currentState.keyReleased(key);
    }
}
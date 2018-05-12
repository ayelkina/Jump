package Game.States;

import Game.GameManagement.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState {

    public enum State {MENU, LEVEL, GAMEOVER}
    private GameState currentState;
    private StartMenu startMenu;
    private Level level;
    private GameOver gameOver;

    public GameState() {}

    public GameState(State state) {
        loadState(state);
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

    public void update() {
        currentState.update();
    }
    public void draw(Graphics2D g) {
        currentState.draw(g);
    }

    public void keyPressed(KeyEvent key) {
        currentState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        currentState.keyReleased(key);
    }
}
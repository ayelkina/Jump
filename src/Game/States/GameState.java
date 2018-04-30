package Game.States;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameState implements KeyListener {

    public enum State {MENU, LEVEL, GAMEOVER}
    private GameState currentState;

    public GameState() {
    }

    public GameState(State state) {
        loadState(state);
    }

    public void loadState(State state) {

        if (state == State.MENU) {
            currentState = new StartMenu(this);
        }

        if (state == State.LEVEL) {
            currentState = new Level(this);
        }

        if (state == State.GAMEOVER) {
            currentState = new GameOver(this);
        }
    }

    public void update() {
        try {
            currentState.update();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g) {
        try {
            currentState.draw(g);
        } catch (Exception e) {
        }
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        currentState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        currentState.keyReleased(key);
    }
}
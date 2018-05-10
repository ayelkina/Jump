package Game.States;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState {

    public enum State {MENU, LEVEL, GAMEOVER}
    private GameState currentState;

    public GameState() {}

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
package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameState implements KeyListener, MouseListener{

    private GameState currentGameState;

    public enum State {MENU, LEVEL};
    public GameState(){}

    public GameState(State state){
        loadState(state);
    }

    public void loadState(State state){
        if (state == State.MENU){
           currentGameState  = new Menu();
        }

        if (state == State.LEVEL){
            currentGameState  = new Level();
        }

    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        currentGameState.keyPressed(key);
    }
    public void keyReleased(KeyEvent key) {
        currentGameState.keyReleased(key);
    }

    public void update() {
        currentGameState.update();
    }

    public void draw(Graphics2D graph) {
        currentGameState.draw(graph);
    }

    public void mouseClicked(MouseEvent mouse) {
        currentGameState.mouseClicked(mouse);
    }
    public void mousePressed(MouseEvent mouse) {
        currentGameState.mousePressed(mouse);
    }
    public void mouseReleased(MouseEvent mouse) {
        currentGameState.mouseReleased(mouse);
    }
    public void mouseEntered(MouseEvent mouse) {
        currentGameState.mouseEntered(mouse);
    }
    public void mouseExited(MouseEvent mouse) {
        currentGameState.mouseExited(mouse);
    }

}

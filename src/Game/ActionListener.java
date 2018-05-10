package Game;

import Game.States.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionListener extends Component implements KeyListener {

    private GameState gameState;

    public ActionListener(GameState gameState){

        this.gameState = gameState;
        addKeyListener(this);

    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        gameState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameState.keyReleased(key);
    }
}

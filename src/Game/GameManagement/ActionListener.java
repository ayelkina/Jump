package Game.GameManagement;

import Game.States.GameState;
import Game.View.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionListener extends View implements KeyListener {

    private GameState gameState;

    public ActionListener(GameState gs){
        this.gameState = gs;
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
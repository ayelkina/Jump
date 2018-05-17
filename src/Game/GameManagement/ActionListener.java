package Game.GameManagement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionListener implements KeyListener{

    private GameController gameController;

    public ActionListener(GameController gameController) {
        this.gameController = gameController;
    }

    public void keyTyped(KeyEvent key) { }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
         gameController.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameController.keyReleased(key);
    }
}

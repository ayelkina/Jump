package Game.GameManagement;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionListener extends JComponent implements KeyListener {

    private ViewController viewController;

    public ActionListener(ViewController vc){
        this.viewController = vc;
    }

    public void keyTyped(KeyEvent key) { }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        viewController.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        viewController.keyReleased(key);
    }
}
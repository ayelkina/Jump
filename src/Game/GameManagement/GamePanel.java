package Game.GameManagement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Image doubleBuffer;
    private ViewController viewController;

    public GamePanel(ViewController viewController) {
        this.viewController = viewController;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(this);
    }

    public void update() {
        paint();
    }

    private void paint() {
        Graphics2D g = (Graphics2D) getGraphics();
        Dimension size = getSize();
        if (size.height > 0 && size.width > 0)
            if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height) {
                doubleBuffer = createImage(size.width, size.height);
            }

        if (doubleBuffer != null) {
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
            viewController.draw(g2);

            g.drawImage(doubleBuffer, 0, 0, null);
            g2.dispose();

        } else {
            if (g != null) viewController.draw(g);
        }
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
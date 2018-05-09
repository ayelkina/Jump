package Game;

import Game.States.GameState;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final int maxPlayerHeight = 400;

    private Thread thread;
    private boolean running;
    private int frames = 60;
    private long targetTime = 1000 / frames;

    private long prevTime;
    private long deltaTime = 100000;

    private GameState gameState;
    private Image doubleBuffer;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        prevTime = System.nanoTime();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }

        if (running) return;

        running = true;
    }

    private void init() {
        gameState = new GameState(GameState.State.MENU);
    }

    public void run() {
        init();

        while (running) {
            update();
        }
    }

    private void update() {
        gameState.update();
        updateDraw();
    }

    private void draw(Graphics2D graph) {
        gameState.draw(graph);
        graph.dispose();
    }

    public void updateDraw(){
        Graphics2D g = (Graphics2D) getGraphics();
        Dimension size = getSize();
        if(doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height){
            doubleBuffer = createImage(size.width, size.height);
        }

        if (doubleBuffer != null){
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
              draw(g2);

            g.drawImage(doubleBuffer, 0 ,0, null);
            g2.dispose();
        }

        else {
               draw(g);
        }
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        gameState.keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameState.keyReleased(key);
    }

}

package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Thread thread;
    private boolean running;
    private int frames = 40;
    private long targetTime = 1000 / frames;

    private GameState gameState;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }

        if(running)
            return;

        running = true;
    }

    private void init() {
        gameState = new GameState(GameState.State.LEVEL);
    }

    public void run() {
        long timeStart;
        long delta;
        long timeWait;

        init();

        while(running) {
            timeStart = System.nanoTime();

            draw();
            update();

            delta = System.nanoTime() - timeStart;

            timeWait = targetTime - delta / 1000000;
            if(timeWait < 0) timeWait = 1;

            try {
                Thread.sleep(timeWait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gameState.update();
    }

    private void draw() {
        Graphics2D graph;
        graph = (Graphics2D) getGraphics();

        gameState.draw(graph);

        graph.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        gameState.keyPressed(key);
   }

    public void keyReleased(KeyEvent key) {
        gameState.keyReleased(key);
    }

}

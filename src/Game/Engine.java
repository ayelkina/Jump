package Game;

import Game.States.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Engine implements Runnable, KeyListener {

    private Thread thread;
    private boolean running;

    private long prevTime;
    private long deltaTime = 2000000;

    private GameState gameState;
    private ActionListener actionListener;

    private Image doubleBuffer;
    private GamePanel gamePanel;

    public Engine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        prevTime = System.nanoTime();


        createThread();
    }

    public void createThread(){
        if (thread == null) {
            gamePanel.addKeyListener(this);
            thread = new Thread(this);
            start();
        }

        if (running) return;
        running = true;
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void init() {
        gameState = new GameState(GameState.State.MENU);
        actionListener = new ActionListener(gameState);
    }

    public void run() {
        init();

        while (running) {
            long curTime = System.nanoTime();
            deltaTime = prevTime - curTime;

            if (curTime - prevTime >= deltaTime) {
                update();
                prevTime = curTime;
            }
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

    public void updateDraw() {
        Graphics2D g = (Graphics2D) gamePanel.getGraphics();
        Dimension size = gamePanel.getSize();
        if (doubleBuffer == null || doubleBuffer.getWidth(gamePanel) != size.width || doubleBuffer.getHeight(gamePanel) != size.height) {
            doubleBuffer = gamePanel.createImage(size.width, size.height);
        }

        if (doubleBuffer != null) {
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
            draw(g2);

            g.drawImage(doubleBuffer, 0, 0, null);
            g2.dispose();
        } else {
            draw(g);
        }
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

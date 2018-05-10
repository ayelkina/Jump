package Game.GameManagement;

import Game.States.GameState;

import java.awt.*;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private long prevTime;
    private long deltaTime = 2000000;

    private GameState gameState;
    private GamePanel gamePanel;
    private ActionListener actionListener;

    private Image doubleBuffer;

    public Engine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        prevTime = System.nanoTime();
        createThread();
    }

    public void createThread() {
        if (running) return;
        running = true;

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
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

    private void init() {
        gameState = new GameState(GameState.State.MENU);
        actionListener = new ActionListener(gameState);
        gamePanel.addKeyListener(actionListener);
    }

    private void update() {
        gameState.update();
        updateImage();
    }

    public void updateImage() {
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

    private void draw(Graphics2D graph) {
        gameState.draw(graph);
        graph.dispose();
    }

}

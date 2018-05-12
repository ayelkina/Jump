package Game.GameManagement;

import Game.States.GameState;

import java.awt.*;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private long prevTime;
    private long deltaTime = 20000000;

    private final static int    MAX_FPS = 50;
    private final static int    MAX_FRAME_SKIPS = 5;
    private final static int    FRAME_PERIOD = 1000 / MAX_FPS;

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

        long beginTime;     // the time when the cycle begun
        long timeDiff;      // the time it took for the cycle to execute
        int sleepTime;      // ms to sleep (<0 if we're behind)
        int framesSkipped;

        while (running) {
            long curTime = System.nanoTime();
//            update();

            beginTime = System.currentTimeMillis();
            framesSkipped = 0;

            update();
            timeDiff = System.currentTimeMillis() - beginTime;
            sleepTime = (int)(FRAME_PERIOD - timeDiff);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {}
            }

            while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                update();
                sleepTime += FRAME_PERIOD;
                framesSkipped++;
            }



           /* if (curTime - prevTime >= deltaTime) {
//                System.out.println(curTime - prevTime);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prevTime = curTime;
            }*/
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

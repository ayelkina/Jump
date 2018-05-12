package Game.GameManagement;

import Game.States.GameState;

import java.awt.*;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private long prevTime;
    private long deltaTime;// = 30;
    private long sleepTime;

    private long targetTime = 1000000000 / 60;

    private GameState gameState;
    private GamePanel gamePanel;
    private ActionListener actionListener;

    private Image doubleBuffer;

    public Engine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

//        prevTime = System.nanoTime();
        deltaTime = System.nanoTime();
        createThread();
    }

    private void createThread() {
        if (running) return;
        running = true;

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        init();

        long curTime;
        /*while (running) {

            prevTime = System.nanoTime();
            update();
            curTime = System.nanoTime();
            deltaTime = curTime - prevTime;
            while (deltaTime < targetTime) {
                sleepTime = (targetTime - deltaTime) / 1000000;
                try {
                   if(sleepTime!=0) System.out.println(sleepTime);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deltaTime = System.nanoTime() - prevTime;
            }
            }*/


        while (running) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
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

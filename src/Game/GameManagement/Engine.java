package Game.GameManagement;

import Game.States.GameState;

import java.awt.*;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private long prevTime;
    public static long deltaTime;// = 30;
    private long sleepTime;

    private int FPS = 60;
    private long targetTime = 10000000;//1000 / FPS;

    private long time;

    private static GameState gameState;
    private GamePanel gamePanel;
    private ActionListener actionListener;

    private Image doubleBuffer;

    public Engine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

//        time = 5;

//        prevTime = System.nanoTime();
//        deltaTime = 5000000;
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

/*    public static long getDelta(long delta){
        return
    }*/

    @Override
    public void run() {
        init();

       /* long curTime;
        while (running) {
            prevTime = System.nanoTime();
//            System.out.println(time);

            update(time);

            curTime = System.nanoTime();
            deltaTime = curTime - prevTime;
            time = deltaTime/1000000;

            while (deltaTime < targetTime) {
                sleepTime = (targetTime - deltaTime) / 1000000;
                try {
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
            time = 5;
            update(time);
        }

 /*
        long start;
        long delta;
        long wait;

        // game loop
        while (running) {

            start = System.nanoTime();

            update();

            delta = System.nanoTime() - start;

            wait = targetTime - delta / 1000000;
            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    private void init() {
        gameState = new GameState(GameState.State.MENU);
        actionListener = new ActionListener(gameState);
        gamePanel.addKeyListener(actionListener);
    }

    private void update(long time) {
        gameState.update(time);
        gamePanel.update();
    }

    public static GameState getState(){
        return gameState;
    }

    public static void draw(Graphics2D graph) {
        gameState.draw(graph);
        graph.dispose();
    }


}
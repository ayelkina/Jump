package Game.GameManagement;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private int FPS = 60;
    private long target = 1000000000 / FPS;

    private GameController gameController;
    private GamePanel gamePanel;

    public Engine(GamePanel gamePanel, GameController gameController) {
        this.gamePanel = gamePanel;
        this.gameController = gameController;
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
        long prevTime;
        long deltaTime = 0;
        long currentTime;

        init();

        while (running) {
            prevTime = System.nanoTime();

            update(deltaTime);

            currentTime = System.nanoTime();
            deltaTime = currentTime - prevTime;

            if (deltaTime < target) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        ActionListener actionListener = new ActionListener(gameController);
        gamePanel.addKeyListener(actionListener);
    }

    private void update(long time) {
        gameController.update(time);
        gamePanel.update();
    }
}
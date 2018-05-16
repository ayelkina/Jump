package Game.GameManagement;

public class Engine implements Runnable {

    private Thread thread;
    private boolean running;

    private long prevTime;
    private static long deltaTime;// = 30;
    private long sleepTime;

    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    private long target = 8000000;

    private long time;

    private StateController stateController;
    private ViewController viewController;
    private GamePanel gamePanel;

    public Engine(GamePanel gamePanel, StateController st) {
        this.gamePanel = gamePanel;
        stateController = st;



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

        /*long curTime;
        while (running) {
            prevTime = System.nanoTime();

            update(time);

            curTime = System.nanoTime();
            deltaTime = curTime - prevTime;
            time = deltaTime/1000000;

            while (deltaTime < target) {
                sleepTime = (target - deltaTime) / 1000000;
                try {
                    if(sleepTime!= 0) System.out.println(sleepTime);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deltaTime = System.nanoTime() - prevTime;
            }
        }*/

        //Klasa Timer do zrobienia pętli

        while (running) {
            time = 5;
            update(time);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*long start;
        long delta;
        long wait;

        while (running) {

            start = System.nanoTime();

            update(time);

            delta = System.nanoTime() - start;

            wait = targetTime - delta / 1000000;
            if (wait < 0) wait = 3;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    private void init() {
//        stateController = new StateController();
        /*ActionListener actionListener = new ActionListener(viewController);
        gamePanel.addKeyListener(actionListener);*/
    }

    private void update(long time) {
        stateController.update();
        gamePanel.update();
    }
}
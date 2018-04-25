package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel
        implements Runnable, KeyListener{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Thread thread;
    private boolean running;
    private int frames = 40;
    private long targetTime = 1000 / frames;

    private Player player;
    private Background background;
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

        background = new Background("/Pics/Sky.png");
        player = new Player();
        player.setPosition(WIDTH/2 - player.width/2, HEIGHT - player.height);
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
        player.update();
    }

    private void draw() {
        Graphics2D graph;
        graph = (Graphics2D) getGraphics();

        background.draw(graph);
        player.draw(graph);
      //  Level.draw(graph);

        graph.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
       if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(true);
       if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(true);
   }

    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(false);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(false);
    }

}


















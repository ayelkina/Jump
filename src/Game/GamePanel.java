package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel
        implements Runnable, KeyListener{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

//    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setFocusable(true);
//        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if(running)
            return;

        running = true;

        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {

        image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

//        gsm = new GameStateManager();

    }

    public void run() {

        init();


        long start;
        long delta;
        long wait;

        while(running) {

            start = System.nanoTime();

//            update();
//            draw();
            draw();

            delta = System.nanoTime() - start;

            wait = targetTime - delta / 1000000;
            if(wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
//        gsm.update();
    }

/*    private void draw() {
//        gsm.draw(g);
    }*/

    private void draw() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH , HEIGHT,null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
   public void keyPressed(KeyEvent key) {
//        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
//        gsm.keyReleased(key.getKeyCode());
    }

}


















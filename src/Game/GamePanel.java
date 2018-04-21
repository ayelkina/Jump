package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel
        implements Runnable, KeyListener{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage background;
    private Graphics2D graphics2D;
    private Player player;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
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

        background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/Pics/Sky.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        graphics2D = (Graphics2D) getGraphics();
//        graphics2D.drawImage(background, 0, 0, WIDTH , HEIGHT,null);

        player = new Player();
//        player.setPosition(WIDTH/2 - player.width/2, HEIGHT - player.height);
        player.setPosition(0, HEIGHT - player.height);
    }

    public void run() {

        init();

        long start;
        long delta;
        long wait;

        while(running) {

            draw(graphics2D);
            player.jump();

            start = System.nanoTime();
            delta = System.nanoTime() - start;

            wait = targetTime - delta / 1000000;
            if(wait < 0) wait = 1;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
    }

    private void draw(Graphics2D graph) {
        graph = (Graphics2D) getGraphics();

        graphics2D.drawImage(background, 0, 0, WIDTH , HEIGHT,null);

        player.draw(graph);

        graph.dispose();
    }

    public void keyTyped(KeyEvent key) {}

   public void keyPressed(KeyEvent key) {

       if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.moveRight();
       if (key.getKeyCode() == KeyEvent.VK_LEFT) player.moveLeft();
       if (key.getKeyCode() == KeyEvent.VK_UP) player.moveUp();
       if (key.getKeyCode() == KeyEvent.VK_DOWN) player.moveDown();
   }
    public void keyReleased(KeyEvent key) {
//        gsm.keyReleased(key.getKeyCode());
    }

}


















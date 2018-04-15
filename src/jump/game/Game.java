package jump.game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.nio.Buffer;

public class Game extends Canvas implements Runnable {

    public static void main(String[] args){
        new Game().start();
    }

    private static final long serialVersionUID = 1L;
    private JFrame frame;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    public boolean running = false;
    private Thread thread;
/*
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public static Sprite hero;
    private static int x = 0;
    private static int y = 0;*/

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage sprite = null;
    private BufferedImage player;

    public void init(){

        ImageLoader loader = new ImageLoader();

        try{
            sprite = loader.loadImage("Res/Pics/pengs4.png");
        }catch(IOException e){
        }

        Sprite ss = new Sprite(sprite);
        player = ss.grabImage(1,1,100,100);
    }


    public Game(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHight = screenSize.height;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Jump!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        //frame.setContentPane(new JLabel(new ImageIcon("Res/Pics/Sky.png")));

        //frame.setBounds(screenWidth/3, screenHight/10, WIDTH, HEIGHT);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void run() {
        init();
        long lastTime = System.currentTimeMillis();
        long delta;


        while(running){
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
        }
        render();
    }



    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this );
        g.drawImage(player, 100, 100, this);

        g.dispose();
        bs.show();
    }

    public void update(long delta){
       /* if (leftPressed == true) {
            --x;
        }
        if (rightPressed == true) {
            ++x;
        }*/
    }

    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;

        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

/*    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        }
    }*/

}



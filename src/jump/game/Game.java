package jump.game;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


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

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage sprite = null;
    private BufferedImage player;

    public void init(){
        System.out.println("init()");

        ImageLoader loader = new ImageLoader();

        try{
            sprite = loader.loadImage("Res/Pics/pengs4.png");
        }catch(IOException e){
        }

        Sprite ss = new Sprite(sprite);
        player = ss.grabImage(100,50,400,100);
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
       // frame.setContentPane(new JLabel(new ImageIcon("Res/Pics/Sky.png")));

       // frame.setBounds(screenWidth/3, screenHight/10, WIDTH, HEIGHT);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void run() {
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

        //g.drawImage(image, 0, 0, getWidth(), getHeight(), this );
        Image img = new ImageIcon("Res/Pics/pengs4.png").getImage();
        g.drawImage(img, 0, 0, null);
       // g.drawImage(player, 0, 0, null);
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
}



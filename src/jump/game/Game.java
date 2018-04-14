package jump.game;

import java.awt.*;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

    public static void main(String[] args){
        new Game().start();
    }

    private static final long serialVersionUID = 1L;
    private JFrame frame;

    public static final int WIDTH = 300;
    public static final int HEIGHT = 800;

    public boolean running = false;

    public Game(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHight = screenSize.height;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame("Jump!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon("Res/Pics/Sky.png")));

        frame.setBounds(screenWidth/3, screenHight/10, WIDTH, HEIGHT);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void run() {
        while(running){
            System.out.println("Boo!\n");
        }
    }

    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop(){
        running = false;
    }

}

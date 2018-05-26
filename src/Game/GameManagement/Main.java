package Game.GameManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Main extends Canvas {

    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        GameController gameController = new GameController();
        View view = new View(gameController);
        GamePanel gamePanel = new GamePanel(view);

        new Engine(gamePanel, gameController);

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        JFrame frame = new JFrame("Jump!");
        frame.setContentPane(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setBounds(screenWidth/3, screenHeight/10, WIDTH, HEIGHT);

//        Image image = Toolkit.getDefaultToolkit().getImage(
//                "/Res/Pics/icon2.png");
//        frame.setIconImage(image);

        ImageIcon icon = new ImageIcon("/Res/Pics/icon2.png");
        frame.setIconImage(icon.getImage());

//        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Res/Pics/icon.png"));

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
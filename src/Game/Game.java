package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas{

    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        JFrame frame = new JFrame("Jump!");
        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setBounds(screenWidth/3, screenHeight/10, WIDTH, HEIGHT);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}



package Game.GameManagement;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas{

    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        StateController stateController = new StateController();
        ViewController viewController = new ViewController(stateController);
        GamePanel gamePanel = new GamePanel(viewController);

        Engine engine = new Engine(gamePanel, stateController);

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        JFrame frame = new JFrame("Jump!");
        frame.setContentPane(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setBounds(screenWidth/3, screenHeight/10, WIDTH, HEIGHT);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
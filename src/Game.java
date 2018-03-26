
import java.awt.*;
import javax.swing.*;


public class Game {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            Window window = new Window();
            window.setTitle("Jump!");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}

class Window extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    public Window()
    {
        add(new BackGround());
        // pack();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHight = screenSize.height;

        setBounds(screenWidth/3, screenHight/10, WIDTH, HEIGHT);
    }
}

class BackGround extends JComponent{
    private Image image;

    public BackGround(){
        image = new ImageIcon("Pics/Sky.png").getImage();
    }

    public void paintComponent(Graphics g){
        if (image== null) return;
        g.drawImage(image, 0, 0, null);
    }

}
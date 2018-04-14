package jump.game;

import java.awt.*;
import javax.swing.*;

public class Game extends Canvas {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            Frame frame = new Frame();
            frame.setTitle("Jump!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}

class Frame extends JFrame
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    public Frame()
    {
        add(new BackGround());
       // add(new Sprite());
        pack();
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
        image = new ImageIcon("Res/Pics/Sky.png").getImage();
    }

    public void paintComponent(Graphics g){
        if (image== null) return;
        System.out.println("back?");
        g.drawImage(image, 0, 0, null);
    }

}

class Sprite extends JComponent{
    private Image sprite;

    public Sprite(){
        sprite = new ImageIcon("Res/Pics/Penguin3.png").getImage();
    }

    public void paintComponent(Graphics g){
        if (sprite== null) return;
        System.out.println("char?");
        g.drawImage(sprite, 200, 400, null);
    }

}
package Game;

import javax.swing.*;
import java.awt.*;

public class Menu extends GameState{

    private Background background;
    private Font font;

    private JButton startButton;
    private JButton exitButton;


    public Menu(){

        try{
            background = new Background("/Pics/Sky.png");
            font = new Font("Arial", Font.PLAIN, 12);
        }catch(Exception e){
            e.printStackTrace();
        }

        startButton = new JButton("Start");
        exitButton = new JButton("Exit");

    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        startButton.setVisible(true);
        exitButton.setVisible(true);

        graph.dispose();
    }


}

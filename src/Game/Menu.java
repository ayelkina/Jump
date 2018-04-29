package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;

public class Menu extends GameState{

    private Background background;
    private Font font;
    private Font titleFont;
    private int currentChoice = 0;

    private GameState gameState;

    private JLabel gameTitle;

   // private String gameTitle;
    private String[] options = {"Start", "Quit"};

    public Menu(GameState gameState){

        this.gameState = gameState;
   //     gameTitle = "Jump!";

        try{
            background = new Background("/Pics/sky1.png");

            gameTitle = new JLabel("Jump!");

            File font_file = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(48f);


            gameTitle.setFont(sizedFont);
            gameTitle.setSize(80,80);
            Game.frame.add(gameTitle);

            titleFont = new Font("Century Gothic", Font.PLAIN, 48);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        graph.setFont(titleFont);
       // graph.dr (gameTitle, GamePanel.WIDTH/2 - 75, GamePanel.HEIGHT/2 - 150);
        graph.setFont(font);

        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.GRAY);
            }
            graph.drawString(options[i], GamePanel.WIDTH/2, GamePanel.HEIGHT/2 + i * 50);
        }

        graph.dispose();
    }

    public void update(){}

    private void select() {
        if(currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER){
            select();
        }
        if(key.getKeyCode() == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }
    public void keyReleased(KeyEvent key) {}

}


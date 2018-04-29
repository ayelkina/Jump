package Game.States;

import Game.Background;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class Menu extends GameState{

    private Background background;
    private Font font;
    private int currentChoice;

    private GameState gameState;
    private String gameTitle;
    private String[] choice;

    public Menu(GameState gameState){

        this.gameState = gameState;
        gameTitle = "Jump!";
        choice = new String[] {"Start", " Quit"};

        currentChoice = 0;

        background = new Background("/Pics/sky1.png");

        try{
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawTitle(graph);
        drawOptions(graph);

        graph.dispose();
    }

    public void drawTitle(Graphics2D graph){
        graph.setColor(Color.BLACK);
        font = font.deriveFont(120f);
        graph.setFont(font);
        graph.drawString(gameTitle, 150, GamePanel.HEIGHT/2 - 150);
    }

    public void drawOptions(Graphics2D graph){
        font = font.deriveFont(48f);
        graph.setFont(font);

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }
            graph.drawString(choice[i], 250, GamePanel.HEIGHT/2 + i * 70);
        }
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
        if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE){
            select();
        }
        if(key.getKeyCode() == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = choice.length - 1;
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == choice.length) {
                currentChoice = 0;
            }
        }
    }
    public void keyReleased(KeyEvent key) {}

}

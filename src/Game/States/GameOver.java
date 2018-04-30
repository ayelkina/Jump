package Game.States;

import Game.Background;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class GameOver extends Menu{

    private String gameOver;
    private String nextTry;

    public GameOver(GameState gameState){
        this.gameState = gameState;

        gameOver = "Game Over";
        nextTry = "Try again?";
        choice = new String [] {"YES", "NO"};

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
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, nextTry, 70f, 350);
        drawOptions(graph);

        graph.dispose();
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
            graph.drawString(choice[i], 200  + i * 150, 450);
        }
    }

    private void select() {
        if(currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER  || key.getKeyCode() == KeyEvent.VK_SPACE){
            select();
        }
        if(key.getKeyCode() == KeyEvent.VK_RIGHT) {

            if(currentChoice != (choice.length-1)) {
                ++currentChoice;
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_LEFT) {
            if(currentChoice != 0) {
                --currentChoice;
            }
        }
    }
}
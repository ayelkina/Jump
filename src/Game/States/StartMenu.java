package Game.States;

import Game.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class StartMenu extends Menu {

    private String gameTitle;

    public StartMenu(GameState gameState) {
        this.gameState = gameState;

        gameTitle = "Jump!";
        choice = new String[]{"Start", "Quit"};

        currentChoice = 0;

        background = new Background("/Pics/sky1.png");

        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void select() {
        if (currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, 120f, 250);
        drawOptions(graph);

        graph.dispose();
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE){
            select();
        }
        if(key.getKeyCode() == KeyEvent.VK_UP) {
            if(currentChoice != 0) {
                --currentChoice;
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_DOWN) {
            if(currentChoice != choice.length-1) {
                ++currentChoice;
            }
        }
    }

}


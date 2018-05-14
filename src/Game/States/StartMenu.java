package Game.States;

import Game.GameManagement.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StartMenu extends Menu {

    private static String gameTitle;

    public StartMenu(GameState gameState) {
        super();
        this.gameState = gameState;

        gameTitle = "Jump!";
        choice = new String[]{"Start", "Quit"};
    }

    private void select() {
        if (currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public static String getGameTitle(){
        return gameTitle;
    }

    public static int getCurrentChoice(){
        return currentChoice;
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, 120f, 300);
        drawOptionsVertical(graph, 48f, GamePanel.HEIGHT / 2);

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

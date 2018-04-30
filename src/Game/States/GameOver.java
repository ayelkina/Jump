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
        super();
        this.gameState = gameState;

        gameOver = "Game Over";
        nextTry = "Try again?";
        choice = new String [] {"YES", "NO"};

    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, nextTry, 60f, 350);
        drawOptionsHorizontal(graph, 40,200, 450);

        graph.dispose();
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
package Game.View;

import Game.GameManagement.StateController;
import Game.States.GameOver;
import Game.States.Level;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ViewGameOver extends ViewMenu {
    private Background background;
    private String gameOver;
    private String score;
    private String nextTry;

    private StateController stateController;

    public ViewGameOver(StateController st) {
        stateController = st;

        background = new Background("/Pics/sky1.png");
        loadFont();

        gameOver = "Game Over";
        score = "Score: ";
        nextTry = "Try again?";
        choice = new String[]{"YES", "NO"};
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, score + Level.getCount(), 60, 350);
        drawText(graph, nextTry, 60f, 450);
        drawOptionsHorizontal(graph, 40, 200, 500);

        graph.dispose();
    }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE) {
            stateController.getGameOver().select(currentChoice);
        }

        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (currentChoice != (choice.length - 1)) {
                ++currentChoice;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentChoice != 0) {
                --currentChoice;
            }
        }
    }
}
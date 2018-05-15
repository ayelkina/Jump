package Game.View;

import Game.States.Level;

import java.awt.*;

public class ViewGameOver extends ViewMenu {

    private String gameOver;
    private String score;
    private String nextTry;

    public ViewGameOver() {
        gameOver = "Game Over";
        score = "Score: " + Level.getCount();
        nextTry = "Try again?";

        background = new Background("/Pics/sky1.png");
        loadFont();
    }

    public void drawGameOver(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, score, 60, 350);
        drawText(graph, nextTry, 60f, 450);
        drawOptionsHorizontal(graph, 40,200, 500);

        graph.dispose();
    }
}

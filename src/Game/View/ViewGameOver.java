package Game.View;

import Game.States.GameState;
import Game.States.Level;

import java.awt.*;

public class ViewGameOver extends ViewMenu {

    private String gameOver;
    private String score;
    private String nextTry;

    private Level level;

    public ViewGameOver() {
        level = GameState.getlevel();
        background = new Background("/Pics/sky1.png");
        loadFont();

        gameOver = "Game Over";
        score = "Score: ";
        nextTry = "Try again?";
    }

    public void drawGameOver(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, score + level.getCount(), 60, 350);
        drawText(graph, nextTry, 60f, 450);
        drawOptionsHorizontal(graph, 40,200, 500);

        graph.dispose();
    }
}

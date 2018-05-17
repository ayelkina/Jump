package Game.View.ViewStates;

import Game.States.GameOver;
import Game.States.Level;
import Game.View.Background;
import Game.View.ViewStates.ViewMenu;

import java.awt.*;

public class ViewGameOver extends ViewMenu {

    private Background background;
    private String gameOver;
    private String score;
    private String nextTry;

    public ViewGameOver(GameOver st) {
        super(st);

        background = new Background("/Pics/sky1.png");
        loadFont();

        gameOver = "Game Over";
        score = "Score: ";
        nextTry = "Try again?";

    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, 100f, 250);
        drawText(graph, score + Level.getCount(), 60, 350);
        drawText(graph, nextTry, 60f, 450);
        drawOptionsHorizontal(graph, 40, 200, 500);

        graph.dispose();
    }
}
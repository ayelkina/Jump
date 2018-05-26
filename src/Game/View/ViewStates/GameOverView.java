package Game.View.ViewStates;

import Game.GameManagement.GamePanel;
import Game.States.GameOver;
import Game.States.Level;
import Game.View.Background;

import java.awt.*;
import java.lang.reflect.GenericArrayType;

public class GameOverView extends MenuView {

    private Background background;
    private String gameOver;
    private String score;
    private String nextTry;

    public GameOverView(GameOver st) {
        super(st);

        background = new Background("/Res/Pics/sky1.png");
        loadFont();

        gameOver = "Game Over";
        score = "Score: ";
        nextTry = "Try again?";

    }

    @Override
    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameOver, GamePanel.HEIGHT/8, GamePanel.HEIGHT/3);
        drawText(graph, score + Level.getCount(), GamePanel.HEIGHT/12, GamePanel.HEIGHT/3 + GamePanel.HEIGHT/8);
        drawText(graph, nextTry, GamePanel.HEIGHT/14, GamePanel.HEIGHT/3 + 2*GamePanel.HEIGHT/8);
        drawOptionsHorizontal(graph, GamePanel.HEIGHT/20, GamePanel.WIDTH/3, GamePanel.HEIGHT/3 + 2*GamePanel.HEIGHT/8 + GamePanel.HEIGHT/16);

        graph.dispose();
    }
}
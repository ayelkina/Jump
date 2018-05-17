package Game.View.ViewStates;

import Game.GameManagement.GamePanel;
import Game.States.StartMenu;
import Game.States.State;
import Game.View.Background;

import java.awt.*;

public class ViewStartMenu extends ViewMenu {

    private String gameTitle;
    private Background background;

    public ViewStartMenu(StartMenu st) {
        super(st);

        background = new Background("/Pics/sky1.png");
        loadFont();
        gameTitle = "Jump!";
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, 120f, 300);
        drawOptionsVertical(graph, 48f, GamePanel.HEIGHT / 2);

        graph.dispose();
    }
}

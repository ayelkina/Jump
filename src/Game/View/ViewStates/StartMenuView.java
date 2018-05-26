package Game.View.ViewStates;

import Game.GameManagement.GamePanel;
import Game.States.StartMenu;
import Game.View.Background;

import java.awt.*;

public class StartMenuView extends MenuView {

    private String gameTitle;
    private Background background;

    public StartMenuView(StartMenu st) {
        super(st);

        background = new Background("/Res/Pics/sky1.png");
        loadFont();
        gameTitle = "Jump!";
    }

    @Override
    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, GamePanel.HEIGHT/6, GamePanel.HEIGHT /3);
        drawOptionsVertical(graph, GamePanel.HEIGHT/15, GamePanel.HEIGHT / 2);

        graph.dispose();
    }
}

package Game.View;

import Game.GameManagement.GamePanel;

import java.awt.*;

public class ViewStartMenu extends ViewMenu {

    private String gameTitle;

    public ViewStartMenu() {
        background = new Background("/Pics/sky1.png");
        loadFont();
        gameTitle = "Jump!";
    }

    public void drawStartMenu(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, 120f, 300);
        drawOptionsVertical(graph, 48f, GamePanel.HEIGHT / 2);

        graph.dispose();
    }
}

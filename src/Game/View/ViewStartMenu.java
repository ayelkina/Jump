package Game.View;

import Game.GameManagement.GamePanel;
import Game.GameManagement.StateController;
import Game.States.StartMenu;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ViewStartMenu extends ViewMenu {

    private String gameTitle;
    private Background background;
    private StateController stateController;

    public ViewStartMenu(StateController st) {
        stateController = st;

        background = new Background("/Pics/sky1.png");
        loadFont();
        gameTitle = "Jump!";

        choice = new String[] {"Start", "Quit"};
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);
        drawText(graph, gameTitle, 120f, 300);
        drawOptionsVertical(graph, 48f, GamePanel.HEIGHT / 2);

        graph.dispose();
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE){
            stateController.getStartMenu().select(currentChoice);
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

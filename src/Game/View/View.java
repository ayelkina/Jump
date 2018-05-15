package Game.View;

import Game.States.GameState;
import Game.GameManagement.GamePanel;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    protected  Background background;
    private ViewStartMenu viewMenu;
    private ViewLevel viewLevel;
    private ViewGameOver viewGameOver;
    protected   Font font;

    public View() { }

    public  void draw(Graphics2D graph) {
        drawGameState(graph);
        graph.dispose();
    }

    private   void drawGameState(Graphics2D graph) {
        if(GameState.getCurState() == GameState.getstartMenu()) {
          if(viewMenu == null) viewMenu = new ViewStartMenu();
                    viewMenu.drawStartMenu(graph);
        }

        if(GameState.getCurState() == GameState.getlevel()) {
            if (viewLevel == null) viewLevel = new ViewLevel();
            viewLevel.drawLevel(graph);
        }

        if(GameState.getCurState() == GameState.getgameOver()){
            if (viewGameOver == null) viewGameOver = new ViewGameOver();
            viewGameOver.drawGameOver(graph);
        }
    }

    public  void drawBackground(Graphics2D graph) {
        graph.drawImage(background.getImage(), 0,0, GamePanel.WIDTH, GamePanel.HEIGHT,null);
    }
}

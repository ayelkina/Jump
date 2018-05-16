package Game.View;

import Game.States.GameState;

import javax.swing.*;
import java.awt.*;

public class ViewGame extends View {

    protected Background background;
    protected Font font;

    private ViewStartMenu viewMenu;
    private ViewLevel viewLevel;
    private ViewGameOver viewGameOver;

    public ViewGame() {}

    public void draw(Graphics2D graph) {
        drawGameState(graph);
        graph.dispose();
    }

    private void drawGameState(Graphics2D graph) {
        if(GameState.getCurState() == GameState.STARTMENU) {
            if(viewMenu == null) viewMenu = new ViewStartMenu();
            viewMenu.drawStartMenu(graph);
        }

        if(GameState.getCurState() == GameState.LEVEL) {
            if (viewLevel == null) viewLevel = new ViewLevel();
            viewLevel.drawLevel(graph);
        }

        if(GameState.getCurState() == GameState.GAMEOVER){
            if (viewGameOver == null) viewGameOver = new ViewGameOver();
            viewGameOver.drawGameOver(graph);
        }
    }


    /*private void drawGameState(Graphics2D graph) {
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
    }*/
}

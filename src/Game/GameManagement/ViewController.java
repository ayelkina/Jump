package Game.GameManagement;

import Game.States.State;
import Game.View.ViewGameOver;
import Game.View.ViewStartMenu;
import Game.View.ViewLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ViewController extends JComponent {

    private ViewStartMenu startMenu;
    private ViewLevel viewLevel;
    private ViewGameOver gameOver;

    private StateController stateController;

    public ViewController(StateController st) {
        stateController = st;
    }

    public void draw(Graphics2D graph) {
        drawGameState(graph);
        graph.dispose();
    }

    private void drawGameState(Graphics2D graph) {
        if(stateController.getCurState() == StateController.STARTMENU) {
            if (startMenu == null) startMenu = new ViewStartMenu(stateController);
            startMenu.draw(graph);
        }

        if(stateController.getCurState() == StateController.LEVEL) {
            if (viewLevel == null) viewLevel = new ViewLevel(stateController);
            viewLevel.draw(graph);
        }

        if(stateController.getCurState() == StateController.GAMEOVER){
            if (gameOver == null) gameOver = new ViewGameOver(stateController);
            gameOver.draw(graph);
        }
    }

    public void keyPressed(KeyEvent key) {
        if(stateController.getCurState() == StateController.STARTMENU) {
            startMenu.keyPressed(key);
        }

        if(stateController.getCurState() == StateController.LEVEL) {
            if(viewLevel != null)
            viewLevel.keyPressed(key);
        }

        if(stateController.getCurState() == StateController.GAMEOVER){
            gameOver.keyPressed(key);
        }
    }

    public void keyReleased(KeyEvent key) {
        if(stateController.getCurState() == StateController.STARTMENU) {
            startMenu.keyReleased(key);
        }

        if(stateController.getCurState() == StateController.LEVEL) {
            viewLevel.keyReleased(key);
        }

        if(stateController.getCurState() == StateController.GAMEOVER){
            gameOver.keyReleased(key);
        }
    }
}

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

    private static State[] gameStates;
    private static int current;

    public ViewController() {
        gameStates = new State[3];
    }

    public void draw(Graphics2D graph) {
        drawGameState(graph);
        graph.dispose();
    }

    private void drawGameState(Graphics2D graph) {
        if(StateController.getCurState() == StateController.STARTMENU) {
            if (startMenu == null) startMenu = new ViewStartMenu();
            startMenu.draw(graph);
        }

        if(StateController.getCurState() == StateController.LEVEL) {
            if (viewLevel == null) viewLevel = new ViewLevel();
            viewLevel.drawLevel(graph);
        }

        if(StateController.getCurState() == StateController.GAMEOVER){
            if (gameOver == null) gameOver = new ViewGameOver();
            gameOver.draw(graph);
        }
        /*current = StateController.getCurState();
        gameStates[current].draw(graph);*/
    }

    public void keyPressed(KeyEvent key) {
//        gameStates[current].keyPressed(key);

        if(StateController.getCurState() == StateController.STARTMENU) {
            startMenu.keyPressed(key);
        }

        if(StateController.getCurState() == StateController.LEVEL) {
            if(viewLevel != null)
            viewLevel.keyPressed(key);
        }

        if(StateController.getCurState() == StateController.GAMEOVER){
            gameOver.keyPressed(key);
        }
    }

    public void keyReleased(KeyEvent key) {
//        gameStates[current].keyReleased(key);

        if(StateController.getCurState() == StateController.STARTMENU) {
            startMenu.keyReleased(key);
        }

        if(StateController.getCurState() == StateController.LEVEL) {
            viewLevel.keyReleased(key);
        }

        if(StateController.getCurState() == StateController.GAMEOVER){
            gameOver.keyReleased(key);
        }
    }
}

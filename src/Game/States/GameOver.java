package Game.States;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOver extends Menu{

    public GameOver(GameState gameState){
        super();
        this.gameState = gameState;

        choice = new String [] {"YES", "NO"};

    }

    private void select() {
        if(currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER  || key.getKeyCode() == KeyEvent.VK_SPACE){
            select();
        }
        if(key.getKeyCode() == KeyEvent.VK_RIGHT) {

            if(currentChoice != (choice.length-1)) {
                ++currentChoice;
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_LEFT) {
            if(currentChoice != 0) {
                --currentChoice;
            }
        }
    }
}
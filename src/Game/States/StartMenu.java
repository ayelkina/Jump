package Game.States;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StartMenu extends Menu {

    public StartMenu(GameState gameState) {
        super();
        this.gameState = gameState;

        choice = new String[]{"Start", "Quit"};
    }

    private void select() {
        if (currentChoice == 0) {
            gameState.loadState(GameState.State.LEVEL);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE){
            select();
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

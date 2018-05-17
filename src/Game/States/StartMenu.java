package Game.States;

import Game.GameManagement.GameController;

import java.awt.event.KeyEvent;

public class StartMenu extends Menu {

    public StartMenu(GameController st) {
        this.gameController = st;
        choice = new String []{"Start", "Quit"};
        currentChoice = 0;
    }

    public void select () {
        if (currentChoice == 0) {
            gameController.loadState(GameController.LEVEL);

        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public int getCurrentChoice() {
        return currentChoice;
    }

    public String[] getChoice() {
        return choice;
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

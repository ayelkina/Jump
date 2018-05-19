package Game.States;

import Game.GameManagement.GameController;

import java.awt.event.KeyEvent;

public class GameOver extends Menu {

    private boolean changeState;

    public GameOver() {
        choice = new String[]{"YES", "NO"};
    }

    private void select() {
        if(currentChoice == 0) {
            changeState = true;
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }

    @Override
    public boolean changeState() {
        return changeState;
    }

    @Override
    public void loadNew() {
        currentChoice = 0;
        changeState = false;
    }

    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE) {
            select();
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (currentChoice != (choice.length - 1)) {
                ++currentChoice;
            }
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentChoice != 0) {
                --currentChoice;
            }
        }
    }
}


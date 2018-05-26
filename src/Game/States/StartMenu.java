package Game.States;

import java.awt.event.KeyEvent;

public class StartMenu extends Menu {

    private boolean changeState;

    public StartMenu() {
        choice = new String []{"Start", "Quit"};
        currentChoice = 0;
    }

    private void select () {
        if (currentChoice == 0) {
            changeState = true;

        }
        if (currentChoice == 1) {
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

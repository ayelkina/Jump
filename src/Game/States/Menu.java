package Game.States;

import Game.GameManagement.GameController;

public class Menu extends State {

    protected GameController gameController;
    protected int currentChoice;
    protected String[] choice;

    public Menu() {}

    public int getCurrentChoice() {
        return currentChoice;
    }

    public String[] getChoice() {
        return choice;
    }
}

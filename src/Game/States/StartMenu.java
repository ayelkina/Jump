package Game.States;

import Game.GameManagement.StateController;

public class StartMenu extends State {

    public StartMenu() { }

    public static void select (int currentChoice) {
        if (currentChoice == 0) {
            StateController.loadState(StateController.LEVEL);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }
}

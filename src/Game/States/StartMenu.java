package Game.States;

import Game.GameManagement.StateController;

public class StartMenu extends State {

    private StateController stateController;

    public StartMenu(StateController st) {
        stateController = st;
    }

    public void select (int currentChoice) {
        if (currentChoice == 0) {
            stateController.loadState(StateController.LEVEL);

        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }
}

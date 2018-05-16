package Game.States;

import Game.GameManagement.StateController;

public class GameOver extends State {

    private StateController stateController;

    public GameOver(StateController st) {
        stateController = st;
    }

    public void select(int currentChoice) {
        if(currentChoice == 0) {
            stateController.reloadState(StateController.LEVEL);
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }
}


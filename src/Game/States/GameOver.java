package Game.States;

import Game.GameManagement.StateController;

public class GameOver extends State {

    public GameOver() {
    }

    public static void select(int currentChoice) {
        if(currentChoice == 0) {
            StateController.reloadState(StateController.LEVEL);
        }
        if(currentChoice == 1) {
            System.exit(0);
        }
    }
}


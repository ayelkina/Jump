package Game.GameManagement;

import Game.States.GameOver;
import Game.States.Level;
import Game.States.StartMenu;
import Game.States.State;
import Game.View.ViewStates.GameOverView;
import Game.View.ViewStates.LevelView;
import Game.View.ViewStates.StartMenuView;
import Game.View.ViewStates.StateView;

import java.awt.event.KeyEvent;

public class GameController {

    private  State[] gameStates;
    private StateView[] stateViews;
    private  int current;

    private static final int NUMBERSTATES = 3;
    private static final int STARTMENU = 0;
    private static final int LEVEL = 1;
    private static final int GAMEOVER = 2;

    public GameController() {
        gameStates = new State[NUMBERSTATES];
        stateViews = new StateView[NUMBERSTATES];

        current = STARTMENU;
        loadState(current);
    }

    private void loadState(int state) {
        if (state == STARTMENU) {
            StartMenu startMenu = new StartMenu();
            gameStates[state] = startMenu;
            stateViews[state] = new StartMenuView(startMenu);

            current = STARTMENU;
        }

        if (state == LEVEL) {
            Level level = new Level();
            stateViews[state] = new LevelView(level);
            gameStates[state] = level;

            current = LEVEL;
        }

        if (state == GAMEOVER) {
            GameOver gameOver = new GameOver();
            stateViews[state]  = new GameOverView(gameOver);
            gameStates[state] = gameOver;

            current = GAMEOVER;
        }
    }

    private void reloadState(int state){
        current = state;
        gameStates[current].loadNew();
    }

    public void update(long time) {
        if(gameStates[current] != null)
            gameStates[current].update(time);

        if(gameStates[current].changeState()) {
            if (current == STARTMENU)
                loadState(LEVEL);

            else if (current == GAMEOVER)
                reloadState(LEVEL);

            else if (current == LEVEL) {
                if (gameStates[GAMEOVER] == null) loadState(GAMEOVER);
                else reloadState(GAMEOVER);
            }
        }
    }

    public StateView getViewState(){
        return stateViews[current];
    }

    public void keyPressed(KeyEvent key) {
        gameStates[current].keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameStates[current].keyReleased(key);
    }
}
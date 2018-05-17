package Game.GameManagement;

import Game.States.GameOver;
import Game.States.Level;
import Game.States.StartMenu;
import Game.States.State;
import Game.View.ViewStates.ViewGameOver;
import Game.View.ViewStates.ViewLevel;
import Game.View.ViewStates.ViewStartMenu;
import Game.View.ViewStates.ViewState;

import java.awt.event.KeyEvent;

public class GameController {

    private  State[] gameStates;
    private ViewState[] viewStates;
    private  int current;

    public static final int NUMBERSTATES = 3;
    public static final int STARTMENU = 0;
    public static final int LEVEL = 1;
    public static final int GAMEOVER = 2;

    public GameController() {
        gameStates = new State[NUMBERSTATES];
        viewStates = new ViewState[NUMBERSTATES];

        current = STARTMENU;
        loadState(current);
    }

    public void loadState(int state) {
        if (state == STARTMENU) {
            StartMenu startMenu = new StartMenu(this);
            gameStates[state] = startMenu;
            viewStates[state] = new ViewStartMenu(startMenu);

            current = STARTMENU;
        }

        if (state == LEVEL) {
            Level level = new Level(this);
            viewStates[state] = new ViewLevel(level);
            gameStates[state] = level;

            current = LEVEL;
        }

        if (state == GAMEOVER) {
            GameOver gameOver = new GameOver(this);
            viewStates[state]  = new ViewGameOver(gameOver);
            gameStates[state] = gameOver;

            current = GAMEOVER;
        }
    }

    public  void reloadState(int state){
        current = state;
        gameStates[current].loadNew();
    }

    public void update() {
        if(gameStates[current] != null) gameStates[current].update();
    }

    public ViewState getViewState(){
        return viewStates[current];
    }

    public void keyPressed(KeyEvent key) {
        gameStates[current].keyPressed(key);
    }

    public void keyReleased(KeyEvent key) {
        gameStates[current].keyReleased(key);
    }
}
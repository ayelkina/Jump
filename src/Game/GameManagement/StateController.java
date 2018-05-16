package Game.GameManagement;

import Game.States.GameOver;
import Game.States.Level;
import Game.States.StartMenu;
import Game.States.State;

public class StateController {

    private  State[] gameStates;
    private  int current;

    public static final int STARTMENU = 0;
    public static final int LEVEL = 1;
    public static final int GAMEOVER = 2;


    private  StartMenu startMenu;
    private  Level level;
    private  GameOver gameOver;

    public StateController() {
        gameStates = new State[3];

        current = STARTMENU;
        loadState(current);
    }

    public void loadState(int state) {
        if (state == STARTMENU) {
            startMenu = new StartMenu(this);
            gameStates[state] = startMenu;
            current = STARTMENU;
        }

        if (state == LEVEL) {
            level = new Level(this);
            gameStates[state] = level;
            current = LEVEL;

        }

        if (state == GAMEOVER) {
            gameOver = new GameOver(this);
            gameStates[state] = gameOver;
            current = GAMEOVER;
        }
    }

    public  int getCurState(){
        return current;
    }

    public  void reloadState(int state){
        current = state;
        gameStates[current].loadNew();
    }

    public void update() {
        if(gameStates[current] != null) gameStates[current].update();
    }


    public StartMenu getStartMenu() {
        return startMenu;
    }

    public  Level getlevel(){
        return level;
    }

    public GameOver getGameOver() {
        return gameOver;
    }
}
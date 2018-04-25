package Game;

public class GameState {

    private GameState gameState;

    public enum State {MENU, LEVEL};
    public GameState(){}

    public GameState(State state){
        loadState(state);
    }

    public void loadState(State state){
        if (state == State.MENU){
           gameState  = new Menu();
        }

        if (state == State.LEVEL){
            gameState  = new Level();
        }

    }
}

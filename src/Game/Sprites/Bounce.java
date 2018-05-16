package Game.Sprites;

import Game.GameManagement.Constants;

public class Bounce extends Sprite{

    public enum State {STAY, DOWN, UP};

    private boolean stay;
    private boolean down;
    private boolean up;

    public Bounce(){
        down = false;

        width = Constants.BounceWidth;
        height = Constants.BounceDownHeight;
    }

    public State getState(){
        State state;

        state = State.valueOf("DOWN");
        if(up) { state = State.valueOf("UP"); }
        if(stay) { state = State.valueOf("DOWN"); }

        return state;
    }

    public boolean getDown() {
        return down;
    }
    public boolean getUp() {
        return up;
    }
    public boolean getStay() {
        return stay;
    }

    public void setDown(boolean b) {
        down = b;
        up = stay = !b;
    }

    public void setStay(boolean b) {
        stay = b;
        height = Constants.BounceDownHeight;
        y += height - Constants.BounceDownHeight;
        up = down = !b;
    }

    public void setUp (boolean b) {
        up = b;
        height = Constants.BounceUpHeight;
        y -= height - Constants.BounceDownHeight;
        down = stay = !b;
    }
}
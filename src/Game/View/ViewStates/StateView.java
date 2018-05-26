package Game.View.ViewStates;

import Game.States.State;

import java.awt.*;

public abstract class StateView {

    private State state;

    protected StateView(State state) {
        this.state = state;
    }

    public abstract void draw(Graphics2D graph);
}

package Game.View.ViewStates;

import Game.States.State;
;
import java.awt.*;

public abstract class ViewState {

    private State state;

    protected ViewState(State state) {
        this.state = state;
    }

    public abstract void draw(Graphics2D graph);
}

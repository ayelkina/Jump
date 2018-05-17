package Game.View.ViewStates;

import Game.States.State;

import javax.swing.text.View;
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class ViewState {

    private State state;

    public ViewState(State state) {
        this.state = state;
    }

    public abstract void draw(Graphics2D graph);
}

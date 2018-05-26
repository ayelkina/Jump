package Game.States;

import java.awt.event.KeyEvent;

public abstract class State {

    public boolean changeState(){return false;}

    public void update(long time) {}
    public void loadNew() {}

    public void keyPressed(KeyEvent key) { }
    public void keyReleased(KeyEvent key) {}
}

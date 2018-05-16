package Game.States;

import java.awt.event.KeyEvent;

public abstract class State {

    public void update(long time) { }
    public void reload() {}
    public void keyPressed(KeyEvent key) { }
    public void keyReleased(KeyEvent key) { }
}

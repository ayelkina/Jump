package Game.States;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends GameState {

    protected static int currentChoice;

    protected GameState gameState;
    protected static String[] choice;

    public Menu() {
        currentChoice = 0;
    }
    public static String[] getChoice(){
        return choice;
    }

    public static int getCurrentChoice(){
        return currentChoice;
    }

    public void update(){}
    public void draw(Graphics2D graph) {}

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {}
    public void keyReleased(KeyEvent key) {}

}
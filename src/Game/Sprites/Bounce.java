package Game.Sprites;

import Game.Sprites.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bounce extends Sprite{

    public enum State {STAY, DOWN, UP};

    private static final int downHeight = 25;
    private static final int upHeight = 65;

    private boolean stay;
    private boolean down;
    private boolean up;

    public static int position;

    public Bounce(){
        down = false;
        position = 1;

        width = 70;
        height = downHeight;
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
        position = 1;
        down = b;
        up = stay = !b;
    }

    public void setStay(boolean b) {
        stay = b;
        position = 1;
        height = downHeight;
        y += height - downHeight;
        up = down = !b;
    }

    public void setUp (boolean b) {
        up = b;
        position = 2;
        height = upHeight;
        y -= height - downHeight;
        down = stay = !b;
    }
}
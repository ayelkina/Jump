package Game.Tools;

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

    public Bounce(){
        down = false;

        width = 70;
        height = downHeight;
        loadSprite("/Pics/bounce.png");
    }

    public BufferedImage loadImage(State state){
        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col*width, row*height, width, height);
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(loadImage(getState()),  (int)x,  (int)y, width, height,null);
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

    public void setDown(boolean b) {
        down = b;
        up = stay = !b;
    }

    public void setStay(boolean b) {
        stay = b;
        height = downHeight;
        y += height - downHeight;
        up = down = !b;
    }

    public void setUp (boolean b) {
        up = b;
        height = upHeight;
        y -= height - downHeight;
        down = stay = !b;
    }
}
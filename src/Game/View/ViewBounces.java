package Game.View;

import Game.States.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewBounces extends ViewSprite {

    public enum State {STAY, DOWN, UP};

    public ViewBounces(){
        width = 70;
        height = 25;

        loadSprite("/Pics/bounce.png");
    }

    public State getState(int i){
        State state;

        state = State.valueOf("DOWN");
        if(Level.getBounces().get(i).getUp()) { state = State.valueOf("UP"); }
        if(Level.getBounces().get(i).getStay()) { state = State.valueOf("DOWN"); }

        return state;
    }

    public BufferedImage loadImage(State state){
        int row = 0;
        int col = state.ordinal();

        if (state == State.UP)
            height = 65;
        else height = 25;

        return image.getSubimage(col*width, row*height, width, height);
    }

    public void draw(Graphics2D graph, int i) {
        x = Level.getBounceX(i);
        y = Level.getBounceY(i);
        graph.drawImage(loadImage(getState(i)),  (int)x,  (int)y, width, height,null);
    }
}

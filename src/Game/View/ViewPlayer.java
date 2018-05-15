package Game.View;

import Game.States.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UP, DOWN, FALL};

    public ViewPlayer(){
        loadSprite("/Pics/peng.png");

        width = 70;
        height = 63;

        x = Level.getPlayer().getx();
        y = Level.getPlayer().gety();
    }

    public static State getState(){
        State state;

        state = State.valueOf("UP");
        if(Level.getPlayer().getUp()) { state = State.valueOf("DOWN"); }
        if(Level.getPlayer().getFall()) { state = State.valueOf("FALL"); }

        return state;
    }

    private  BufferedImage loadImage(State state){
        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col*width, row*height, width, height);
    }

    public  void drawPlayer(Graphics2D graph) {
        x = Level.getPlayer().getx();
        y = Level.getPlayer().gety();
        graph.drawImage(loadImage(getState()),  (int)x, (int) y, width, height,null);
    }
}

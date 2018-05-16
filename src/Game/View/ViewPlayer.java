package Game.View;

import Game.GameManagement.StateController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UP, DOWN, FALL};

    public ViewPlayer(){
        loadSprite("/Pics/peng.png");

        level = StateController.getlevel();

        width = 70;
        height = 63;
    }

    private State getState(){
        State state;

        state = State.valueOf("DOWN");
        if(level.getPlayer().getUp()) { state = State.valueOf("UP"); }
        if(level.getPlayer().getFall()) { state = State.valueOf("FALL"); }

        return state;
    }

    private  BufferedImage loadImage(State state){
        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col*width, row*height, width, height);
    }

    public  void drawPlayer(Graphics2D graph) {
        x = level.getPlayer().getx();
        y = level.getPlayer().gety();
        graph.drawImage(loadImage(getState()),  (int)x, (int) y, width, height,null);
    }
}

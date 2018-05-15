package Game.View;

import Game.States.GameState;
import Game.States.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UP, DOWN, FALL};
    private Level level;

    public ViewPlayer(){
        loadSprite("/Pics/peng.png");

        level = GameState.getlevel();

        width = 70;
        height = 63;

        x = level.getPlayer().getx();
        y = level.getPlayer().gety();
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

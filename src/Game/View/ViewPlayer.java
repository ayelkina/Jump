package Game.View;

import Game.GameManagement.Constants;
import Game.GameManagement.StateController;
import Game.States.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UP, DOWN, FALL};

    public ViewPlayer(StateController st) {
        stateController = st;

        loadSprite("/Pics/peng.png");

        width = Constants.PlayerWidth;
        height = Constants.PlayerHeight;
    }

    private State getState(){
        State state;

        Level level = stateController.getlevel();

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

    public void drawPlayer(Graphics2D graph) {
        Level level = stateController.getlevel();


        x = level.getPlayer().getx();
        y = level.getPlayer().gety();
;
        graph.drawImage(loadImage(getState()),  (int)x, (int) y, width, height,null);
    }
}

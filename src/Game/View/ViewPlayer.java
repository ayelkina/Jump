package Game.View;

import Game.GameManagement.Constants;
import Game.GameManagement.GameController;
import Game.Sprites.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UPRIGHT, DOWNRIGHT, FALL, UPLEFT, DOWNLEFT}
    private Player player;

    public ViewPlayer(Player player) {
        this.player = player;

        loadSprite("/Pics/peng.png");

        width = Constants.PlayerWidth;
        height = Constants.PlayerHeight;
    }

    private State getState() {
        State state;

        state = State.valueOf("DOWNRIGHT");
        if (player.getDown() && player.getAnimationLeft()) state = State.valueOf("DOWNLEFT");
        if (player.getUp() && !player.getAnimationLeft()) state = State.valueOf("UPRIGHT");
        if (player.getUp() && player.getAnimationLeft()) state = State.valueOf("UPLEFT");
        if (player.getFall()) state = State.valueOf("FALL");

        return state;
    }

    private BufferedImage loadImage(State state) {
        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col * width, row * height, width, height);
    }

    public void drawPlayer(Graphics2D graph) {
        x = player.getx();
        y = player.gety();
        graph.drawImage(loadImage(getState()), (int) x, (int) y, width, height, null);
    }
}

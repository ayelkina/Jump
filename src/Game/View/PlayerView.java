package Game.View;

import Game.GameManagement.Constants;
import Game.Sprites.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView extends SpriteView {

    public enum State {UPRIGHT, DOWNRIGHT, FALL, UPLEFT, DOWNLEFT}
    private Player player;

    public PlayerView(Player player) {
        this.player = player;

        width = Constants.PlayerWidth;
        height = Constants.PlayerHeight;

        imageHeight = Constants.PlayerImageHeight;
        imageWidth = Constants.PlayerImageWidth;

        loadSprite("/Res/Pics/peng.png");
    }

    private State getState() {
        State state;

        state = State.DOWNRIGHT;
        if (player.getDown() && player.getAnimationLeft()) state = State.DOWNLEFT;
        if (player.getUp() && !player.getAnimationLeft()) state = State.UPRIGHT;
        if (player.getUp() && player.getAnimationLeft()) state = State.UPLEFT;
        if (player.getFall()) state = State.FALL;

        return state;
    }

    private BufferedImage loadImage(State state) {
        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col * imageWidth, row * imageHeight, imageWidth, imageHeight);
    }

    public void drawPlayer(Graphics2D graph) {
        x = player.getx();
        y = player.gety();
        graph.drawImage(loadImage(getState()), (int) x, (int) y, width, height, null);
    }
}

package Game.GameManagement;

import Game.States.GameState;
import Game.States.Level;
import Game.States.Menu;
import Game.States.StartMenu;
import Game.Tools.Background;
import Game.GameManagement.GamePanel;
import Game.Sprites.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPlayer extends ViewSprite {

    public enum State {UP, DOWN, FALL};

    public ViewPlayer(){
        loadSprite("/Pics/peng.png");

        width = 70;
        height = 63;

        setSpritePosition(GamePanel.WIDTH/2 - width /2, GamePanel.HEIGHT - height);
    }

    private  BufferedImage loadImage(Player.State state){
        state = Player.getState();

        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col*width, row*height, width, height);
    }

    public  void drawPlayer(Graphics2D graph) {
        x = Level.getPlayerX();
        y = Level.getPlayerY();
        graph.drawImage(loadImage(Player.getState()),  (int)x, (int) y, width, height,null);
    }
}

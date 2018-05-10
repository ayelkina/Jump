package Game.Tools;

import Game.Sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bounce extends Sprite{

    private boolean playerJumped;

    public Bounce(){
        width = 65;
        height = 35;

        playerJumped = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/bounce.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public BufferedImage loadImage(){

        int row = 1;
        int col = 1;
        /*int col = state.ordinal() + 1;

        BufferedImage img = image.getSubimage(col*(width) - width, row*(height) - height, width, height);*/
        BufferedImage img = image.getSubimage(0, 0, width, height);

        return img;
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(loadImage(),  (int)x,  (int)y, width, height,null);
    }

    public boolean getPlayerJumped() {
        return playerJumped;
    }

    public void setPlayerJumped(boolean b) {
        playerJumped = b;
    }
}

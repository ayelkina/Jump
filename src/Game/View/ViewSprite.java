package Game.View;

import Game.States.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ViewSprite extends ViewGame {

    protected BufferedImage image;
    protected Level level;

    protected double x;
    protected double y;
    protected int width;
    protected int height;

    protected void loadSprite(String path){
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

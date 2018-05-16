package Game.View;

import Game.GameManagement.StateController;
import Game.States.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ViewSprite {

    protected BufferedImage image;
    protected StateController stateController;

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

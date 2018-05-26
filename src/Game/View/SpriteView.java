package Game.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class SpriteView {

    protected BufferedImage image;

    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected int imageWidth;
    protected int imageHeight;

    protected void loadSprite(String path){
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

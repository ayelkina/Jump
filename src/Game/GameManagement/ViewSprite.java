package Game.GameManagement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ViewSprite extends View {

    protected  BufferedImage image;

    protected  double x;
    protected  double y;
    protected  int width;
    protected  int height;

    protected void loadSprite(String path){
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void drawSprite(Graphics graph){
        graph.drawImage(image, (int)x, (int)y, width, height, null);
    }

    public  int getSpriteWidth() { return width; }
    public  int getSpriteHeight() { return height; }

    public void setSpritePosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

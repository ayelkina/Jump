/*
package Game.GameManagement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ViewSprite extends View {

    protected static BufferedImage image;

    protected static double x;
    protected static double y;
    protected static int width;
    protected static int height;

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

    public static int getSpriteWidth() { return width; }
    public static int getSpriteHeight() { return height; }

    public void setSpritePosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
*/

package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {

        File file = new File(path);
        image = ImageIO.read(new File(path));
//        image = ImageIO.read(getClass().getResource(path));
        return image;

/*    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
        return image;*/
    }

}


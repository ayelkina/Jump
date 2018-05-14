package Game.Tools;

import Game.GameManagement.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private BufferedImage image;

    public Background(String path){
        image = new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graph) {
        if(graph == null) System.out.println("null");
        graph.drawImage(image, 0,0, GamePanel.WIDTH, GamePanel.HEIGHT,null);
    }

    public BufferedImage getImage(){
        return image;
    }
}
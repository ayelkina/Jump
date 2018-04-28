package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class Tiles extends Sprite {

    public Tiles(){
        width = 100;
        height = 30;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/tile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(y > GamePanel.HEIGHT)
            y = 0;
    }
}

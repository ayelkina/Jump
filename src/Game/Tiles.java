package Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tiles extends Sprite {

    public Tiles(){

        width = 150;
        height = 70;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/ice.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

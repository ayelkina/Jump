package Game;

import javax.imageio.ImageIO;
import java.io.IOException;

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
}

package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Tiles extends Sprite {
    private final Random random;

    public Tiles(){
        random = new Random();
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
            y = -100;


    }

    public int random(int bound, int min){
        return random.nextInt(bound) + min;
    }

    public void setRandomPosition(){

    }
}

package Game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tiles extends Sprite {

    private boolean withBounce;
    private boolean lessThenZero;

    public Tiles(){
        width = 100;
        height = 30;

        withBounce = false;
        lessThenZero = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/tile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(y < 0)
            lessThenZero = true;
        else
            lessThenZero = false;

//        System.out.println(lessThenZero);
    }

    public void setWithBounce(boolean b){
        withBounce = b;
    }

    public boolean getWithBounce(){
        return withBounce;
    }
}

package Game.Tools;

import Game.Sprites.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bounce extends Sprite{

    private static final int downHeight = 25;
    private static final int upHeight = 65;

    private static boolean stay;
    private static boolean down;
    private static boolean up;

    public Bounce(){
        down = false;

        width = 70;
        height = downHeight;
    }

    public boolean getDown() {
        return down;
    }
    public boolean getUp() {
        return up;
    }
    public boolean getStay() {
        return stay;
    }

    public void setDown(boolean b) {
        down = b;
        up = stay = !b;
    }

    public void setStay(boolean b) {
        stay = b;
        height = downHeight;
        y += height - downHeight;
        up = down = !b;
    }

    public void setUp (boolean b) {
        up = b;
        height = upHeight;
        y -= height - downHeight;
        down = stay = !b;
    }
}
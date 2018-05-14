package Game.Sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.abs;

public abstract class Sprite {

    protected BufferedImage image;

    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    protected boolean left;
    protected boolean right;

    public Sprite(){}

    private void setVariables(){}

    public double getx() { return x; }
    public double gety() { return y; }
    public double getdx() { return dx; }
    public double getdy() { return dy; }

    public void setdx(double newdx) { dx = newdx;}
    public void setdy(double newdy) { y = newdy; }

    public void setx(double newx) { dx = newx;}
    public void sety(double newy) { y = newy; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public double getBoundsRight() { return width + x; }
    public double getBoundsDown() { return y + height; }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }

    public boolean intersectsX (Sprite s2){
        return (getBoundsRight() - width/3 > s2.getx() &&
                x + width/3  < s2.getBoundsRight());
    }

    public boolean intersectsY (Sprite s2){
        return ((getBoundsDown() <= s2.y + 3) && (getBoundsDown() >= s2.y - 3));
    }

    public boolean intersects(Sprite s2) {
        return (intersectsX(s2) && intersectsY(s2));
    }
}
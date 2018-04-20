
package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Sprite {

    private BufferedImage sprite;

    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    protected boolean left;
    protected boolean right;
    protected boolean falling;

    public Sprite(){}

    public int getx() { return (int)x; }
    public int gety() { return (int)y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }

    public void draw(java.awt.Graphics graph){
        graph.drawImage(sprite, (int)x, (int)y, width, height, null);
    }

}


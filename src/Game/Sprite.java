package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static java.lang.Math.abs;

public abstract class Sprite {

    protected BufferedImage image;

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    protected int width;
    protected int height;

    protected boolean left;
    protected boolean right;
    protected boolean toDraw;
    protected boolean falling;

    public Sprite(){}

    public int getx() { return (int)x; }
    public int gety() { return (int)y; }
    public int getdx() { return dx; }
    public int getdy() { return dy; }

    public void setdx(int newdx) { dx = newdx;}
    public void setdy(int newdy) { y = newdy; }

    public void setx(int newx) { dx = newx;}
    public void sety(int newy) { y = newy; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getBoundsRight() { return width + x; }
    public int getBoundsDown() { return y + height; }

//    public Point getPosition() { return (x,y); }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }

    public void draw(Graphics graph){
        graph.drawImage(image, (int)x, (int)y, width, height, null);
    }

    public boolean intersectsX (Sprite s2){
        if (getBoundsRight() - width/3 > s2.getx() &&
                x + width/3  < s2.getBoundsRight()) return true;
        return false;
    }

    public boolean intersectsY (Sprite s2){
        if (getBoundsDown() - s2.y < 10) return true;
        return false;
    }

    public boolean intersects(Sprite s2){
        if (intersectsX(s2) && intersectsY(s2)) return true;
        return false;
    }

    public double distanceFromY (Sprite s2){
        return s2.gety() - getBoundsDown();
    }


}


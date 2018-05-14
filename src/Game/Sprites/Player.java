package Game.Sprites;

import Game.GameManagement.Engine;
import Game.GameManagement.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite {

    private static boolean up;
    private static boolean down;
    private static boolean fall;
    private boolean left;
    private boolean right;

    public boolean jumpedFromBounce;

    private double downY;
    private double halfWidth;

    public double prevDownY;
    private double maxJump;

    public enum State {UP, DOWN, FALL};

    public Player() {
        setVariables();
        setPosition(GamePanel.WIDTH/2 - width /2, GamePanel.HEIGHT - height);
    }

    private void setVariables(){
        width = 70;
        height = 63;
        halfWidth = width /2;

        prevDownY = GamePanel.HEIGHT +50;
        maxJump = GamePanel.HEIGHT/4;

        jumpedFromBounce = false;
        downY = GamePanel.HEIGHT;

        dy = 6;
        dx = dy;
    }

    public double getDownY(){return downY;}
    public void setLeft(boolean b) {left = b; }
    public void setRight(boolean b) {right = b; }
    public boolean getFall() {return fall;}

    public void setUp () {
        up = true;
        down = fall = false;
    }
    public void setDown () {
        down = true;
        up = fall = false;
    }
    public void setFall () {
        fall = true;
        up = down = false;
    }

    public void setDy(double m){
        dy = m;
    }

    public void switchDy(){
        dy *= -1;
    }

    public void setDownY(double newDownY){
        downY = newDownY;
    }
    public boolean getUp(){return up;}
    public boolean getDown(){return down;}

    public double getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }

    public static State getState(){
        State state;

        state = State.valueOf("UP");
        if(down) { state = State.valueOf("DOWN"); }
        if(fall) { state = State.valueOf("FALL"); }

        return state;
    }

    private void jump(double downY, long time){
        y-= dy;
//        System.out.println(time);

        if (right) {x += dx;}
        if (left) {x -= dx; }

        if((getBoundsDown() - downY > 0 && down) || (downY-getBoundsDown() > maxJump && up)) {
            dy *= -1;
        }

        if(dy > 0) {setUp();}
        else {setDown();}
    }

    private void changeLocationIfOut(){

        if (x + halfWidth > GamePanel.WIDTH){
            x = (-halfWidth);
            setPosition((int)x, (int)y);
        }

        if (x + halfWidth < 0){
            x += GamePanel.WIDTH;
            setPosition((int)x, (int)y);
        }
    }

    public void update(long time){
        changeLocationIfOut();
        checkFallDown();

        if(!fall)
            jump(downY, time);
    }

    private void checkFallDown(){
        if (getBoundsDown() >= GamePanel.HEIGHT+10){
            y = 0;
            setFall();
        }
    }
}
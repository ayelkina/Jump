package Game.Sprites;

import Game.GameManagement.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean fall;

    public boolean jumpedFromBounce;

    private double downY;
    private double halfWidth;

    public double prevDownY;
    private double maxJump;

    private enum PlayerState {UP, DOWN, FALL};

    public Player() {
        loadSprite("/Pics/peng.png");
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

        dy = 1.2;
        dx = dy;
    }

    private BufferedImage loadImage(PlayerState state){

        int row = 0;
        int col = state.ordinal();

        return image.getSubimage(col*width, row*height, width, height);
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(loadImage(getState()),  (int)x, (int) y, width, height,null);
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

    public PlayerState getState(){
        PlayerState state;

        state = PlayerState.valueOf("UP");
        if(down) { state = PlayerState.valueOf("DOWN"); }
        if(fall) { state = PlayerState.valueOf("FALL"); }

        return state;
    }

    private void jump(double downY){
        y-= dy;

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

    public void update(){
        changeLocationIfOut();
        checkFallDown();

        if(!fall)
         jump(downY);
    }

    private void checkFallDown(){
        if (getBoundsDown() >= GamePanel.HEIGHT+10){
            y = 0;
            setFall();
        }
    }
}
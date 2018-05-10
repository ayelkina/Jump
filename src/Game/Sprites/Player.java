package Game.Sprites;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.abs;

public class Player extends Sprite {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean fall;

    private double downY;
    private double halfWidth;

    public double prevDownY;
    private double maxJump;


    public int count;

    public enum PlayerState {STAY, UP, DOWN, FALL};

    public Player() {

        width = 70;
        height = 63;
        halfWidth = width /2;

        prevDownY = 820;
        maxJump = 200;

        count = 0;

        setPosition(Game.GamePanel.WIDTH/2 - width /2, Game.GamePanel.HEIGHT - height);

        downY = 800;

        dy = 0.7;
        dx = 0.7;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/peng.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage loadImage(PlayerState state){

        int row = 1;
        int col = state.ordinal() + 1;

        BufferedImage img = image.getSubimage(col*(width) - width, row*(height) - height, width, height);

        return img;
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
        if(down)             //NIE PODOBA MI SIE
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

        state = PlayerState.valueOf("STAY");
        if(up) { state = PlayerState.valueOf("UP"); }
        if(down) { state = PlayerState.valueOf("DOWN"); }
        if(fall) { state = PlayerState.valueOf("FALL"); }

        return state;
    }

    public void jump(double downY){
        y-= dy;

        if (right) {x += dx;}
        if (left) {x -= dx; }

        if((getBoundsDown() - downY > 0 && down) || (downY-getBoundsDown() > maxJump && up)) {
            dy *= -1;
        }

        if(dy > 0) {setUp();}
        else {setDown();}
    }

    public void changeLocationIfOut(){

        if (x + halfWidth > Game.GamePanel.WIDTH){
            x = (-halfWidth);
            setPosition((int)x, (int)y);
        }

        if (x + halfWidth < 0){
            x += Game.GamePanel.WIDTH;
            setPosition((int)x, (int)y);
        }
    }

    public void update(){
        changeLocationIfOut();
        checkFallDown();


        if(!fall)
         jump(downY);
    }

    public void checkFallDown(){
        if (getBoundsDown() >= Game.GamePanel.HEIGHT+10){
            y = 0;
            setFall();
        }
    }
}
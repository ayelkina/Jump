package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.abs;

public class Player extends Sprite {

    public boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean fall;

    private int downY;
    public int downYPrev;
    private int halfWidth;
    private int time;
    private int speedMin;

    public enum PlayerState {STAY, UP, DOWN, FALL};

    public Player() {

        width = 70;
        height = 63;
        halfWidth = width /2;

        time = 0;
        speedMin = 4;

        setPosition(GamePanel.WIDTH/2 - width /2, GamePanel.HEIGHT - height);

        downY = 800;

        dy = 6;
        dx = 6;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/peng.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(PlayerState state){

        int row = 1;
        int col = state.ordinal() + 1;

        BufferedImage img = image.getSubimage(col*(width) - width, row*(width) - width, width, height);

        return img;
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(getImage(getState()),  x,  y, width, height,null);
    }

    public int getDownY(){return downY;}
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

    public void setDownY(int newDownY){
        downY = newDownY;
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

        if((getBoundsDown() - downY > 1 && down) || (downY-getBoundsDown() > 200 && up)) {
            dy *= -1;
        }

        if(dy > 0) {setUp();}
        else {setDown();}
    }

    public void changeLocationIfOut(){

        if (x + halfWidth > GamePanel.WIDTH){
            x = (-halfWidth);
            setPosition(x, y);
        }

        if (x + halfWidth < 0){
            x += GamePanel.WIDTH;
            setPosition(x, y);
        }
    }

    public void update(){
        changeLocationIfOut();
        checkFallDown();


        if(!fall)
         jump(downY);
    }

    public void checkFallDown(){
        if (getBoundsDown() >= GamePanel.HEIGHT+10){
            y = 0;
            setFall();

        }
    }
}
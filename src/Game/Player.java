package Game;

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

    private int downY;
    private int halfWidth;

    public int prevDownY;

    public int count;

    public enum PlayerState {STAY, UP, DOWN, FALL};

    public Player() {

        width = 70;
        height = 63;
        halfWidth = width /2;

        prevDownY = 820;

        count = 0;

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

    public BufferedImage loadImage(PlayerState state){

        int row = 1;
        int col = state.ordinal() + 1;

        BufferedImage img = image.getSubimage(col*(width) - width, row*(height) - height, width, height);

        return img;
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(loadImage(getState()),  x,  y, width, height,null);
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

    public void changeDy(){
        dy *= -1;
    }

    public void setDownY(int newDownY){
        if(down)             //NIE PODOBA MI SIE
             downY = newDownY;
    }
    public boolean getUp(){return up;}
    public boolean getDown(){return down;}

    public PlayerState getState(){
        PlayerState state;

        state = PlayerState.valueOf("STAY");
        if(up) { state = PlayerState.valueOf("UP"); }
        if(down) { state = PlayerState.valueOf("DOWN"); }
        if(fall) { state = PlayerState.valueOf("FALL"); }

        return state;
    }

    public void jump(int downY){
        y-= dy;

        if (right) {x += dx;}
        if (left) {x -= dx; }

        if((getBoundsDown() - downY > 0 && down) || (downY-getBoundsDown() > 200 && up)) {
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
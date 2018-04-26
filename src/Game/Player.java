package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Sprite {

    private BufferedImage image;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean fall;

    private int count;
    private double countMax;
    private double halfWidth;

    public enum PlayerState {STAY, UP, DOWN, FALL};

    public Player() {

        count = 0;

        width = 100;
        height = 100;
        halfWidth = width/2;

        dy = 6;
        dx = dy;

        countMax = 200/dy;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Pics/pengs4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(PlayerState state){

        int row = 1;
        int col = state.ordinal() + 1;

        BufferedImage img = image.getSubimage(col*(100) - 100, row*(100) - 100, width, height );

        return img;
    }

    public void draw(Graphics2D graph) {
        graph.drawImage(getImage(getState()), (int) x, (int) y, width, height,null);
    }

    public void setLeft(boolean b) {left = b;}
    public void setRight(boolean b) {right = b;}

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

    public PlayerState getState(){

        PlayerState state;
        state = PlayerState.valueOf("STAY");
        if(up) { state = PlayerState.valueOf("UP"); }
        if(down) { state = PlayerState.valueOf("DOWN"); }
        if(fall) { state = PlayerState.valueOf("FALL"); }

        return state;
    }

    public void jump(){

        ++count;
        y -= dy;

        if (right) {x += dx;}
        if (left) {x -= dx;}

        if(dy > 0) {setUp();}
              else {setDown();}

        if(count > countMax) {
            dy *= -1;
            count = 0;
        }
    }

    public void changeLocationIfOut(){

        if (x + halfWidth > GamePanel.WIDTH){
            setPosition((int)(-halfWidth), (int)y);
        }

        if (x + halfWidth < 0){
            setPosition((int)(x + GamePanel.WIDTH), (int)y);
        }
    }

    public void update(){
        changeLocationIfOut();
        jump();
    }
}
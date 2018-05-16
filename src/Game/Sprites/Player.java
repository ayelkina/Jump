package Game.Sprites;

import Game.GameManagement.GamePanel;
import Game.States.Level;

public class Player extends Sprite {

    private static boolean up;
    private static boolean down;
    private static boolean fall;
    private boolean left;
    private boolean right;
    private boolean jumpedFromBounce;

    private double downY;
    private double maxJump;

    public Player() {
        width = 70;
        height = 63;
    }

    public void setPlayer(){
        setUp();
        setPosition(GamePanel.WIDTH/2 - width /2, GamePanel.HEIGHT - height);

        maxJump = GamePanel.HEIGHT/4;
        downY = GamePanel.HEIGHT;

        dy = Level.GRID;
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

    private void jump(double downY){
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

        if (x + width/2 > GamePanel.WIDTH){
            x = (-width/2);
            setPosition((int)x, (int)y);
        }

        if (x + width/2 < 0){
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

    public boolean isJumpedFromBounce() {
        return jumpedFromBounce;
    }

    public void setJumpedFromBounce(boolean jumpedFromBounce) {
        this.jumpedFromBounce = jumpedFromBounce;
    }
}
package Game.Sprites;

import Game.GameManagement.Constants;
import Game.GameManagement.GamePanel;

public class Player extends Sprite {

    private boolean up;
    private boolean down;
    private boolean fall;
    private boolean left;
    private boolean right;
    private boolean animationLeft;
    private boolean jumpedFromBounce;

    private double downY;
    private double maxJump;


    private double speed;

    public Player() {
        width = Constants.PlayerWidth;
        height = Constants.PlayerHeight;
    }

    public void setPlayer(){
        setUp();
        setPosition(Constants.PlayermiddleOfPanel, Constants.PlayerBeginY);

        maxJump = Constants.basicJumpHeight;
        downY = GamePanel.HEIGHT;

        speed = Constants.GRID;
        dx = dy = 1;
    }

    public double getDownY(){return downY;}
    public boolean getLeft() {return left; }
    public boolean getRight() {return right;}
    public boolean getAnimationLeft() {return  animationLeft;}

    public void setLeft(boolean b) {left = b; }
    public void setRight(boolean b) {right = b;}
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

    private void jump(double downY, long time){
       y-= dy*speed;

        if (right) {x += dx*speed; animationLeft = false;}
        if (left) {x -= dx*speed; animationLeft = true;}

        if((getBoundsDown() - downY >= 0 && down) || (downY-getBoundsDown() >= maxJump && up)) {
            dy *= -1;
        }

        if(dy > 0) {setUp();}
        else {setDown();}
    }

    private void changeLocationIfOut(){

        if (x + width/2 > GamePanel.WIDTH){
            x = (-width/2);
        }

        if (x + width/2 < 0){
            x += GamePanel.WIDTH;
        }
    }

    public void update(long time){
        changeLocationIfOut();
        checkFallDown();

        speed = Constants.GRID*time/10000000;

        if(!fall)
            jump(downY, time);
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


    public double getSpeed() {
        return speed;
    }
}
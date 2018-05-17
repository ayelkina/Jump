package Game.GameManagement.Vars;

import Game.States.Level;

public class PlayerVariables extends Variables{

    private Level level;

    public PlayerVariables(Level level) {
        this.level = level;
    }

    public boolean isPlayerUp() {
        return level.getPlayer().getUp();
    }

    public boolean isPlayerDown() {
        return level.getPlayer().getDown();
    }

    public boolean isPlayerFall() {
        return level.getPlayer().getFall();
    }

    public void setPlayerLeft(boolean b) {
        level.getPlayer().setLeft(b);
    }

    public void setPlayerRight(boolean b) {
        level.getPlayer().setRight(b);
    }


    public double getX() {
        return level.getPlayer().getx();
    }


    public double getY() {
        return level.getPlayer().gety();
    }
}

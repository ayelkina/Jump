package Game.Tools;

import Game.Sprites.Sprite;

public class Tiles extends Sprite {

    private boolean withBounce;

    public Tiles(){
        setVariables();
        loadSprite("/Pics/tile.png");
    }

    private void setVariables(){
        width = 100;
        height = 25;

        withBounce = false;
    }

    public void setWithBounce(boolean b){ withBounce = b;}

    public boolean getWithBounce(){return withBounce;}
}

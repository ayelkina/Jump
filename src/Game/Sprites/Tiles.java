package Game.Sprites;


import Game.GameManagement.Constants;

public class Tiles extends Sprite {

    private boolean withBounce;

    public Tiles(){
        setVariables();
    }

    private void setVariables(){
        width = Constants.TileWidth;
        height = Constants.TileHeight;

        withBounce = false;
    }

    public void setWithBounce(boolean b){ withBounce = b;}

    public boolean getWithBounce(){return withBounce;}
}
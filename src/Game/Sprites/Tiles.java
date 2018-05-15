package Game.Sprites;


public class Tiles extends Sprite {

    private boolean withBounce;

    public Tiles(){
        setVariables();
    }

    private void setVariables(){
        width = 100;
        height = 25;

        withBounce = false;
    }

    public void setWithBounce(boolean b){ withBounce = b;}

    public boolean getWithBounce(){return withBounce;}
}
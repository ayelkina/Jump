package Game;

import java.awt.*;

public class Menu extends GameState{

    private Background backGround;
    private Font font;


    public Menu(){

        try{
            backGround = new Background("/Pics/Sky.png");
            font = new Font("Arial", Font.PLAIN, 12);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

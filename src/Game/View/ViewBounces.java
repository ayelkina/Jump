package Game.View;

import Game.GameManagement.StateController;
import Game.Sprites.Bounce;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewBounces extends ViewSprite {

    public ViewBounces(){
        width = 70;
        height = 25;
        level = StateController.getlevel();
        loadSprite("/Pics/bounce.png");
    }

   private BufferedImage loadImage(Bounce.State state){
       int row = 0;
       int col = state.ordinal();

       if(state == Bounce.State.UP)
           height = 65;
       else height = 25;

       return image.getSubimage(col*width, row*height, width, height);
   }

    public void draw(Graphics2D graph, int i) {
        x = level.getBounces().get(i).getx();
        y = level.getBounces().get(i).gety();
        graph.drawImage(loadImage(level.getBounces().get(i).getState()),  (int)x,  (int)y, width, height,null);
    }

}

package Game.View;

import Game.GameManagement.Constants;
import Game.GameManagement.StateController;
import Game.Sprites.Bounce;
import Game.States.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewBounces extends ViewSprite {

    public ViewBounces(StateController st) {
        stateController = st;

        width = Constants.BounceWidth;
        height = Constants.BounceDownHeight;

        loadSprite("/Pics/bounce.png");
    }

   private BufferedImage loadImage(Bounce.State state){
       int row = 0;
       int col = state.ordinal();

       if(state == Bounce.State.UP)
           height = Constants.BounceUpHeight;
       else height = Constants.BounceDownHeight;

       return image.getSubimage(col*width, row*height, width, height);
   }

    public void draw(Graphics2D graph, int i) {
        Level level = stateController.getlevel();

        x = level.getBounces().get(i).getx();
        y = level.getBounces().get(i).gety();
        graph.drawImage(loadImage(level.getBounces().get(i).getState()),  (int)x,  (int)y, width, height,null);
    }

}

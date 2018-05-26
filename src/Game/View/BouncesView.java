package Game.View;

import Game.GameManagement.Constants;
import Game.Sprites.Bounce;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BouncesView extends SpriteView {

    private Bounce bounce;

    public BouncesView(Bounce bounce) {
        this.bounce = bounce;

        width = Constants.BounceWidth;
        height = Constants.BounceDownHeight;

        imageHeight = Constants.BounceImageUpHeight;
        imageWidth = Constants.BounceImageWidth;

        loadSprite("/Res/Pics/bounce.png");
    }

   private BufferedImage loadImage(Bounce.State state){
       int row = 0;
       int col = state.ordinal();

       if(state == Bounce.State.UP) {
           imageHeight = Constants.BounceImageUpHeight;
           height = Constants.BounceUpHeight;
       }
       else {
           imageHeight = Constants.BounceImageDownHeight;
           height = Constants.BounceDownHeight;
       }

       return image.getSubimage(col*imageWidth, row*imageHeight, imageWidth, imageHeight);
   }

    public void draw(Graphics2D graph) {
        x = bounce.getx();
        y = bounce.gety();
        graph.drawImage(loadImage(bounce.getState()),  (int)x,  (int)y, width, height,null);
    }

}

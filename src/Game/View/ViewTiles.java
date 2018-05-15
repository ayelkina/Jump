package Game.View;

import Game.States.Level;

import java.awt.*;

public class ViewTiles extends  ViewSprite {

    public ViewTiles() {
        width = 100;
        height = 25;

        loadSprite("/Pics/tile.png");
    }

    public void draw(Graphics2D graph, int i) {
        x = Level.getTiles().get(i).getx();
        y = Level.getTiles().get(i).gety();
        graph.drawImage(image,  (int)x,  (int)y, width, height,null);
    }
}

package Game.GameManagement;

import Game.States.Level;

import java.awt.*;

public class ViewTiles extends  ViewSprite {

    public ViewTiles() {
        width = 100;
        height = 25;

        loadSprite("/Pics/tile.png");
    }

    public void draw(Graphics2D graph, int i) {
        x = Level.getTilesX(i);
        y = Level.getTilesY(i);
        graph.drawImage(image,  (int)x,  (int)y, width, height,null);
    }
}

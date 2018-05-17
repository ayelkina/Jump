package Game.View;

import Game.GameManagement.Constants;
import Game.GameManagement.GameController;
import Game.Sprites.Tiles;
import Game.States.Level;

import java.awt.*;
import java.util.Vector;

public class ViewTiles extends  ViewSprite {

    private Tiles tiles;

    public ViewTiles(Tiles tiles) {
        this.tiles = tiles;

        width = Constants.TileWidth;
        height = Constants.TileHeight;

        loadSprite("/Pics/tile.png");
    }

    public void draw(Graphics2D graph, int i) {
        x = tiles.getx();
        y = tiles.gety();
        graph.drawImage(image,  (int)x,  (int)y, width, height,null);
    }
}

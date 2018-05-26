package Game.View;

import Game.GameManagement.Constants;
import Game.Sprites.Tiles;

import java.awt.*;

public class TilesView extends SpriteView {

    private Tiles tiles;

    public TilesView(Tiles tiles) {
        this.tiles = tiles;

        width = Constants.TileWidth;
        height = Constants.TileHeight;

        loadSprite("/Res/Pics/tile.png");
    }

    public void draw(Graphics2D graph) {
        x = tiles.getx();
        y = tiles.gety();
        graph.drawImage(image,  (int)x,  (int)y, width, height,null);
    }
}

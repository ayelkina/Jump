package Game.View;

import Game.GameManagement.Constants;
import Game.GameManagement.StateController;
import Game.States.Level;

import java.awt.*;

public class ViewTiles extends  ViewSprite {

    public ViewTiles(StateController st) {
        stateController = st;

        width = Constants.TileWidth;
        height = Constants.TileHeight;

        loadSprite("/Pics/tile.png");
    }

    public void draw(Graphics2D graph, int i) {
        Level level = stateController.getlevel();

        x = level.getTiles().get(i).getx();
        y = level.getTiles().get(i).gety();
        graph.drawImage(image,  (int)x,  (int)y, width, height,null);
    }
}

package Game.View.ViewStates;

import Game.GameManagement.Constants;
import Game.Sprites.Bounce;
import Game.Sprites.Player;
import Game.Sprites.Tiles;
import Game.States.Level;
import Game.View.Background;
import Game.View.ViewBounces;
import Game.View.ViewPlayer;
import Game.View.ViewTiles;

import java.awt.*;
import java.io.File;
import java.util.Vector;

public class ViewLevel extends ViewState {

    private ViewPlayer viewPlayer;
    private Background background;
    private Font font;

    private Vector<ViewTiles> tilesVector;
    private Vector<ViewBounces> bouncesVector;

    private Level level;

    public ViewLevel(Level level) {
        super(level);
        this.level = level;

        background = new Background("/Pics/sky1.png");
        createEntityView();
        loadFont();
    }

    private void createEntityView(){
        Player player = level.getPlayer();
        Vector<Tiles> tiles = level.getTiles();
        Vector<Bounce> bounces = level.getBounces();

        viewPlayer = new ViewPlayer(player);

        tilesVector = new Vector<>();
        for (int i = 0; i <= Constants.TilesNumber; ++i) {
            tilesVector.addElement(new ViewTiles(tiles.get(i)));
        }

        bouncesVector = new Vector<>();
        for (int i = 0; i <= Constants.BouncesNumber; ++i) {
            bouncesVector.addElement(new ViewBounces(bounces.get(i)));
        }
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i < tilesVector.size(); ++i)
            tilesVector.get(i).draw(graph, i);

        for (int i = 0; i < bouncesVector.size(); ++i)
            bouncesVector.get(i).draw(graph, i);

        viewPlayer.drawPlayer(graph);
        drawCount(graph);
        graph.dispose();
    }

    private void loadFont() {
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawCount(Graphics2D graph) {
        graph.setColor(Color.BLACK);
        font = font.deriveFont(40f);
        graph.setFont(font);

        graph.drawString(Integer.toString(Level.getCount()), 10, 40);
    }
}

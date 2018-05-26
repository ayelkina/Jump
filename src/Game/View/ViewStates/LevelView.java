package Game.View.ViewStates;

import Game.GameManagement.Constants;
import Game.Sprites.Bounce;
import Game.Sprites.Player;
import Game.Sprites.Tiles;
import Game.States.Level;
import Game.View.Background;
import Game.View.BouncesView;
import Game.View.PlayerView;
import Game.View.TilesView;

import java.awt.*;
import java.io.File;
import java.util.Vector;

public class LevelView extends StateView {

    private PlayerView viewPlayer;
    private Background background;
    private Font font;

    private Vector<TilesView> tilesVector;
    private Vector<BouncesView> bouncesVector;

    private Level level;

    public LevelView(Level level) {
        super(level);
        this.level = level;

        background = new Background("/Res/Pics/sky1.png");
        createEntityView();
        loadFont();
    }

    private void createEntityView(){
        Player player = level.getPlayer();
        Vector<Tiles> tiles = level.getTiles();
        Vector<Bounce> bounces = level.getBounces();

        viewPlayer = new PlayerView(player);

        tilesVector = new Vector<>();
        for (int i = 0; i <= Constants.TilesNumber; ++i) {
            tilesVector.addElement(new TilesView(tiles.get(i)));
        }

        bouncesVector = new Vector<>();
        for (int i = 0; i <= Constants.BouncesNumber; ++i) {
            bouncesVector.addElement(new BouncesView(bounces.get(i)));
        }
    }

    @Override
    public void draw(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i < tilesVector.size(); ++i)
            tilesVector.get(i).draw(graph);

        for (int i = 0; i < bouncesVector.size(); ++i)
            bouncesVector.get(i).draw(graph);

        viewPlayer.drawPlayer(graph);
        drawCount(graph);
        graph.dispose();
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Res/Fonts/orange.ttf"));
        } catch (Exception e) {
            font = new Font(Font.SERIF, Font.BOLD, 40);
//            e.printStackTrace();
        }
    }

    private void drawCount(Graphics2D graph) {
        graph.setColor(Color.BLACK);
        font = font.deriveFont(40f);
        graph.setFont(font);

        graph.drawString(Integer.toString(Level.getCount()), 10, 40);
    }
}

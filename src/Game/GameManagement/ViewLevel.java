package Game.GameManagement;

import Game.States.GameState;
import Game.States.Level;
import Game.States.Menu;
import Game.States.StartMenu;
import Game.Tools.Background;
import Game.GameManagement.GamePanel;
import Game.Sprites.Player;
import Game.Tools.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class ViewLevel extends View {

    private ViewPlayer viewPlayer;

    private Vector<ViewTiles> tilesVector;
    private Vector<ViewBounces> bouncesVector;

    public ViewLevel(){
        background = new Background("/Pics/sky1.png");
        viewPlayer = new ViewPlayer();

        tilesVector = new Vector<>();
        for (int i = 0; i < Level.getTiles().size(); ++i) {
            tilesVector.addElement(new ViewTiles());
            tilesVector.get(i).x = Level.getTiles().get(i).getx();
            tilesVector.get(i).y = Level.getTiles().get(i).gety();
        }

        bouncesVector = new Vector<>();
        for (int i = 0; i < Level.getBounces().size(); ++i) {
            bouncesVector.addElement(new ViewBounces());
            bouncesVector.get(i).x = Level.getBounces().get(i).getx();
            bouncesVector.get(i).y = Level.getBounces().get(i).gety();
        }

        loadFont();
    }
    public  void drawLevel(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i < tilesVector.size(); ++i)
            tilesVector.get(i).draw(graph, i);

        for (int i = 0; i < bouncesVector.size(); ++i)
            bouncesVector.get(i).draw(graph, i);



        viewPlayer.drawPlayer(graph);
        drawCount(graph);
        graph.dispose();
    }

    private  void loadFont() {
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void drawCount(Graphics2D graph) {
        graph.setColor(Color.BLACK);
        font = font.deriveFont(40f);
        graph.setFont(font);

        graph.drawString(Integer.toString(Level.getCount()), 10, 40);
    }


}

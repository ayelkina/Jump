package Game.View;

import Game.States.Level;
import Game.States.GameState;

import java.awt.*;
import java.io.File;
import java.util.Vector;

public class ViewLevel extends ViewGame {

    private ViewPlayer viewPlayer;

    private Vector<ViewTiles> tilesVector;
    private Vector<ViewBounces> bouncesVector;

    private Level level;

    public ViewLevel(){
        background = new Background("/Pics/sky1.png");
        viewPlayer = new ViewPlayer();

        level = GameState.getlevel();

        tilesVector = new Vector<>();
        for (int i = 0; i < level.getTiles().size(); ++i) {
            tilesVector.addElement(new ViewTiles());
            tilesVector.get(i).x = level.getTiles().get(i).getx();
            tilesVector.get(i).y = level.getTiles().get(i).gety();
        }

        bouncesVector = new Vector<>();
        for (int i = 0; i < level.getBounces().size(); ++i) {
            bouncesVector.addElement(new ViewBounces());
            bouncesVector.get(i).x = level.getBounces().get(i).getx();
            bouncesVector.get(i).y = level.getBounces().get(i).gety();
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

        graph.drawString(Integer.toString(level.getCount()), 10, 40);
    }


}

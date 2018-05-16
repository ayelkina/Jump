package Game.View;

import Game.GameManagement.Constants;
import Game.States.Level;
import Game.GameManagement.StateController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Vector;

public class ViewLevel {

    private ViewPlayer viewPlayer;
    private Background background;
    private Font font;

    private Vector<ViewTiles> tilesVector;
    private Vector<ViewBounces> bouncesVector;

    private Level level;
    private StateController stateController;

    public ViewLevel(StateController st) {
        stateController = st;

        background = new Background("/Pics/sky1.png");
        viewPlayer = new ViewPlayer(stateController);

        tilesVector = new Vector<>();
        for (int i = 0; i <= Constants.TilesQuantity; ++i) {
            tilesVector.addElement(new ViewTiles(stateController));
        }

        bouncesVector = new Vector<>();
        for (int i = 0; i <= Constants.BounceQuantity; ++i) {
            bouncesVector.addElement(new ViewBounces(stateController));
        }

        loadFont();
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

    private  void loadFont() {
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

    public void keyPressed(KeyEvent key) {
        Level level = stateController.getlevel();

        if (key.getKeyCode() == KeyEvent.VK_RIGHT) level.getPlayer().setRight(true);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) level.getPlayer().setLeft(true);
    }

    public void keyReleased(KeyEvent key) {
        Level level = stateController.getlevel();

        if (key.getKeyCode() == KeyEvent.VK_RIGHT) level.getPlayer().setRight(false);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) level.getPlayer().setLeft(false);
    }



}

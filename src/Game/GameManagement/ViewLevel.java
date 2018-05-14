package Game.GameManagement;

import Game.States.GameState;
import Game.States.Level;
import Game.States.Menu;
import Game.States.StartMenu;
import Game.Tools.Background;
import Game.GameManagement.GamePanel;
import Game.Sprites.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewLevel extends View {

    protected BufferedImage playerSprite;
    protected BufferedImage tileSprite;
    protected BufferedImage bounceSprite;
   private  ViewPlayer viewPlayer;

    public ViewLevel(){
        background = new Background("/Pics/sky1.png");
        viewPlayer = new ViewPlayer();
        loadFont();
    }
    public  void drawLevel(Graphics2D graph) {

        background.draw(graph);




        /*for (int i = 0; i < Level.getTiles().size(); ++i)
            Level.getTiles().get(i).draw(graph);

        for (int i = 0; i < bounces.size(); ++i)
            bounces.get(i).draw(graph);*/



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

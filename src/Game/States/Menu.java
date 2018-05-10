package Game.States;

import Game.Tools.Background;
import Game.GameManagement.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class Menu extends GameState {

    protected Background background;
    protected Font font;
    protected int currentChoice;

    protected GameState gameState;
    protected String[] choice;

    public Menu() {
        currentChoice = 0;

        background = new Background("/Pics/sky1.png");
        loadFont();
    }

    public void loadFont(){
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double middleX(Graphics2D graph, String text) {
        FontRenderContext context = graph.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        return (GamePanel.WIDTH - bounds.getWidth()) / 2;
    }

    public void drawText(Graphics2D graph, String text, float size, double y){
        Color color = new Color(187, 128,68);
        graph.setColor(Color.BLACK);
        font = font.deriveFont(size);
        graph.setFont(font);

        int x = (int) middleX(graph, text);
        graph.drawString(text, x, (int)y);
    }

    public void drawOptionsVertical(Graphics2D graph, float size, double y){
        Color color = new Color(121, 117,116);
        graph.setColor(color);

        font = font.deriveFont(size);
        graph.setFont(font);

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }

            int x = (int) middleX(graph, choice[i]);
            graph.drawString(choice[i], x, (int)y + i * 70);
        }
    }

    public void drawOptionsHorizontal(Graphics2D graph, float size, double x, double y){
        font = font.deriveFont(size);
        graph.setFont(font);

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }
            graph.drawString(choice[i], (int)x + i * 130, (int)y );
        }
    }

    public void update(){}
    public void draw(Graphics2D graph) {}

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {}
    public void keyReleased(KeyEvent key) {}

}

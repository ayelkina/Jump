package Game.States;

import Game.Background;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Menu extends GameState {

    protected Background background;
    protected Font font;
    protected int currentChoice;

    protected GameState gameState;
    protected String[] choice;

    public Menu() {}

    public double middleX(Graphics2D graph, String text) {
        FontRenderContext context = graph.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        double x = (GamePanel.WIDTH - bounds.getWidth()) / 2;

        return x;
    }

    public void drawText(Graphics2D graph, String text, float size, double y){
        graph.setColor(Color.BLACK);
        font = font.deriveFont(size);
        graph.setFont(font);

        int x = (int) middleX(graph, text);
        graph.drawString(text, x, (int)y);
    }

    public void drawOptions(Graphics2D graph){
        font = font.deriveFont(48f);
        graph.setFont(font);

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }
            int x = (int) middleX(graph, choice[i]);
            graph.drawString(choice[i], x, GamePanel.HEIGHT/2 + i * 70);
        }
    }

    public void update(){}
    private void select() {}
    public void draw(Graphics2D graph) {}

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {}
    public void keyReleased(KeyEvent key) {}

}

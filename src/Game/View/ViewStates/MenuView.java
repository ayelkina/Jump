package Game.View.ViewStates;

import Game.GameManagement.GamePanel;
import Game.States.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class MenuView extends StateView {

    protected Font font;
    protected Menu menu;

    public MenuView(Menu menu) {
        super(menu);
        this.menu = menu;
    }

    @Override
    public void draw(Graphics2D graph) {}

    protected void loadFont(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Res/Fonts/orange.ttf"));
        } catch (Exception e) { ;
            e.printStackTrace();
        }
    }

    protected void drawText(Graphics2D graph, String text, float size, double y){
        graph.setColor(Color.BLACK);
        font = font.deriveFont(size);
        graph.setFont(font);

        int x = (int) middleX(graph, text);
        graph.drawString(text, x, (int)y);
    }

    private double middleX(Graphics2D graph, String text) {
        FontRenderContext context = graph.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        return (GamePanel.WIDTH - bounds.getWidth()) / 2;
    }


    protected void drawOptionsVertical(Graphics2D graph, float size, double y){
        Color color = new Color(121, 117,116);
        graph.setColor(color);

        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = menu.getCurrentChoice();
        String []choice = menu.getChoice();

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }

            int x = (int) middleX(graph, choice[i]);
            graph.drawString(choice[i], x, (int)y + i * GamePanel.HEIGHT/10);
        }
    }

    protected void drawOptionsHorizontal(Graphics2D graph, float size, double x, double y){
        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = menu.getCurrentChoice();
        String []choice = menu.getChoice();

        for(int i = 0; i < choice.length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }
            graph.drawString(choice[i], (int)x + i * GamePanel.WIDTH/5, (int)y );
        }
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {}
    public void keyReleased(KeyEvent key) {}

}
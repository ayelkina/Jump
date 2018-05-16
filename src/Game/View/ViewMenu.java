package Game.View;

import Game.States.Menu;;
import Game.GameManagement.GamePanel;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;

abstract class ViewMenu extends ViewGame {

    private Font font;

    protected void loadFont(){
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void drawText(Graphics2D graph, String text, float size, double y){
        Color color = new Color(187, 128,68);
        graph.setColor(Color.BLACK);
        font = font.deriveFont(size);
        graph.setFont(font);

        int x = (int) middleX(graph, text);
        graph.drawString(text, x, (int)y);
    }

    private  double middleX(Graphics2D graph, String text) {
        FontRenderContext context = graph.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        return (GamePanel.WIDTH - bounds.getWidth()) / 2;
    }


    protected void drawOptionsVertical(Graphics2D graph, float size, double y){
        Color color = new Color(121, 117,116);
        graph.setColor(color);

        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = Menu.getCurrentChoice();

        for(int i = 0; i < Menu.getChoice().length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }

            int x = (int) middleX(graph, Menu.getChoice()[i]);
            graph.drawString(Menu.getChoice()[i], x, (int)y + i * 70);
        }
    }

    protected void drawOptionsHorizontal(Graphics2D graph, float size, double x, double y){
        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = Menu.getCurrentChoice();

        for(int i = 0; i < Menu.getChoice().length; ++i) {
            if (i == currentChoice) {
                graph.setColor(Color.BLACK);
            }
            else {
                graph.setColor(Color.LIGHT_GRAY);
            }
            graph.drawString(Menu.getChoice()[i], (int)x + i * 130, (int)y );
        }
    }
}
package Game.View;

import Game.States.GameState;
import Game.Tools.Background;
import Game.GameManagement.GamePanel;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    protected  Background background;
    private ViewMenu viewMenu;
    private ViewLevel viewLevel;
    protected  Font font;

    public View() {
        background = new Background("/Pics/sky1.png");
    }

    public  void draw(Graphics2D graph) {
        drawGameState(graph);
//        Engine.getState().draw(graph);
        graph.dispose();
    }


    public  void drawGameState(Graphics2D graph) {
        if(GameState.getCurState() == GameState.getstartMenu()) {
          if(viewMenu == null) viewMenu = new ViewMenu();
                    viewMenu.drawMenu(graph);
        }

        if(GameState.getCurState() == GameState.getlevel()) {
            if (viewLevel == null) viewLevel = new ViewLevel();
            viewLevel.drawLevel(graph);
        }

        if(GameState.getCurState() == GameState.getgameOver())
            drawGameOver(graph);
    }

    public  void drawBackground(Graphics2D graph) {
        graph.drawImage(background.getImage(), 0,0, GamePanel.WIDTH, GamePanel.HEIGHT,null);
    }

/*

    public static void drawMenu(Graphics2D graph) {
        background = new Background("/Pics/sky1.png");
        loadFont();

        String gameTitle = "Jump!";

//        drawBackground(graph);
//        drawText(graph, gameTitle, 120f, 300);
//        drawOptionsVertical(graph, 48f, 400);

        graph.dispose();
    }



    public static void loadFont(){
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drawText(Graphics2D graph, String text, float size, double y){
        Color color = new Color(187, 128,68);
        graph.setColor(Color.BLACK);
        font = font.deriveFont(size);
        graph.setFont(font);

        int x = (int) middleX(graph, text);
        graph.drawString(text, x, (int)y);
    }

    public static double middleX(Graphics2D graph, String text) {
        FontRenderContext context = graph.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        return (GamePanel.WIDTH - bounds.getWidth()) / 2;
    }


    public static void drawOptionsVertical(Graphics2D graph, float size, double y){
        Color color = new Color(121, 117,116);
        graph.setColor(color);

        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = StartMenu.getCurrentChoice();

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

    public void drawOptionsHorizontal(Graphics2D graph, float size, double x, double y){
        font = font.deriveFont(size);
        graph.setFont(font);

        int currentChoice = 0;

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

    public static void drawLevel(Graphics2D graph) {

    }*/

    public static void drawGameOver(Graphics2D graph) {

    }



}

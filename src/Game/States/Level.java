package Game.States;

import Game.Sprites.Player;
import Game.Tools.Background;
import Game.Tools.Tiles;
import Game.GamePanel;
import Game.Tools.Bounce;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;

public class Level extends GameState {

    private final Random random;
    private GameState gameState;
    private Font font;

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;
    private Vector<Bounce> bounces;

    private double offTime;
    private double offset;
    private int heightCount;
    private int plusCount;

    private double nearestTile;
    private double maxReachedTile;
    public final int maxPlayerHeight = 400;

    public Level(GameState gameState) {

        this.gameState = gameState;
        heightCount = 0;
        plusCount = 0;
        nearestTile = 820;
        maxReachedTile = 820;

        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        random = new Random();

        background = new Background("/Pics/sky1.png");
        player = new Player();
        tiles = new Vector<Tiles>();
        for (int i = 0; i <= 15; ++i)
            tiles.addElement(new Tiles());

        bounces = new Vector<Bounce>();
        for (int i = 0; i <= 2; ++i)
            bounces.addElement(new Bounce());

        setTilesPositions();
        setBouncePosition();

        offTime = 0;
    }



    public void setTilesPositions() {
        tiles.get(0).setPosition(random(300, 100), 700);
        for (int i = 1; i < tiles.size(); ++i) {
            setRandomTile(i);
        }
    }

    public void setBouncePosition() {
        for(int i =0; i<bounces.size(); ++i)
             setRandomBounce(i);

    }

    public void setRandomTile(int currentTile) {
        int prev;

        if (currentTile == 0) prev = tiles.size() - 1;
        else prev = currentTile - 1;

        double prevY = tiles.get(prev).gety();
        double prevX = tiles.get(prev).getx();

        double newX = random(500, 0);
        double newY = random(100, (int)prevY - 150);

        tiles.get(currentTile).setPosition(newX, newY);
    }

    public int random(int bound, int min) {
        return random.nextInt(bound) + min;
    }

    public void setRandomBounce(int i) {
        int k = random(14, 0);
        if(tiles.get(k).gety() < 0 && !tiles.get(k).getWithBounce()) {
            bounces.get(i).setPosition(tiles.get(k).getx(), tiles.get(k).gety() - bounces.get(i).getHeight() + 5);
            tiles.get(k).setWithBounce(true);

            return;
        }
        else setRandomBounce(i);
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i < tiles.size(); ++i)
            tiles.get(i).draw(graph);

        for(int i = 0; i< bounces.size(); ++i)
            bounces.get(i).draw(graph);

        player.draw(graph);
        drawCount(graph);
        graph.dispose();
    }

    public void drawCount(Graphics2D graph) {

        graph.setColor(Color.BLACK);
        font = font.deriveFont(40f);
        graph.setFont(font);

        graph.drawString(Integer.toString(heightCount), 10, 40);
    }

    public void update() {
        player.update();

        if (!player.getFall()) {
            jumpFromTile();
            jumpFromBounce();
        }

        moveTiles();
        checkGameOver();

        setCount();
    }

    public void jumpFromTile() {
        player.setDownY(nearestDownTile());
    }

//ZMIENIC! sprawdzic bounce w nearestdowntile
    public void jumpFromBounce() {
        for(int i = 0; i < bounces.size(); ++i)
        if (player.intersects(bounces.get(i)) && !bounces.get(i).getPlayerJumped()) {
            player.setDy(1.2);
            player.setMaxJump(800);
            bounces.get(i).setPlayerJumped(true);
        }
    }

    public void setCount() {
        if (player.getState() == Player.PlayerState.UP) {
//            ++heightCount;

            if (player.getDownY() < player.prevDownY) {
//                ++heightCount;

                heightCount += player.prevDownY - player.getDownY();
                maxReachedTile = player.getDownY(); //ZMIENIC
//                System.out.println(maxReachedTile);
            }

            player.prevDownY = player.getDownY();
        }
    }

    public double nearestDownTile() {
        nearestTile = GamePanel.HEIGHT + 20;

        for (int i = tiles.size() - 1; i >= 0; --i)
            if (abs(player.distanceFromY(tiles.get(i))) < abs(player.getdy()) && player.intersectsX(tiles.get(i))) {
                return tiles.get(i).gety();
            }
        return nearestTile;
    }

    public void moveTiles() {
        if (player.getBoundsDown() - player.getDownY() > 0) offset = offset();
        if (offset == 0) return;

        if (player.gety() <= maxPlayerHeight) {
            ++offTime;

            if (offTime < offset / player.getdy()) {
                player.setPosition(player.getx(), maxPlayerHeight);

                for (int i = 0; i < tiles.size(); ++i) {
                    tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
                    if (tiles.get(i).gety() > GamePanel.HEIGHT) {
                        setRandomTile(i);
                        tiles.get(i).setWithBounce(false);
                    }
                }

                for (int i = 0; i < bounces.size(); ++i) {
                    bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
                    if(bounces.get(i).gety() > GamePanel.HEIGHT) setRandomBounce(i);
                }

            } else {
                if (player.getdy() > 0) player.switchDy();
                player.setDownY(nearestDownTile());

                offTime = 0;
            }
        }
    }

    public double offset() {
        double currentY = player.gety();
        if (currentY - maxPlayerHeight < player.getMaxJump()) return maxPlayerHeight + player.getMaxJump() - currentY;

        return 0;
    }

    public void checkGameOver() {

        if (player.getFall()) {
            player.sety(player.gety() - player.getdy());

            for (int i = 0; i < tiles.size(); ++i) {
                tiles.get(i).setPosition(tiles.get(i).getx(),  (tiles.get(i).gety() + player.getdy()));
            }

            for (int i = 0; i < bounces.size(); ++i) {
                bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
            }

            if (player.gety() > GamePanel.HEIGHT - 100) gameState.loadState(State.GAMEOVER);
        }
    }


    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(true);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(true);
    }

    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(false);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(false);
    }
}
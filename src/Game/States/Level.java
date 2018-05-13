package Game.States;

import Game.Sprites.Player;
import Game.Tools.Background;
import Game.Tools.Tiles;
import Game.GameManagement.GamePanel;
import Game.Tools.Bounce;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Level extends GameState {

    private final Random random;
    private GameState gameState;
    private Font font;

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;
    private Vector<Bounce> bounces;

    private double playerSuspendedTime;
    private double timeLongJump;
    private double offset;
    private static int heightCount;
    private int plusCount;

    private boolean longJump; //ZROBIC COS Z TYM

    private double bouncedDy = 8;
    private double bouncedJumpHeight = 800;

    private Bounce jumpedBounce;

    private double nearestTileY;
    private Tiles maxReachedTile;
    private Tiles prevMaxTile;
    private int playerHeightLimit;

    public Level(GameState gameState) {

        this.gameState = gameState;
        random = new Random();

        loadVariables();
        loadEntity();

        setTilesPositions();
        setBouncePosition();
    }

    private void loadVariables() {
        jumpedBounce = null;
        heightCount = 0;
        plusCount = 0;
        nearestTileY = 820;
        maxReachedTile = null;
        playerSuspendedTime = 0;
        playerHeightLimit = 400;

        timeLongJump = 0;
        longJump = false;
    }

    private void loadEntity() {
        loadFont();

        background = new Background("/Pics/sky1.png");
        player = new Player();
        tiles = new Vector<Tiles>();
        for (int i = 0; i <= 15; ++i)
            tiles.addElement(new Tiles());

        bounces = new Vector<Bounce>();
        for (int i = 0; i <= 2; ++i)
            bounces.addElement(new Bounce());
    }

    private void loadFont() {
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTilesPositions() {
        tiles.get(0).setPosition(random(100, 200), 700);
        for (int i = 1; i < tiles.size(); ++i) {
            setRandomTile(i);
        }
    }

    private void setBouncePosition() {
        bounces.get(0).setPosition(tiles.get(3).getx(), tiles.get(3).gety() - bounces.get(0).getHeight());
        for (int i = 1; i < bounces.size(); ++i)
            setRandomBounce(i);
    }

    private void setRandomTile(int currentTile) {
        int prevTile;

        if (currentTile == 0) prevTile = tiles.size() - 1;
        else prevTile = currentTile - 1;

        double prevY = tiles.get(prevTile).gety();

        double newX = random(500, 0);
        double newY = random(100, prevY - 150);

        tiles.get(currentTile).setPosition(newX, newY);
    }

    private double random(int bound, double min) {
        return random.nextInt(bound) + min;
    }

    private void setRandomBounce(int i) {
        int k = random.nextInt(14);

        if (tiles.get(k).gety() < 0 && !tiles.get(k).getWithBounce()) {
            bounces.get(i).setPosition(tiles.get(k).getx(), tiles.get(k).gety() - bounces.get(i).getHeight());
            tiles.get(k).setWithBounce(true);
        } else setRandomBounce(i);
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i < tiles.size(); ++i)
            tiles.get(i).draw(graph);

        for (int i = 0; i < bounces.size(); ++i)
            bounces.get(i).draw(graph);

        player.draw(graph);
        drawCount(graph);
        graph.dispose();
    }

    private void drawCount(Graphics2D graph) {

        graph.setColor(Color.BLACK);
        font = font.deriveFont(40f);
        graph.setFont(font);

        graph.drawString(Integer.toString(heightCount), 10, 40);
    }

    public void update() {
        player.update();

        if (!player.getFall()) {
            jumpFromTile();
//            jumpFromBounce();
            stopLongJump();
        }

        moveMap();
        checkGameOver();

        setCount();
    }

    private void jumpFromTile() {
        player.setDownY(nearestDownTileY());
    }

    private void jumpFromBounce() {
        for (int i = 0; i < bounces.size(); ++i) {
            if (player.getDown() && player.intersects(bounces.get(i))) {
                jumpedBounce = bounces.get(i);
                jumpedBounce.setDown(true);
                longJump = true;
            }

            if (longJump && player.getUp()) {
                player.setDy(player.getdy() * 2);
                player.setMaxJump(bouncedJumpHeight);

                player.jumpedFromBounce = true;
                jumpedBounce.setUp(true);

                longJump = false;
            }
        }
    }

    private void stopLongJump() {
        if (player.jumpedFromBounce && player.getDown()) {
            player.setDy(player.getdy() / 2);
            player.setMaxJump(200);
            player.jumpedFromBounce = false;
        }
    }

    private void setCount() {
        if(player.getUp()) {
            double downY = player.getDownY();

            if (downY < player.prevDownY) {
                ++heightCount;
                player.prevDownY = downY;

//                heightCount += player.prevDownY - player.getDownY();
//                maxReachedTile = player.getDownY(); //ZMIENIC
//                System.out.println(maxReachedTile);
            }
        }
        else {

        }
    }

    private void count(boolean b){
        if(b == true)
            ++heightCount;
    }

    public static int getCount() {
        return heightCount;
    }

    private double nearestDownTileY() {

        nearestTileY = GamePanel.HEIGHT + 20;

        for (int i = tiles.size() - 1; i >= 0; --i)
            if(player.intersects(tiles.get(i))){
                prevMaxTile = maxReachedTile;
                maxReachedTile = tiles.get(i);
                return tiles.get(i).gety();
            }
        return nearestTileY;
    }

    private void moveMap() {
        if (playerIntersectsTile()) offset = predicMaxHeightMinusLimitHeight();
        if (offset == 0) return;

        if (player.gety() <= playerHeightLimit) {
            ++playerSuspendedTime;

            if (playerSuspendedTime < offset / player.getdy()) {
                player.setPosition(player.getx(), playerHeightLimit);

                moveTiles();
                moveBounces();

            } else {
                setPlayerDown();
                playerSuspendedTime = 0;
            }
        }
    }

    private boolean playerIntersectsTile() {
        return player.getBoundsDown() > player.getDownY();
    }

    private double predicMaxHeightMinusLimitHeight() {
        double currentY = player.gety();
        if (currentY - playerHeightLimit < player.getMaxJump())
            return playerHeightLimit + player.getMaxJump() - currentY;

        return 0;
    }

    private void moveTiles() {
        for (int i = 0; i < tiles.size(); ++i) {
            tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
            if (tiles.get(i).gety() > GamePanel.HEIGHT) {
                tiles.get(i).setWithBounce(false);
                setRandomTile(i);
            }
        }
    }

    private void moveBounces() {
        for (int i = 0; i < bounces.size(); ++i) {
            bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
            if (bounces.get(i).gety() > GamePanel.HEIGHT) {
                bounces.get(i).setStay(true);
                setRandomBounce(i);
            }
        }
    }

    private void setPlayerDown() {
        if (player.getdy() > 0) player.switchDy();
        player.setDownY(nearestDownTileY());
    }

    private void checkGameOver() {

        if (player.getFall()) {
            player.sety(player.gety() - player.getdy());

            for (int i = 0; i < tiles.size(); ++i) {
                tiles.get(i).setPosition(tiles.get(i).getx(), (tiles.get(i).gety() + player.getdy()));
            }

            for (int i = 0; i < bounces.size(); ++i) {
                bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
            }

            if (player.getBoundsDown() > GamePanel.HEIGHT) gameState.loadState(State.GAMEOVER);
        }
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
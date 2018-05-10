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
    private int heightCount;
    private int plusCount;

    private double bounceDy = 1.2;
    private double bounceJumpHeight = 800;

    private double nearestTileY;
    private double maxReachedTile;
    private final int playerHeightLimit = 400;

    public Level(GameState gameState) {

        this.gameState = gameState;
        random = new Random();

        loadVariables();
        loadEntity();

        setTilesPositions();
        setBouncePosition();
    }

    private void loadVariables() {
        heightCount = 0;
        plusCount = 0;
        nearestTileY = 820;
        maxReachedTile = 820;
        playerSuspendedTime = 0;

        timeLongJump = 0;

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

    private void loadFont(){
        try {
            File fontFile = new File("Res/Fonts/orange.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTilesPositions() {
        tiles.get(0).setPosition(random(300, 100), 700);
        for (int i = 1; i < tiles.size(); ++i) {
            setRandomTile(i);
        }
    }

    private void setBouncePosition() {
        for(int i = 0; i<bounces.size(); ++i)
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

        if(tiles.get(k).gety() < 0 && !tiles.get(k).getWithBounce()) {
            bounces.get(i).setPosition(tiles.get(k).getx(), tiles.get(k).gety() - bounces.get(i).getHeight() + 5);
            tiles.get(k).setWithBounce(true);
        }
        else setRandomBounce(i);
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for (int i = 0; i< tiles.size(); ++ i)
            tiles.get(i).draw(graph);

        for (int i = 0; i< bounces.size(); ++ i)
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
//            stopLongJump();
        }

        moveMap();
        checkGameOver();

        setCount();
    }

    private void jumpFromTile() {
        player.setDownY(nearestDownTileY());
    }

    private void jumpFromBounce() {
        for(int i = 0; i < bounces.size(); ++i)
        if (player.intersects(bounces.get(i)) && !bounces.get(i).getPlayerJumped()) {

            player.setDy(bounceDy);
            player.setMaxJump(bounceJumpHeight);
            bounces.get(i).setPlayerJumped(true);
            player.jumpedFromBounce = true;
        }
    }

    private void stopLongJump(){
       if(player.jumpedFromBounce) ++timeLongJump;

        if(timeLongJump >= bounceJumpHeight / bounceDy) {
            player.setDy(0.7);
            player.setMaxJump(200);
            player.jumpedFromBounce = false;
            timeLongJump = 0;
        }
    }

    private void setCount() {
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

    private double nearestDownTileY() {
        nearestTileY = GamePanel.HEIGHT + 20;

        for (int i = tiles.size() - 1; i >= 0; --i)
            if (abs(player.distanceFromY(tiles.get(i))) < abs(player.getdy()) && player.intersectsX(tiles.get(i))) {
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
    
    private boolean playerIntersectsTile(){ return player.getBoundsDown() > player.getDownY(); }

    private double predicMaxHeightMinusLimitHeight() {
        double currentY = player.gety();
        if (currentY - playerHeightLimit < player.getMaxJump()) return playerHeightLimit + player.getMaxJump() - currentY;

        return 0;
    }

    private void moveTiles(){
        for (int i = 0; i < tiles.size(); ++i) {
            tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
            if (tiles.get(i).gety() > GamePanel.HEIGHT) {
                setRandomTile(i);
                tiles.get(i).setWithBounce(false);
            }
        }
    }

    private void moveBounces(){
        for (int i = 0; i < bounces.size(); ++i) {
            bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
            if(bounces.get(i).gety() > GamePanel.HEIGHT){
                setRandomBounce(i);
                bounces.get(i).setPlayerJumped(false);
            }
        }
    }

    private void setPlayerDown(){
        if (player.getdy() > 0) player.switchDy();
        player.setDownY(nearestDownTileY());
    }



    private void checkGameOver() {

        if (player.getFall()) {
            player.sety(player.gety() - player.getdy());

            for (int i = 0; i < tiles.size(); ++i) {
                tiles.get(i).setPosition(tiles.get(i).getx(),  (tiles.get(i).gety() + player.getdy()));
            }

            for (int i = 0; i < bounces.size(); ++i) {
                bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy());
            }

            if (player.getBoundsDown() > GamePanel.HEIGHT ) gameState.loadState(State.GAMEOVER);
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
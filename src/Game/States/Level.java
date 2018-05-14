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

public class Level extends GameState {

    private final Random random;
    private GameState gameState;

    private Font font;
    private Background background;

    private static Player player;
    private static Vector<Tiles> tiles;
    private static Vector<Bounce> bounces;

    private double playerSuspendedTime;
    private double offset;
    private static int heightCount;
    private double diff;

    private boolean longJump; //ZROBIC COS Z TYM
    private Bounce jumpedBounce;

    private double nearestTileY;
    private Tiles currNearestTile;
    private Tiles prevJumpedTile;
    private int playerHeightLimit;

    private boolean count;
    private boolean startCount;

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
        nearestTileY = 820;
        currNearestTile = prevJumpedTile = null;
        playerSuspendedTime = 0;
        diff = 0;
        playerHeightLimit = 400;

        longJump = false;
    }

    private void loadEntity() {
        player = new Player();
        tiles = new Vector<Tiles>();
        for (int i = 0; i <= 15; ++i)
            tiles.addElement(new Tiles());

        bounces = new Vector<Bounce>();
        for (int i = 0; i <= 2; ++i)
            bounces.addElement(new Bounce());
    }

    public static double getPlayerX(){
        return player.getx();
    }

    public static double getPlayerY(){
        return player.gety();
    }

    public static double getBounceX(int i) {
        return bounces.get(i).getx();
    }

    public static double getBounceY(int i) {
        return bounces.get(i).gety();
    }

    public static double getTilesX(int i) {
        return tiles.get(i).getx();
    }

    public static double getTilesY(int i) {
        return tiles.get(i).gety();
    }

    public static Vector<Tiles> getTiles(){
        return tiles;
    }

    public static Vector<Bounce> getBounces(){
        return bounces;
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

    public void update(long time) {
        player.update(time);

        if (!player.getFall()) {
            jumpFromTile();
            jumpFromBounce();
            stopLongJump();
            setCount();
        }

        moveMap();
        checkGameOver();
    }

    private void jumpFromTile() {
        if(player.getDown())
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
                heightCount+=800;
                player.setDy(player.getdy() * 2);
                player.setMaxJump(player.getMaxJump()*4);
                player.jumpedFromBounce = true;
                jumpedBounce.setUp(true);
//                diff = 800;
//                count = true;
//                count(diff);

                longJump = false;
            }
        }
    }

    private void stopLongJump() {
        if (player.jumpedFromBounce && player.getDown()) {
            player.setDy(player.getdy() / 2);
            player.setMaxJump(player.getMaxJump()/4);
            player.jumpedFromBounce = false;
        }
    }

    private void setCount() {

        if(currNearestTile != null && prevJumpedTile !=null) {
            if (prevJumpedTile.gety() > currNearestTile.gety()) {
//                diff = prevJumpedTile.gety() - currNearestTile.gety();
//                System.out.println(diff);
//                count = true;
                heightCount += prevJumpedTile.gety() - currNearestTile.gety();
            }
        }
        count(diff);
    }

    public void count(double diff){
        if(player.getUp() && count) {
            ++heightCount;
            startCount = true;
        }
        if(startCount )
            if(player.getDown() && startCount) {
                count = false;
                startCount = false;
            }
    }

    public static int getCount() {
        return heightCount;
    }

    private double nearestDownTileY() {
        nearestTileY = GamePanel.HEIGHT + 20;

        for (int i = tiles.size() - 1; i >= 0; --i)
            if(player.intersects(tiles.get(i))){
                prevJumpedTile = currNearestTile;
                currNearestTile = tiles.get(i);
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
            if (bounces.get(i).getBoundsDown() > GamePanel.HEIGHT) {
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
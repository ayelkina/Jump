package Game.States;

import Game.GameManagement.*;
import Game.Sprites.Player;
import Game.Sprites.Tiles;
import Game.Sprites.Bounce;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

public class Level extends State {

    private int playerHeightLimit = GamePanel.HEIGHT / 2;

    private Player player;
    private Vector<Tiles> tiles;
    private Vector<Bounce> bounces;
    private Bounce jumpedBounce;
    private Tiles currNearestTile;
    private Tiles prevJumpedTile;

    private double playerSuspendedTime;
    private double offset;

    private double nearestTileY;

    private int heightFromStart;
    private static int heightCount;
    private boolean longJump;
    private boolean changeState;
    private final Random random;

    public Level() {
        random = new Random();

        loadEntity();
        loadNew();
    }

    private void loadEntity() {
        player = new Player();

        tiles = new Vector<Tiles>();
        for (int i = 0; i <= Constants.TilesNumber; ++i)
            tiles.addElement(new Tiles());

        bounces = new Vector<Bounce>();
        for (int i = 0; i <= Constants.BouncesNumber; ++i)
            bounces.addElement(new Bounce());
    }

    @Override
    public void loadNew() {
        player.setPlayer();
        loadVariables();

        setTilesPositions();
        setBouncePosition();
    }

    private void loadVariables() {
        heightCount = 0;
        nearestTileY = GamePanel.HEIGHT;
        heightFromStart = 0;
        currNearestTile = null;
        changeState = false;
    }

    public void setTilesPositions() {
        tiles.get(0).setPosition(random(Constants.TileWidth, Constants.minFirstTileX), Constants.firstTileY);
        for (int i = 1; i < tiles.size(); ++i) {
            setRandomTile(i);
        }
    }

    public void setBouncePosition() {
        bounces.get(0).setPosition(tiles.get(3).getx(), tiles.get(3).gety() - bounces.get(0).getHeight());
        for (int i = 1; i < bounces.size(); ++i) {
            setRandomBounce(i);
        }
    }

    private void setRandomTile(int currentTile) {
        int prevTile;;

        if (currentTile == 0) prevTile = tiles.size() - 1;
        else prevTile = currentTile - 1;

        double prevY = tiles.get(prevTile).gety();
        double prevX = tiles.get(prevTile).getx();
        int maxDistanceX = Constants.maxTilesDistance + Constants.TileWidth;

        int addBoundY = Constants.maxTilesDistance - Constants.minTilesDistance;
        int addBoundX = maxDistanceX*2;

        double newY = random(addBoundY, prevY - Constants.maxTilesDistance);
        double newX = random(addBoundX, prevX - maxDistanceX);

        newX = changeIfOutOfPanel(newX);

        tiles.get(currentTile).setPosition(newX, newY);
    }

    private double changeIfOutOfPanel(double x){
        if(x < 0) {
            if (x + Constants.TileWidth < 0) {
                return x + GamePanel.WIDTH;
            } else return x + Constants.TileWidth;
        }

        if(x + Constants.TileWidth > GamePanel.WIDTH) {
            if (x  > GamePanel.WIDTH) {
                return x -GamePanel.WIDTH;
            } else return x -Constants.TileWidth;
        }
        return x;
    }

    public double random(int max, double min) {
        return random.nextInt(max) + min;
    }

    private void setRandomBounce(int index) {
        int tilesOutOfRange[] = new int[Constants.TilesNumber];
        int j = 0;

        for (int i = tiles.size() - 1; i >= 0; --i) {
            if (tiles.get(i).gety() < 0 && !tiles.get(i).getWithBounce()) {
                tilesOutOfRange[j] = i;
                ++j;
            }
        }

        for (j = 0; j < 15; ++j) {
            if (tilesOutOfRange[j] == 0) break;
        }

        if(j == 0) return;
        int rand = random.nextInt(j);
        int newPosition = tilesOutOfRange[rand];

        bounces.get(index).setPosition(tiles.get(newPosition).getx(), tiles.get(newPosition).gety() - bounces.get(index).getHeight());
        tiles.get(newPosition).setWithBounce(true);
    }

    @Override
    public void update(long time) {
        player.update(time);


        if (!player.getFall()) {
            jumpFromTile();
            jumpFromBounce();
        }

        moveMap();
        checkGameOver();
    }

    private void jumpFromTile() {
        if (player.getDown()) player.setDownY(nearestDownTileY());
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
                player.setMaxJump(player.getMaxJump() * 4);
                player.setJumpedFromBounce(true);
                jumpedBounce.setUp(true);

                longJump = false;
            }
        }
        stopLongJump();
    }

    private void stopLongJump() {
        if (player.isJumpedFromBounce() && player.getDown()) {
            heightCount += player.getMaxJump();
            heightFromStart += player.getMaxJump();

            player.setDy(player.getdy() / 2);
            player.setMaxJump(player.getMaxJump() / 4);
            player.setJumpedFromBounce(false);
        }
    }

    private void setCount() {
        setCountInFirstJump();

        if (prevJumpedTile != null) {
            if (shortJump()) {
                heightFromStart += prevJumpedTile.gety() - currNearestTile.gety();
                heightCount = maxCount(heightCount, heightFromStart);
            }
        }
    }

    private void setCountInFirstJump() {
        if (prevJumpedTile == null) {
            if (player.getDown())
                heightCount = heightFromStart = Constants.basicJumpHeight + GamePanel.HEIGHT - Constants.firstTileY;
        }
    }

    private boolean shortJump() {
        return prevJumpedTile.gety() - currNearestTile.gety() > -Constants.basicJumpHeight;
    }

    private int maxCount(int first, int sec) {
        return (first > sec) ? first : sec;
    }

    private double nearestDownTileY() {
        nearestTileY = GamePanel.HEIGHT + 20;

        for (int i = tiles.size() - 1; i >= 0; --i)
            if (player.intersects(tiles.get(i))) {
                ;
                prevJumpedTile = currNearestTile;
                currNearestTile = tiles.get(i);
                setCount();

                return tiles.get(i).gety();
            }
        return nearestTileY;
    }

    private void moveMap() {
        if (playerIntersectsTile()) offset = overLimitHeight();
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

    private double overLimitHeight() {
        double currentY = player.gety();
        if (currentY - playerHeightLimit < player.getMaxJump())
            return playerHeightLimit + player.getMaxJump() - currentY;

        return 0;
    }

    private void moveTiles() {
        for (int i = 0; i < tiles.size(); ++i) {
            tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy()*player.getSpeed());
            if (tiles.get(i).gety() > GamePanel.HEIGHT) {
                tiles.get(i).setWithBounce(false);
                setRandomTile(i);
            }
        }
    }

    private void moveBounces() {
        for (int i = 0; i < bounces.size(); ++i) {
            bounces.get(i).setPosition(bounces.get(i).getx(), bounces.get(i).gety() + player.getdy()*player.getSpeed());
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

            if (player.getBoundsDown() > GamePanel.HEIGHT / 2)
                changeState = true;//gameController.loadState(GameController.GAMEOVER);
        }
    }

    @Override
    public boolean changeState() {
        return changeState;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector<Tiles> getTiles() {
        return tiles;
    }

    public Vector<Bounce> getBounces() {
        return bounces;
    }

    public static int getCount() {
        return heightCount;
    }

    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(true);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(true);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(false);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(false);
    }
}
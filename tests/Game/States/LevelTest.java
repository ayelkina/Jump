package Game.States;

import Game.GameManagement.Constants;
import Game.GameManagement.GamePanel;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    private long time = 10000000;
    private double ticks = Constants.basicJumpHeight / Constants.GRID;

    @Test
    public void firstJumpPlayerIntersectsTileRight(){
        boolean intersects = false;
        Level level = new Level();
        level.getTiles().get(0).setPosition(Constants.minFirstTileX, Constants.firstTileY);

        for(int i =0; i<ticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(0)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void firstJumpPlayerIntersectsTileLeft() {
        boolean intersects = false;

        Level level = new Level();
        level.getTiles().get(0).setPosition(Constants.TileWidth + Constants.minFirstTileX, Constants.firstTileY);

        for(int i =0; i<ticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(0)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMinDistanceUp() {
        Level level = new Level();
        int x = 100;
        int prevY = 700;
        boolean intersects = false;
        int minDistance = prevY - Constants.minTilesDistance;

        level.getTiles().get(0).setPosition(x, prevY);
        level.getTiles().get(1).setPosition(x, minDistance);
        level.getPlayer().setPosition(x, prevY - level.getPlayer().getHeight());
        level.getPlayer().setDownY(prevY);

        for(int i =0; i<ticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceUp() {
        Level level = new Level();
        int x = 100;
        int prevY = 700;
        boolean intersects = false;
        int maxDistance = prevY - Constants.maxTilesDistance;

        level.getTiles().get(0).setPosition(x, prevY);
        level.getTiles().get(1).setPosition(x, maxDistance);
        level.getPlayer().setPosition(x, prevY - level.getPlayer().getHeight());
        level.getPlayer().setDownY(prevY);

        for(int i =0; i<ticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceRight() {
        Level level = new Level();
        int prevX = 0;
        int prevY = 700;
        double doubleticks = ticks*2;
        boolean intersects = false;

        int playerX = prevX + level.getTiles().get(0).getWidth()+ level.getPlayer().getWidth()/3;
        double playerY = prevY - level.getPlayer().getHeight();

        level.getTiles().get(0).setPosition(prevX, prevY);
        level.getTiles().get(1).setPosition(prevX + Constants.TileWidth+ Constants.maxTilesDistance, prevY - Constants.maxTilesDistance);

        level.getPlayer().setPosition(playerX, playerY);
        level.getPlayer().setDownY(prevY);
        level.getPlayer().setRight(true);

        for(int i = 0; i< doubleticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceLeft() {
        Level level = new Level();
        int prevX = 700;
        int prevY = 700;
        double doubleticks = ticks*2;
        boolean intersects = false;

        int playerX = prevX - level.getTiles().get(0).getWidth() - level.getPlayer().getWidth() / 3;
        double playerY = prevY - level.getPlayer().getHeight();

        level.getTiles().get(0).setPosition(prevX, prevY);
        level.getTiles().get(1).setPosition(prevX - Constants.TileWidth - Constants.maxTilesDistance, prevY - Constants.maxTilesDistance);
        level.getPlayer().setPosition(playerX, playerY);
        level.getPlayer().setDownY(prevY);
        level.getPlayer().setLeft(true);

        for (int i = 0; i < doubleticks; ++i) {
            level.update(time);
            if (level.getPlayer().intersects(level.getTiles().get(1))) intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerInTheMiddleOfTheScreen() {
        double x = 100;
        double y = GamePanel.HEIGHT/2 + 100;

        Level level = new Level();
        level.getPlayer().setDownY(y);
        level.getPlayer().setPosition(x,y);

        for (int i = 0; i < ticks; ++i){
            level.update(time);
        }

        assertEquals(GamePanel.HEIGHT/2, level.getPlayer().gety());
    }
}
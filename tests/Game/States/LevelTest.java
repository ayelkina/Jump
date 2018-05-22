package Game.States;

import Game.GameManagement.Constants;

import Game.GameManagement.GamePanel;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    @Test
    public void firstJumpPlayerIntersectsTileRight() throws Exception{
        double ticks = 166;
        boolean intersects = false;
        Level level = new Level();
        level.getTiles().get(0).setPosition(Constants.minFirstTileX, Constants.firstTileY);

        for(int i =0; i<ticks; ++i) {
            level.update(10000000);
            if (level.getPlayer().intersects(level.getTiles().get(0)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void firstJumpPlayerIntersectsTileLeft() throws Exception{
        double ticks = 166;
        boolean intersects = false;

        Level level = new Level();
        level.getTiles().get(0).setPosition(Constants.TileWidth + Constants.minFirstTileX, Constants.firstTileY);

        for(int i =0; i<ticks; ++i) {
            level.update(1000000);
            if (level.getPlayer().intersects(level.getTiles().get(0)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMinDistanceUp() throws Exception{
        Level level = new Level();
        int x = 100;
        int prevY = 700;
        int ticks = 166;
        boolean intersects = false;
        double maxDistance = prevY - Constants.maxTilesDistance;
        int minDistance = prevY - Constants.minTilesDistance;
        double newY = minDistance;

        level.getTiles().get(0).setPosition(x, prevY);
        level.getTiles().get(1).setPosition(x, newY);
        level.getPlayer().setPosition(x, prevY - level.getPlayer().getHeight());
        level.getPlayer().setDownY(prevY);

        for(int i =0; i<ticks; ++i) {
            level.update(1000000);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceUp() throws Exception{
        Level level = new Level();
        int x = 100;
        int prevY = 700;
        int ticks = 200;
        boolean intersects = false;
        int maxDistance = prevY - Constants.maxTilesDistance;

        level.getTiles().get(0).setPosition(x, prevY);
        level.getTiles().get(1).setPosition(x, maxDistance);
        level.getPlayer().setPosition(x, prevY - level.getPlayer().getHeight());
        level.getPlayer().setDownY(prevY);

        for(int i =0; i<ticks; ++i) {
            level.update(1000000);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceRight() throws Exception{
        Level level = new Level();
        int prevX = 0;
        int prevY = 700;
        int ticks = 166*2;
        boolean intersects = false;

        int playerX = prevX + level.getTiles().get(0).getWidth()+ level.getPlayer().getWidth()/3;
        double playerY = prevY - level.getPlayer().getHeight();

        level.getTiles().get(0).setPosition(prevX, prevY);
        level.getTiles().get(1).setPosition(prevX + Constants.TileWidth+ Constants.maxTilesDistance, prevY - Constants.maxTilesDistance);

        level.getPlayer().setPosition(playerX, playerY);
        level.getPlayer().setDownY(prevY);
        level.getPlayer().setRight(true);

        for(int i =0; i<ticks; ++i) {
            level.update(1000000);
            if (level.getPlayer().intersects(level.getTiles().get(1)))
                intersects = true;
        }

        assertTrue(intersects);
    }

    @Test
    public void playerAchieveTileMaxDistanceLeft() throws Exception {
        Level level = new Level();
        int prevX = 700;
        int prevY = 700;
        int ticks = 166 * 2;
        boolean intersects = false;

        int playerX = prevX - level.getTiles().get(0).getWidth() - level.getPlayer().getWidth() / 3;
        double playerY = prevY - level.getPlayer().getHeight();

        level.getTiles().get(0).setPosition(prevX, prevY);
        level.getTiles().get(1).setPosition(prevX - Constants.TileWidth - Constants.maxTilesDistance, prevY - Constants.maxTilesDistance);

        level.getPlayer().setPosition(playerX, playerY);
        level.getPlayer().setDownY(prevY);
        level.getPlayer().setLeft(true);

        for (int i = 0; i < ticks; ++i) {
            level.update(10000000);
            if (level.getPlayer().intersects(level.getTiles().get(1))) intersects = true;
        }

        assertTrue(intersects);
    }
}
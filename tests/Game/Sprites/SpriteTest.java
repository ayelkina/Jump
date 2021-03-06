package Game.Sprites;

import Game.GameManagement.Constants;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {
    @Test
    public void intersect() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100, 700);
        tile.setPosition(player.getx(), player.gety()+player.getHeight());

        assertTrue(player.intersects(tile));
    }

    @Test
    public void intersectXLeftTrue() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(player.getBoundsRight() - player.width/3-0.1, 100);

        assertTrue(player.intersectsX(tile));
    }

    @Test
    public void intersectXRightTrue() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(player.x + player.width/3 - tile.width +0.1, 100);

        assertTrue(player.intersectsX(tile));
    }

    @Test
    public void intersectXLeftFalse() throws Exception {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(player.getBoundsRight() - player.width/3, 100);

        assertFalse(player.intersectsX(tile));
    }

    @Test
    public void intersectXRightFalse() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(player.x + player.width/3 - tile.width, 100);

        assertFalse(player.intersectsX(tile));
    }

    @Test
    public void intersectYUpTrue() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(100, player.getBoundsDown() - Constants.GRID/2);

        assertTrue(player.intersectsY(tile));
    }

    @Test
    public void intersectYUpFalse() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(100, player.getBoundsDown() - Constants.GRID/2-0.1);

        assertFalse(player.intersectsY(tile));
    }

    @Test
    public void intersectYDownTrue() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(100, player.getBoundsDown() + Constants.GRID/2-0.1);

        assertTrue(player.intersectsY(tile));
    }

    @Test
    public void intersectYDownFalse() {
        Player player = new Player();
        Tiles tile = new Tiles();

        player.setPosition(100,100);
        tile.setPosition(100, player.getBoundsDown() + Constants.GRID/2);

        assertFalse(player.intersectsY(tile));
    }

}
package Game.Sprites;

import Game.GameManagement.Constants;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void playerUp() {
        Player player = new Player();
        int dy = 1;
        int x = 100;
        int y = 100;

        player.setPosition(x,y);
        player.setDy(dy);
        int ticks = 3;

        for(int i =0; i<ticks; ++i) {
            player.update(10000000);
        }
        assertEquals(y - ticks * dy*Constants.GRID, player.y);
        assertTrue(player.getUp());
    }

    @Test
    public void goesDownAfterReachedMaxJump() {
        Player player = new Player();
        int dy = 1;
        int downY = 100;
        double maxJump = 10*Constants.GRID;
        int ticks = 10+1;

        player.setMaxJump(maxJump);
        player.setDy(dy);
        player.setDownY(downY);
        player.setPosition(100,downY-player.getHeight());

        for(int i =0; i < ticks; ++i)
            player.update(10000000);

        assertEquals(downY - (maxJump- Constants.GRID), player.getBoundsDown());
        assertTrue(player.getDown());
    }


}
package Game.Sprites;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void playerUp() {
        Player player = new Player();
        int dy = 2;
        int x = 100;
        int y = 100;

        player.setPosition(x,y);
        player.setDy(dy);
        int ticks = 3;

        for(int i =0; i<ticks; ++i) {
            player.update(1000000);
        }
        assertEquals(y - ticks * dy, player.y);
        assertTrue(player.getUp());
    }

    @Test
    public void goesDownAfterReachedMaxJump() {
        Player player = new Player();
        int dy = 10;
        int downY = 100;
        int maxJump = 50;
        int ticks = maxJump/dy+1;

        player.setMaxJump(maxJump);
        player.setDy(dy);
        player.setDownY(downY);
        player.setPosition(100,downY-player.getBoundsDown());

        for(int i =0; i < ticks; ++i)
            player.update(1000000);

        assertEquals(100 - (maxJump-dy), player.getBoundsDown());
        assertTrue(player.getDown());
    }


}
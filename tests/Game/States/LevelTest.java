package Game.States;


import Game.GameManagement.GamePanel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    //RANDOM!!!!
    @Test
    public void firstJumpPlayerIntersectsTile() throws Exception{
        Level level = new Level();

        if(level.getPlayer().getBoundsDown() < GamePanel.HEIGHT)
         assertTrue(level.getPlayer().intersects(level.getTiles().get(0)));
    }


}
package Game.GameManagement.Vars;

import Game.States.Level;

public class LevelVariables extends Variables {

    private PlayerVariables playerVariables;
    private BounceVariables bounceVariables;
    private Level level;

    public LevelVariables(Level level) {
        this.level = level;

        playerVariables = new PlayerVariables(level);
        bounceVariables = new BounceVariables(level);
    }

    public PlayerVariables getPlayerVariables() {
        return playerVariables;
    }

    public BounceVariables getBounceVariables() {
        return bounceVariables;
    }
}

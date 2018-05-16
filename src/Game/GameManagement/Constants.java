package Game.GameManagement;

public class Constants {

    public static final double GRID = 1.2;

    public static final int PlayerWidth = 70;
    public static final int PlayerHeight = 63;
    public static final int basicJumpHeight = GamePanel.HEIGHT/4;

    public static final int BounceDownHeight = 25;
    public static final int BounceUpHeight = 65;
    public static final int BounceWidth = 70;
    public static final int BounceQuantity = 3;

    public static final int TileHeight = 25;
    public static final int TileWidth = 100;
    public static final int TilesQuantity = 15;
    public static final int maxTileX = GamePanel.WIDTH - TileWidth;
    public static final int maxTilesDistance = basicJumpHeight *3/4;
    public static final int minTilesDistance = basicJumpHeight /4;
    public static final int minFirstTileX = GamePanel.WIDTH/2 - TileWidth;
    public static final int halfBasicJump = GamePanel.HEIGHT - Constants.basicJumpHeight/2;
}

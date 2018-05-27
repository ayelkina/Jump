package Game.GameManagement;

public class Constants {

    public static final double GRID = 4;

    public static final int PlayerSetWidth = 350;
    public static final int PlayerImageWidth = PlayerSetWidth/5;
    public static final int PlayerImageHeight = 63;

    public static final int PlayerHeight = GamePanel.WIDTH/10;
    public static final int PlayerWidth = PlayerHeight*7/6;
    public static final int PlayermiddleOfPanel = GamePanel.WIDTH/2 - PlayerWidth /2;
    public static final int PlayerBeginY = GamePanel.HEIGHT - PlayerHeight;
    public static final int basicJumpHeight = GamePanel.HEIGHT/4;

    public static final int BounceImageWidth = 70;
    public static final int BounceImageDownHeight = 15;
    public static final int BounceImageUpHeight = 35;

    public static final int BounceWidth =  GamePanel.WIDTH*7/60;
    public static final int BounceUpHeight = BounceWidth/2;
    public static final int BounceDownHeight = BounceUpHeight*3/7;
    public static final int BouncesNumber = 2;

    public static final int TileImageHeight = 25;
    public static final int TileImageWidth = 100;

    public static final int TileHeight = GamePanel.HEIGHT/32;
    public static final int TileWidth = GamePanel.WIDTH/6;
    public static final int TilesNumber = 13;
    public static final int maxTilesDistance = basicJumpHeight *3/4;
    public static final int minTilesDistance = basicJumpHeight /4;
    public static final int minFirstTileX = GamePanel.WIDTH/2 - TileWidth;
    public static final int firstTileY = GamePanel.HEIGHT - Constants.basicJumpHeight/2;
}

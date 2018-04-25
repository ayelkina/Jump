package Game;

public class Level extends GameState{

    private Player player;
    private Background background;

    public Level(){
        player = new Player();
        player.setPosition(Game.WIDTH/2 - player.width/2, Game.HEIGHT - player.height);
    }
}

package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Level extends GameState{

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;

    public Level(){
        background = new Background("/Pics/Sky.png");
        player = new Player();

        tiles = new Vector();
        for(int i = 0; i<=2; ++i)
            tiles.addElement(new Tiles());


        player.setPosition(GamePanel.WIDTH/2 - player.SpriteWidth /2, GamePanel.HEIGHT - player.SpriteHeight);
        tiles.get(0).setPosition(100,300);
        tiles.get(1).setPosition(250,700);
        tiles.get(2).setPosition(300,200);
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for(int i = 0; i<=2; ++i)
          tiles.get(i).draw(graph);

        player.draw(graph);

        graph.dispose();
    }

    public void update() {
        player.update();

        if (player.getState() == Player.PlayerState.DOWN &&
                player.gety() - tiles.get(1).gety() - tiles.get(1).getSpriteHeight() <= 1){
            player.setDownY(tiles.get(1).gety()- tiles.get(1).getSpriteHeight());
            System.out.println(tiles.get(1).gety());
        }
//        System.out.println( "player.gety() " + player.gety());
//        System.out.println( "tiles " + (tiles.get(1).gety() - tiles.get(1).getSpriteHeight()));
    }

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(true);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(true);
    }

    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(false);
        if (key.getKeyCode() == KeyEvent.VK_LEFT) player.setLeft(false);
    }

}

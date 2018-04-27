package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Level extends GameState{

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;
    boolean toMoveTiles = false;

    public Level(){
        background = new Background("/Pics/Sky.png");
        player = new Player();

        tiles = new Vector();
        for(int i = 0; i<=4; ++i)
            tiles.addElement(new Tiles());

        player.setPosition(GamePanel.WIDTH/2 - player.width /2, GamePanel.HEIGHT - player.height);
        tiles.get(4).setPosition(150,650);
        tiles.get(2).setPosition(250,450);
        tiles.get(3).setPosition(300,550);
        tiles.get(1).setPosition(150,250);
        tiles.get(0).setPosition(350,200);
    }

    public void draw(Graphics2D graph) {
        background.draw(graph);

        for(int i = 0; i< tiles.size(); ++i)
          tiles.get(i).draw(graph);

        player.draw(graph);
        graph.dispose();
    }

    public void update() {
        player.update();
        jumpFromTile();
    }

    public void jumpFromTile(){
        player.downYPrev = player.getDownY();
        if (player.getState() == Player.PlayerState.DOWN) {

            for (int i = 0; i < tiles.size(); ++i)
                if (player.intersects(tiles.get(i)) && tiles.get(i) == nearestDownTile()) {
                    toMoveTiles = true;
                    
                    System.out.println(player.getDownY() - tiles.get(i).gety());
                    player.setDownY(tiles.get(i).gety());
                    if(player.downYPrev == player.getDownY()) toMoveTiles = false; //NIE ZNAU KAK DOBAWIC USLOWIE CZTOBY WSE TILESY NIE ZDWIGALIS

                } else if (player.distanceBetweenY(nearestDownTile()) > 10) player.setDownY(800); //ZMIENIC 10!!!!!! bo bez sensu
        }


        if(player.getState() == Player.PlayerState.UP && toMoveTiles) moveTiles();
    }

    public Sprite nearestDownTile(){
        Sprite nearestTile = tiles.get(0);
        double minDistance = 200;

        for (int i = 0; i < tiles.size(); ++i)
            if(player.distanceBetweenY(tiles.get(i)) < minDistance && player.intersectsX(tiles.get(i))) {
                minDistance = player.distanceBetweenY(tiles.get(i));
                nearestTile = tiles.get(i);
            }

        return nearestTile;
    }

    public void moveTiles(){
        for (int i = 0; i < tiles.size(); ++i){
            tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + 3);
        }
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

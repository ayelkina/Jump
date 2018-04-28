package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

public class Level extends GameState{

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;
    final Random random;

    private boolean toMoveTiles;


    public Level(){
        random = new Random();

        background = new Background("/Pics/sky1.png" );

        player = new Player();
        tiles = new Vector<Tiles>();
        for(int i = 0; i<=13; ++i)
            tiles.addElement(new Tiles());

        setTilesPositions();
        toMoveTiles = false;
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
        for(int i = 0; i< tiles.size(); ++i)
            tiles.get(i).update();
        jumpFromTile();
    }

    public void jumpFromTile(){
        player.downYPrev = player.getDownY();
        if (player.getState() == Player.PlayerState.DOWN) {

            for (int i = 0; i < tiles.size(); ++i)
                if (player.intersects(tiles.get(i)) && tiles.get(i) == nearestDownTile()) {
                    toMoveTiles = true;

//                    System.out.println(player.getDownY() - tiles.get(i).gety());
                    player.setDownY(tiles.get(i).gety());
                    if(player.downYPrev - player.getDownY() > 0) toMoveTiles = false; //NIE ZNAU KAK DOBAWIC USLOWIE CZTOBY WSE TILESY NIE ZDWIGALIS

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

    public void setTilesPositions(){
        tiles.get(0).setPosition(random(300, 100), 750);
        int j= 0;
        for(int i = 1; i< tiles.size(); ++i, ++j) {
            int prevY = tiles.get(j).gety();

            int newY = random(80, prevY - 130);
            int newX = random(500, 0);

            tiles.get(i).setPosition(newX, newY);
        }
    }

    public int random(int bound, int min){
        return random.nextInt(bound) + min;
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

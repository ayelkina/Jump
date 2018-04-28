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

    private int offTime;
    private int offset;

    public Level(){
        random = new Random();;
        background = new Background("/Pics/sky1.png" );
        player = new Player();
        tiles = new Vector<Tiles>();
        for(int i = 0; i<=10; ++i)
            tiles.addElement(new Tiles());

        setTilesPositions();
        offTime = 0;
    }

    public void setTilesPositions(){
        tiles.get(0).setPosition(random(300, 100), 700);
        int j= 0;
        for(int i = 1; i< tiles.size(); ++i, ++j) {
            int prevY = tiles.get(j).gety();

            int newX = random(500, 0);
            int newY = random(100, prevY - 150);

            tiles.get(i).setPosition(newX, newY);
        }
    }

    public int random(int bound, int min){
        return random.nextInt(bound) + min;
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
        moveTiles();
    }

    public void jumpFromTile(){
        if (player.getState() == Player.PlayerState.DOWN) {
            player.downYPrev = player.getDownY();
                 player.setDownY(nearestDownTile());
        }
    }

    public int nearestDownTile(){
       int  nearestTile = GamePanel.HEIGHT;
        for (int i = tiles.size()-1; i >= 0; --i)
            if(player.distanceFromY(tiles.get(i)) < -player.getdy() &&
                    player.distanceFromY(tiles.get(i)) > player.getdy() &&
                    player.intersectsX(tiles.get(i))) {
                return tiles.get(i).gety();
            }

        return nearestTile;
    }

    public void moveTiles(){
        if(player.getBoundsDown() - player.getDownY() > 0) offset =  offset();
        if (offset == 0) return;

        if(player.gety() < GamePanel.HEIGHT/2){
            ++offTime;
            if (offTime < offset/player.getdy()){
                player.setPosition(player.getx(), GamePanel.HEIGHT/2);

                for (int i = 0; i < tiles.size(); ++i) {
                    tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
                }
                player.setDown();
                player.setDownY(nearestDownTile());
            }
            else offTime = 0;
        }
    }

    public int offset(){
        int currentY = player.gety();
        if(currentY - GamePanel.HEIGHT/2 < 200)
            return 600 - currentY;

        return 0;
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

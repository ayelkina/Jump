package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

import static java.lang.Math.abs;

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


        player.setPosition(GamePanel.WIDTH/2 - player.width /2, GamePanel.HEIGHT - player.height);
        tiles.get(0).setPosition(100,600);
        tiles.get(1).setPosition(250,350);
        tiles.get(2).setPosition(300,450);
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
        checkCollisionWithTiles();
    }

    public boolean intersectsX (Sprite s1, Sprite s2){
        if (s1.getBoundsRight() - s1.getWidth()/3 > s2.getx() &&
                s1.getx() + s1.getWidth()/3 < s2.getBoundsRight()) return true;
        return false;
    }

    public boolean intersectsY (Sprite s1, Sprite s2){
        if (s1.gety() < s2.getBoundsTop()) return true;
        return false;
    }

    public boolean intersects(Sprite s1, Sprite s2){
        if (intersectsX(s1, s2) && intersectsY(s1,s2)) return true;
        return false;
    }

    public void checkCollisionWithTiles(){
        if (player.getState() == Player.PlayerState.DOWN)
            for (int i = 0; i < tiles.size(); ++i)
                if (intersects(player, tiles.get(i)) && tiles.get(i) == nearestDownTile()) {
                    player.setDownY(tiles.get(i).getBoundsTop());
                } else if (distanceBetweenY(player, nearestDownTile()) > 100) player.setDownY(700);
    }

    public Sprite nearestDownTile(){
        Sprite nearestTile = tiles.get(0);
        double minDistance = 200;
        for (int i = 0; i < tiles.size(); ++i)
            if(distanceBetweenY(player, tiles.get(i))< minDistance && intersectsX(player, tiles.get(i))) {
                minDistance = distanceBetweenY(player, tiles.get(i));
                nearestTile = tiles.get(i);
            }
        return nearestTile;
    }

    public double distanceBetweenY (Sprite s1, Sprite s2){
        return abs(s1.y - s2.getBoundsTop());
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

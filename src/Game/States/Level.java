package Game.States;

import Game.Player;
import Game.Background;
import Game.Tiles;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;


public class Level extends GameState{

    private Player player;
    private Background background;
    private Vector<Tiles> tiles;
    private final Random random;
    private GameState gameState;

    private double offTime;
    private int offset;

    public Level(GameState gameState){

        this.gameState = gameState;

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
            setRandomPosition(i);
        }
    }

    public void setRandomPosition(int cur){
        int prev;

        if(cur == 0) prev = tiles.size() -1;
        else prev = cur-1;

        int prevY = tiles.get(prev).gety();
        int prevX = tiles.get(prev).getx();

        int newX = random(500, 0);
        int newY = random(100, prevY - 150);

        tiles.get(cur).setPosition(newX, newY);
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

        if(!player.getFall()) {
            jumpFromTile();

        }
        moveTiles();
        checkGameover();
    }

    public void jumpFromTile(){
        if (player.getState() == Player.PlayerState.DOWN) {
            player.downYPrev = player.getDownY();
            player.setDownY(nearestDownTile());
        }
    }

    public int nearestDownTile(){
        int  nearestTile = GamePanel.HEIGHT + 20;

        for (int i = tiles.size()-1; i >= 0; --i)
            if(abs(player.distanceFromY(tiles.get(i))) < abs(player.getdy()) &&
                    player.intersectsX(tiles.get(i))) {
                return tiles.get(i).gety();
            }
        return nearestTile;
    }

    public void moveTiles(){
        if(player.getBoundsDown() - player.getDownY() > 0) offset =  offset();
        if (offset == 0) return;

        if(player.gety() <= GamePanel.maxPlayerHeight){
            ++offTime;

            if (offTime < offset/player.getdy()){
                player.setPosition(player.getx(), GamePanel.maxPlayerHeight);

                for (int i = 0; i < tiles.size(); ++i) {
                    tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
                    if(tiles.get(i).gety() > GamePanel.HEIGHT) setRandomPosition(i);
                }

                player.setDownY(nearestDownTile());
                player.setDown();
            }

            else// (offTime >= offset/player.getdy()){
            {
                offTime = 0;
            }
        }
    }

    public int offset(){
        int currentY = player.gety();
        if(currentY - GamePanel.maxPlayerHeight < 200)
            return GamePanel.maxPlayerHeight + 200 - currentY;

        return 0;
    }

    public void checkGameover(){

        if (player.getFall()){

            player.sety(player.gety() - player.getdy());

            for (int i = 0; i < tiles.size(); ++i) {
                tiles.get(i).setPosition(tiles.get(i).getx(), tiles.get(i).gety() + player.getdy());
            }

            if(tiles.get(0).gety() <=0)
                gameState.loadState(State.GAMEOVER);
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
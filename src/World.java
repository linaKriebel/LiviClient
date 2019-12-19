import models.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;

    public List<GameItem> players = new ArrayList<>();
    public List<GameItem> balls = new ArrayList<>();
    public List<GameItem> obstacles = new ArrayList<>();
    public List<GameItem> holes = new ArrayList<>();

    public World(){
        GameItem ai = new GameItem(new Field(1,1), 0, Color.CYAN);
        GameItem player1 = new GameItem(new Field(2,2), 1, Color.MAGENTA);
        GameItem player2 = new GameItem(new Field(6,6), 2, Color.YELLOW);

        players.add(ai);
        players.add(player1);
        players.add(player2);

        GameItem ball1 = new GameItem(new Field(7, 8), 1, Color.WHITE);
        GameItem ball2 = new GameItem(new Field(3, 3), 2, Color.WHITE);

        balls.add(ball1);
        balls.add(ball2);

        GameItem hole1 = new GameItem(new Field(10,10), 1, Color.GREEN);
        GameItem hole2 = new GameItem(new Field(15,15), 1, Color.GREEN);

        holes.add(hole1);
        holes.add(hole2);

        int idCount = 0;
        for(int x=0; x<WIDTH; x++){
            GameItem obstacle1 = new GameItem(new Field(x, 0), idCount, Color.darkGray);
            idCount++;
            GameItem obstacle2 = new GameItem(new Field(x, HEIGHT-1), idCount, Color.darkGray);
            idCount++;

            obstacles.add(obstacle1);
            obstacles.add(obstacle2);
        }
        for(int y=0; y<HEIGHT; y++){
            GameItem obstacle1 = new GameItem(new Field(0, y), idCount, Color.darkGray);
            idCount++;
            GameItem obstacle2 = new GameItem(new Field(WIDTH-1, y), idCount, Color.darkGray);
            idCount++;

            obstacles.add(obstacle1);
            obstacles.add(obstacle2);
        }

        GameItem obstacle1 = new GameItem(new Field(5, 5), idCount, Color.darkGray);
        obstacles.add(obstacle1);
    }

    public void setPlayerCoordinates(int id, Field position){
        for (GameItem i : players) {
            if (i.getId() == id) {
                i.setCoordinates(position);
            }
        }
    }

    public GameItem getPlayer(int id) {
        for (GameItem i : players) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void setBallCoordinates(int id, Field position){
        for (GameItem i : balls) {
            if (i.getId() == id) {
                i.setCoordinates(position);
            }
        }
    }

    public void removeBall(int id) {
        GameItem removeBall = null;
        for (GameItem i : balls) {
            if (i.getId() == id) {
                removeBall = i;
            }
        }
        balls.remove(removeBall);
    }
}

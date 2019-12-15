import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public List<GameItem> players = new ArrayList<>();
    public List<GameItem> balls = new ArrayList<>();
    public List<GameItem> obstacles = new ArrayList<>();

    public World(){

        GameItem ai = new GameItem(new Coordinate(0,0), 0, Color.CYAN);
        GameItem player1 = new GameItem(new Coordinate(2,2), 1, Color.RED);
        GameItem player2 = new GameItem(new Coordinate(6,6), 2, Color.BLUE);

        players.add(ai);
        players.add(player1);
        players.add(player2);

        GameItem ball1 = new GameItem(new Coordinate(7, 8), 1, Color.BLACK);
        GameItem ball2 = new GameItem(new Coordinate(3, 3), 2, Color.BLACK);

        balls.add(ball1);
        balls.add(ball2);

        GameItem obstacle1 = new GameItem(new Coordinate(5, 5), 1, Color.darkGray);

        obstacles.add(obstacle1);
    }

    public Coordinate getPlayerPosition(int id){
        for (GameItem i : players) {
            if (i.getId() == id) {
                return i.getCoordinates();
            }
        }
        return null;
    }

    public void setPlayerCoordinates(int id, Coordinate position){
        for (GameItem i : players) {
            if (i.getId() == id) {
                i.setCoordinates(position);
            }
        }
    }

    public void setBallCoordinates(int id, Coordinate position){
        for (GameItem i : balls) {
            if (i.getId() == id) {
                i.setCoordinates(position);
            }
        }
    }
}

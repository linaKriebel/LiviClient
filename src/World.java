import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public Coordinate playerPosition = new Coordinate(100, 100);
    public Coordinate player2Position = new Coordinate(100, 100);
    public List<GameItem> players = new ArrayList<>();
    private List<GameItem> balls = new ArrayList<>();

    public World(){
        GameItem player1 = new GameItem(new Coordinate(2,2), 1, Color.RED);
        GameItem player2 = new GameItem(new Coordinate(6,6), 2, Color.BLUE);
        players.add(player1);
        players.add(player2);
        GameItem ball = new GameItem(new Coordinate(7, 8), 1, Color.BLACK);
        balls.add(ball);
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
}

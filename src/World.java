import models.Field;
import models.GameItem;

import java.util.ArrayList;
import java.util.List;

public class World {

    public List<GameItem> players = new ArrayList<>();
    public List<GameItem> balls = new ArrayList<>();
    public List<GameItem> obstacles = new ArrayList<>();
    public List<GameItem> holes = new ArrayList<>();

    public World(){

    }

    public void setUp(List<GameItem> players, List<GameItem> balls, List<GameItem> obstacles, List<GameItem> holes){
        this.players = players;
        this.balls = balls;
        this.obstacles = obstacles;
        this.holes = holes;
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

    public void removePlayer(int id) {
        GameItem removePlayer = null;
        for (GameItem i : players) {
            if (i.getId() == id) {
                removePlayer = i;
            }
        }
        players.remove(removePlayer);
    }
}

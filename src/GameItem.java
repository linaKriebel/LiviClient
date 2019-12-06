import java.awt.*;

public class GameItem {

    private Coordinate coordinates;
    private int id;
    private Color color;


    public GameItem(Coordinate coordinates, int id, Color color) {
        this.coordinates = coordinates;
        this.id = id;
        this.color = color;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}


import models.Field;

import java.awt.*;

public class GameItem {

    private Field coordinates;
    private int id;
    private Color color;
    private int score;


    public GameItem(Field coordinates, int id, Color color) {
        this.coordinates = coordinates;
        this.id = id;
        this.color = color;
        this.score = 0;
    }

    public Field getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Field coordinates) {
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

    public int getScore() {
        return score;
    }

    public void score() {
        this.score += 1;
    }
}


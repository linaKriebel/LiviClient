
import models.GameItem;

import javax.swing.*;
import java.awt.*;

/**
 * draws game items
 */
public class WorldPanel extends JPanel {

    private World world;

    public WorldPanel(World world){
        this.world = world;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));

        for (GameItem player : world.players) {
            g.setColor(player.getColor());
            if(player.getId() != 0) g.drawString("Player " + player.getId() + " : " + player.getScore(), 450, player.getId() * 20);
            g.fillOval(player.getCoordinates().x * 30, player.getCoordinates().y * 30, 30, 30);
        }
        for (GameItem ball : world.balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getCoordinates().x * 30, ball.getCoordinates().y * 30, 30, 30);
        }
        for (GameItem obstacle : world.obstacles) {
            g.setColor(obstacle.getColor());
            g.drawRect(obstacle.getCoordinates().x * 30, obstacle.getCoordinates().y * 30, 30, 30);
        }
        for (GameItem holes : world.holes) {
            g.setColor(holes.getColor());
            g.drawOval(holes.getCoordinates().x * 30, holes.getCoordinates().y * 30, 30, 30);
        }
    }
}

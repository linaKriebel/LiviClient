import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    private World world;

    public WorldPanel(World world){
        this.world = world;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //paint players
        g.setColor(Color.RED);
        g.fillOval(world.playerPosition.x,world.playerPosition.y, 10, 10);

        //paint obstacles
        g.setColor(Color.BLACK);
        for(int i=0; i < world.obstacles.length; i++){
            Coordinate obstacle = world.obstacles[i];
            g.fillRect(obstacle.x, obstacle.y, 30, 30);
        }
    }
}

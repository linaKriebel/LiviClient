
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

        for (GameItem player : world.players) {
            g.setColor(player.getColor());
            g.fillOval(player.getCoordinates().x * 10, player.getCoordinates().y * 10, 10, 10);
        }

    }
}

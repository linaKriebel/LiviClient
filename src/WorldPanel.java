import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    private WorldPlaceholder world;

    public WorldPanel(WorldPlaceholder world){
        this.world = world;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //paint players
        g.setColor(Color.RED);
        g.fillOval(world.x,world.y, 10, 10);
    }
}

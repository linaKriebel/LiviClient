import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(final Graphics graphics){
        super.paintComponent(graphics);

        Dimension size = getSize();

        int x = 0;
        int y = 0;
        int i = 0;
        while (x < size.width && y < size.height) {
            graphics.setColor(i % 2 == 0 ? Color.RED : Color.WHITE);
            graphics.fillOval(x, y, size.width - (2 * x), size.height - (2 * y));
            x += 10;
            y += 10;
            i++;
        }
    }
}

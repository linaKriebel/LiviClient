import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    private WorldPlaceholder world;
    private JFrame frame;

    public InputManager(WorldPlaceholder world, JFrame frame){
        this.world = world;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            world.x += 10;
            frame.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            world.x -= 10;
            frame.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            world.y -= 10;
            frame.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            world.y += 10;
            frame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

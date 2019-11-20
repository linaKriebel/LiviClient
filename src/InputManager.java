import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    private Client client;

    public InputManager(Client client){
        this.client = client;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            client.processMove("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            client.processMove("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            client.processMove("up");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            client.processMove("down");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

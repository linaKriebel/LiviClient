import models.ClientCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * handles player input
 */
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
            client.sendMessageToServer(ClientCommand.RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            client.sendMessageToServer(ClientCommand.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            client.sendMessageToServer(ClientCommand.UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            client.sendMessageToServer(ClientCommand.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

import models.GameEvent;

import java.io.*;
import java.net.Socket;

public class MessageListener implements Runnable{

    private Socket socket;
    private Client client;
    private ObjectInputStream inputStream;

    public MessageListener(Socket socket, Client client) throws IOException {
        this.client = client;
        this.socket = socket;
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            //Get the return message from the server
            try {
                GameEvent event = (GameEvent) inputStream.readObject();
                switch (event.getCommand()) {
                    case START:
                        client.getGui().startGame();
                    case MOVE:
                        client.processMovement(event.getField(), event.getItemId(), event.getItemType());
                        break;
                    case REMOVE:
                        client.getWorld().removeBall(event.getItemId());
                        break;
                    case SCORE:
                        client.getWorld().getPlayer(event.getItemId()).score();
                        break;
                }
                client.getGui().repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

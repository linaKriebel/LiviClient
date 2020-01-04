import models.GameEvent;
import models.GameItem;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageListener implements Runnable {

    private Socket socket;
    private Client client;
    private ObjectInputStream inputStream;

    private boolean isStartMessage = false;
    private int count = 0;

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
                System.out.println("Message from server: " + event.getCommand());
                switch (event.getCommand()) {
                    case START:
                        //TODO fix damn OptionalDataException!!!
                        //client.getWorld().setUp(event.getPlayers(), event.getBalls(), event.getObstacles(), event.getHoles());
                        client.getGui().startGame();
                    case MOVE:
                        client.processMovement(event.getItemType(), event.getItemId(), event.getField());
                        break;
                    case REMOVE:
                        client.getWorld().removeBall(event.getItemId());
                        break;
                    case SCORE:
                        client.getWorld().getPlayer(event.getItemId()).score();
                        break;
                    case EXIT:
                        client.getWorld().removePlayer(event.getItemId());
                        break;
                    case END:
                        client.getGui().showFinalResult();
                        break;
                }
                client.getGui().repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

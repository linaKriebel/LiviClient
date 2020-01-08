import models.GameEvent;

import java.io.*;
import java.net.Socket;

public class MessageListener implements Runnable {

    private Socket socket;
    private Client client;
    private ObjectInputStream inputStream;
    private boolean gameEnded = false;

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
                    case REGISTER:
                        if(client.id == 0) client.id = event.getItemId();
                        client.getGui().updateStartPanel(event.getItemId());
                        break;
                    case START:
                        client.getWorld().setUp(event.getPlayers(), event.getBalls(), event.getObstacles(), event.getHoles());
                        client.getGui().startGame();
                        break;
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
                        if (!gameEnded) client.getGui().showFinalResult();
                        gameEnded = true;
                        break;
                }
                client.getGui().repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

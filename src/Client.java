import models.ClientCommand;
import models.Field;
import models.ItemType;

import java.io.*;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private World world = new World();
    private ObjectOutputStream os;
    private MessageListener messageListener;
    private GUI gui;

    public Client(String host, int port) {
        try {
            gui = new GUI(this, world);
            socket = new Socket(host, port);
            os = new ObjectOutputStream(socket.getOutputStream());

            messageListener = new MessageListener(socket, this);
            Thread thread = new Thread(messageListener);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processMovement(Field position, int number, ItemType type) {
        if (type == ItemType.PLAYER) world.setPlayerCoordinates(number, position);

        if (type == ItemType.BALL) world.setBallCoordinates(number, position);
    }

    public void sendMessageToServer(ClientCommand message) {
        System.out.println(message);
        try {
            os.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public World getWorld() {
        return world;
    }

    public GUI getGui() {
        return gui;
    }
}

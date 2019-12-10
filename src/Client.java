import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private World world = new World();
    private JFrame frame;
    private BufferedWriter bufferedWriter;
    private MessageListener messageListener;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            messageListener = new MessageListener(socket, this);
            Thread thread = new Thread(messageListener);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawFrame() {
        frame = new JFrame();
        WorldPanel panel = new WorldPanel(world);

        frame.addKeyListener(new InputManager(this));
        frame.setTitle("Livi");
        frame.setSize(new Dimension(1000, 500));

        frame.add(panel);

        frame.setVisible(true);
    }

    public void processMove(Coordinate position, int number, ItemType type) {
        if (type == ItemType.PLAYER) world.setPlayerCoordinates(number, position);

        if (type == ItemType.BALL) world.setBallCoordinates(number, position);

        frame.repaint();
    }

    public boolean sendMessageToServer(String sendMessage) {
        //send message to server
        try {
            sendMessage = sendMessage + "\n";
            bufferedWriter.write(sendMessage);
            bufferedWriter.flush();
            System.out.println(sendMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Socket getSocket() {
        return socket;
    }

    public World getWorld() {
        return world;
    }

}

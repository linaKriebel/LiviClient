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
    private BufferedReader bufferedReader;


    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
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

    public void processMove(String direction) {
        if (sendMessageToServer(direction)) {

            switch (direction) {
                case "left":
                    world.playerPosition.x -= 10;
                    break;
                case "right":
                    world.playerPosition.x += 10;
                    break;
                case "up":
                    world.playerPosition.y -= 10;
                    break;
                case "down":
                    world.playerPosition.y += 10;
                    break;
            }

            frame.repaint();
        }
        // else don't paint
    }

    public boolean sendMessageToServer(String sendMessage) {
        //send message to server
        try {
            sendMessage = sendMessage + "\n";
            bufferedWriter.write(sendMessage);
            bufferedWriter.flush();
            System.out.println("Message sent to the server : " + sendMessage);

            //Get the return message from the server
            String receivedMessage = bufferedReader.readLine();
            System.out.println("Message received from the server : " + receivedMessage);

            if (receivedMessage.equals("ok")) {
                return true;
            }
            return false;

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

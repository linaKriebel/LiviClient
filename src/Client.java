import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private World world = new World();
    private JFrame frame;
    private OutputStream outputStream;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();
            osw = new OutputStreamWriter(outputStream);
            bw = new BufferedWriter(osw);
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
        sendMessageToServer(direction);
    }

    public void sendMessageToServer(String sendMessage) {
        //send message to server
        try {
            sendMessage = sendMessage + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : " + sendMessage);

            //Get the return message from the server
            /*InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String receivedMessage = br.readLine();
            System.out.println("Message received from the server : " + receivedMessage);*/
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

}

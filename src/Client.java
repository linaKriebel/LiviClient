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
        JLabel player1Label = new JLabel("Player One: 0");
        JLabel player2Label = new JLabel("Player Two: 0");

        WorldPanel panel = new WorldPanel(world);
        panel.setBackground(Color.BLACK);
        player1Label.setForeground(Color.MAGENTA);
        panel.add(player1Label);
        player2Label.setForeground(Color.YELLOW);
        panel.add(player2Label);

        frame.addKeyListener(new InputManager(this));
        frame.setTitle("Livi");
        frame.setSize(new Dimension(600, 650));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void processMovement(Coordinate position, int number, ItemType type) {
        if (type == ItemType.PLAYER) world.setPlayerCoordinates(number, position);

        if (type == ItemType.BALL) world.setBallCoordinates(number, position);

        frame.repaint();
    }

    public void sendMessageToServer(String message) {
        System.out.println(message);
        message = message + "\n";

        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();

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

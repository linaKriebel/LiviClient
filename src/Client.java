import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void drawFrame(){
        JFrame frame = new JFrame();
        WorldPanel panel = new WorldPanel();
        frame.setTitle("Livi");
        frame.add(panel);
        frame.setVisible(true);
    }

    public Socket getSocket() {
        return socket;
    }

}

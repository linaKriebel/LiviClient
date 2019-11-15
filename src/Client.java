import javax.swing.*;
import java.io.IOException;
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

    public void sendMessage(int message){
        try {
            socket.getOutputStream().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

}

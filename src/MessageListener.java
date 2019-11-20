import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageListener implements Runnable{

    private Socket socket;
    private BufferedReader bufferedReader;
    private Client client;

    public MessageListener(Socket socket, Client client) throws IOException {
        this.client = client;
        this.socket = socket;
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    @Override
    public void run() {
        while (true) {
            //Get the return message from the server
            String receivedMessage = null;
            try {
                receivedMessage = bufferedReader.readLine();
                String[] split = receivedMessage.split(" ");
                client.processMove(split[1], Integer.parseInt(split[0]));
                System.out.println(" messagelistener" + receivedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

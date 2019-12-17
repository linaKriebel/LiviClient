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
                System.out.println("Message from server: " + receivedMessage);

                //TODO handle commands
                String[] split = receivedMessage.split(" ");
                String command = split[0];
                ItemType type = ItemType.valueOf(split[1]);
                int id = Integer.parseInt(split[2]);
                Coordinate coordinate = new Coordinate(Integer.parseInt(split[3]), Integer.parseInt(split[4]));

                client.processMovement(coordinate, id, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

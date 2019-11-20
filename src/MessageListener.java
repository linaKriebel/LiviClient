import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageListener implements Runnable{

    private Socket socket;
    private BufferedReader bufferedReader;

    public MessageListener(Socket socket) throws IOException {
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
                System.out.println(receivedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

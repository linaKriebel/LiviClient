import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Client client = new Client(null, 12345);
            client.drawFrame();
            client.sendMessage(10);
            client.getWorld().x = client.getSocket().getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

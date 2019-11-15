import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Client client = new Client(null, 12345);
            client.sendMessage(10);
            System.out.println(client.getSocket().getInputStream().read());
            client.drawFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

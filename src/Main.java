import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {
            Client client = new Client(null, 12345);

            try {
                //send message to server
                OutputStream os = client.getSocket().getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                String sendMessage = "2" + "\n";
                bw.write(sendMessage);
                bw.flush();
                System.out.println("Message sent to the server : "+ sendMessage);

                //Get the return message from the server
                InputStream is = client.getSocket().getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String receivedMessage = br.readLine();
                System.out.println("Message received from the server : " + receivedMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }

            client.drawFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

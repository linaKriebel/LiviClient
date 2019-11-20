import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private World world = new World();
    private JFrame frame;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawFrame(){
        frame = new JFrame();
        WorldPanel panel = new WorldPanel(world);

        frame.addKeyListener(new InputManager(this));
        frame.setTitle("Livi");
        frame.setSize(new Dimension(1000, 500));

        frame.add(panel);

        frame.setVisible(true);
    }

    public void processMove(String direction){

        switch (direction){
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
        //TODO: send move to server
    }


    public Socket getSocket() {
        return socket;
    }

    public World getWorld() {
        return world;
    }

}

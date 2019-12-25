import models.ClientCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    private Client client;
    private World world;
    private JFrame frame;
    private JPanel root;
    private WorldPanel worldPanel;

    public GUI(Client client, World world) {
        this.client = client;
        this.world = world;
    }

    public void drawFrame() {
        frame = new JFrame();
        frame.setFocusable(false);

        root = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) root.getLayout();

        JPanel startPanel = new JPanel(new GridLayout());
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessageToServer(ClientCommand.START);
            }
        });
        startPanel.add(startButton);

        worldPanel = new WorldPanel(world);
        worldPanel.setBackground(Color.BLACK);
        worldPanel.setFocusable(true);
        worldPanel.setRequestFocusEnabled(true);
        worldPanel.addKeyListener(new InputManager(client));

        root.add(startPanel, "START");
        root.add(worldPanel, "WORLD");

        cardLayout.show(root, "START");

        frame.setTitle("Livi");
        frame.setSize(new Dimension(600, 650));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.add(root);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendMessageToServer(ClientCommand.END);
            }
        });
        frame.setVisible(true);
        worldPanel.requestFocus();
    }

    public void startGame() {
        CardLayout cardLayout = (CardLayout) root.getLayout();
        cardLayout.show(root, "WORLD");
        worldPanel.requestFocusInWindow();
    }

    public void repaint() {
        frame.repaint();
    }

}

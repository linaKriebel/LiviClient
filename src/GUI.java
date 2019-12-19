import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private Client client;
    private World world;
    private JFrame frame;
    JPanel root;

    public GUI(Client client, World world) {
        this.client = client;
        this.world = world;
    }

    public void drawFrame() {
        frame = new JFrame();

        root = new JPanel(new CardLayout());

        JPanel startPanel = new JPanel(new GridLayout());
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessageToServer("start");
            }
        });
        startPanel.add(startButton);

        WorldPanel panel = new WorldPanel(world);
        panel.setBackground(Color.BLACK);

        root.add(startPanel, "START");
        root.add(panel, "WORLD");

        CardLayout cardLayout = (CardLayout) root.getLayout();
        cardLayout.show(root, "START");

        frame.addKeyListener(new InputManager(client));
        frame.setTitle("Livi");
        frame.setSize(new Dimension(600, 650));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.add(root);
        frame.setVisible(true);
    }

    public void startGame() {
        CardLayout cardLayout = (CardLayout) root.getLayout();
        cardLayout.show(root, "WORLD");
    }

    public void repaint() {
        frame.repaint();
    }

}

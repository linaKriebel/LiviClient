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
    private JPanel endPanel;
    private JLabel countDownLabel;
    private int counterValue = 10;
    private Timer timer;

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
                startCountDown();
            }
        });
        startButton.setBackground(Color.WHITE);
        startButton.setAlignmentX(0.5f);
        startButton.setAlignmentY(0.5f);
        startPanel.add(startButton);
        startPanel.setBackground(Color.BLACK);

        worldPanel = new WorldPanel(world);
        worldPanel.setBackground(Color.BLACK);
        worldPanel.setFocusable(true);
        worldPanel.setRequestFocusEnabled(true);
        worldPanel.addKeyListener(new InputManager(client));

        countDownLabel = new JLabel();
        countDownLabel.setText(String.valueOf(counterValue));
        countDownLabel.setForeground(Color.WHITE);
        countDownLabel.setVerticalTextPosition(SwingConstants.CENTER);
        worldPanel.add(countDownLabel);

        endPanel = new JPanel(new GridLayout());
        endPanel.setBackground(Color.BLACK);

        root.add(startPanel, "START");
        root.add(worldPanel, "WORLD");
        root.add(endPanel, "END");

        cardLayout.show(root, "START");

        frame.setTitle("Livi");
        frame.setSize(new Dimension(600, 650));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.add(root);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendMessageToServer(ClientCommand.EXIT);
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

    public void showFinalResult() {
        CardLayout cardLayout = (CardLayout) root.getLayout();
        cardLayout.show(root, "END");

        int idOfPlayerWithHighestScore = 0;
        int score = 0;
        String resultText = "nobody won";
        Color playerColor = Color.WHITE;

        for (GameItem player : world.players) {
            if (player.getScore() > score) {
                score = player.getScore();
                idOfPlayerWithHighestScore = player.getId();
                resultText = "Player " + idOfPlayerWithHighestScore + " won";
                playerColor = player.getColor();
            }
        // TODO add additional check for nobody won
        }
        JLabel resultLabel = new JLabel();
        resultLabel.setText(resultText);
        resultLabel.setVerticalTextPosition(SwingConstants.CENTER);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setForeground(playerColor);
        endPanel.add(resultLabel);
    }

    private void startCountDown() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counterValue--;
                countDownLabel.setText(String.valueOf(counterValue));
                if(counterValue == 0){
                    timer.stop();
                    client.sendMessageToServer(ClientCommand.COUNTDOWN);
                }
            }
        });
        timer.start();
    }
}

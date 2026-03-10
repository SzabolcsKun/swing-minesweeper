package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton btnPlay;
    private JButton btnScoreBoard;
    private JLabel lbName;
    private JTextField tfNameInput;
    private JLabel lbTitle;

    private GameWindow parent;

    // the panel that contains every main menu buttons and the action of each one of them
    public MenuPanel(GameWindow p) {
        // aligns items to the center, less work for me
        setLayout(new GridBagLayout());
        parent = p;

        lbTitle = new JLabel("MINESWEEPER");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 50));

        lbName = new JLabel("Enter your name");
        lbName.setPreferredSize(new Dimension(200, 50));
        lbName.setFont(new Font("Arial", Font.BOLD, 24));

        tfNameInput = new JTextField("Anonymus");
        tfNameInput.setPreferredSize(new Dimension(200, 50));
        tfNameInput.setFont(new Font("Arial", Font.ITALIC, 24));

        btnScoreBoard = new JButton("Scoreboard");
        btnScoreBoard.setPreferredSize(new Dimension(200, 50));
        btnScoreBoard.setFont(new Font("Arial", Font.BOLD, 24));
        btnScoreBoard.setBackground(Color.cyan);
        btnScoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getScoreboardPanel().refresh();
                parent.showView("SCOREBOARD");
            }
        });

        btnPlay = new JButton("Play");
        btnPlay.setPreferredSize(new Dimension(200, 50));
        btnPlay.setFont(new Font("Arial", Font.BOLD, 24));
        btnPlay.setBackground(Color.cyan);
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfNameInput.getText();
                parent.startGame(username);
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0);
        add(lbTitle, c);

        // margins around each element in the layout
        c.insets = new Insets(10, 0, 10, 0);
        c.gridy = 1;
        add(lbName, c);

        c.gridy = 2;
        add(tfNameInput, c);

        c.gridy = 3;
        add(btnScoreBoard, c);

        c.gridy = 4;
        add(btnPlay, c);

        setVisible(true);
    }
}

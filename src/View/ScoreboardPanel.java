package View;

import Model.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreboardPanel extends JPanel {
    private final DefaultListModel<ScoreManager.ScoreEntry> listModel;
    private final JList<ScoreManager.ScoreEntry> scoreList;
    private final GameWindow window;

    public ScoreboardPanel(GameWindow p) {
        window = p;
        // I chose the BorderLayout for an easier object placement
        setLayout(new BorderLayout());

        JLabel lbTitle = new JLabel("TOP 15 SCORES", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 50));
        add(lbTitle, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        scoreList = new JList<>(listModel);
        scoreList.setFont(new Font("Monospaced", Font.BOLD, 20));
        scoreList.setBackground(Color.lightGray);

        JScrollPane scrollPane = new JScrollPane(scoreList);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnBack = new JButton("Back to Menu");
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setBackground(Color.RED);
        btnBack.addActionListener(e -> window.showView("MENU"));
        add(btnBack, BorderLayout.SOUTH);

        setVisible(true);
    }

    // after every successful round, we refresh the scoreboard so that your score could appear if it's the case
    public void refresh() {
        listModel.clear();
        ScoreManager manager = new ScoreManager();

        List<ScoreManager.ScoreEntry> scores = manager.loadScores();

        if (!scores.isEmpty())
            for (ScoreManager.ScoreEntry entry : scores)
                listModel.addElement(entry);
    }
}

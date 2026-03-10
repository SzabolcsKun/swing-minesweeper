package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final ScoreboardPanel scoreboardPanel;

    public GameWindow() {
        setTitle("Minesweeper");
        setBounds(150, 50,700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // I used card layout, so that the navigation between the different "windows"(panels) of the app
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // this is main menu panel, with all of its features
        MenuPanel menu = new MenuPanel(this);
        mainContainer.add(menu, "MENU");

        // a dedicated panel for the scoreboard (only those scores are saved, which represent a successful completion of the game (these are saved automatically)
        scoreboardPanel = new ScoreboardPanel(this);
        mainContainer.add(scoreboardPanel, "SCOREBOARD");

        add(mainContainer);

        // the default panel, which appears when you start the app
        showView("MENU");
        this.setVisible(true);
    }

    // a function that helps to switch (to show) between the different panels
    public void showView(String viewName) {
        cardLayout.show(mainContainer, viewName);
    }

    // this function launches the game itself
    // it puts together the whole game panel, the header (an exit button, a timer and a counter for the remaining flags) + the game (a 16 x 16 grid)
    public void startGame(String username) {
        GamePanel gamePanel = new GamePanel(username);
        HeaderPanel header = gamePanel.getHeaderPanel();
        header.addExitGameActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showView("MENU");
            }
        });

        mainContainer.add(gamePanel, "GAME");
        showView("GAME");
    }

    // a getter function for the scoreboard panel
    public ScoreboardPanel getScoreboardPanel() {
        return scoreboardPanel;
    }
}
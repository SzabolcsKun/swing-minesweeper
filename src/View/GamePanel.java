package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    HeaderPanel headerPanel;
    BoardPanel boardPanel;

    // the concrete panel, with the header and the game panel
    public GamePanel(String username) {
        setLayout(new BorderLayout());

        headerPanel = new HeaderPanel(username);
        add(BorderLayout.NORTH, headerPanel);

        JPanel boardWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        boardWrapper.setBackground(Color.DARK_GRAY);

        boardPanel = new BoardPanel(headerPanel);

        boardWrapper.add(boardPanel);
        add(BorderLayout.CENTER, boardWrapper);

        setVisible(true);
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }
}

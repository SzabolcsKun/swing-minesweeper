package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {
    private final JButton btnBack;
    private final JLabel lbUsername;
    private final JLabel lbFlags;
    private final JLabel lbTimer;

    public HeaderPanel(String username) {
        setLayout(new GridLayout());

        btnBack = new JButton("◀"); // ◀ = \u25C0
        btnBack.setBackground(Color.red);

        lbUsername = new JLabel(username);
        lbUsername.setFont(new Font("Arial", Font.ITALIC, 20));

        lbTimer = new JLabel("0");
        lbTimer.setFont(new Font("Arial", Font.BOLD, 20));

        lbFlags = new JLabel("40");
        lbFlags.setFont(new Font("Arial", Font.BOLD, 20));

        add(btnBack);
        add(lbUsername);
        add(lbTimer);
        add(lbFlags);

        setVisible(true);
    }

    public void addExitGameActionListener(ActionListener e) {
        btnBack.addActionListener(e);
    }

    public String getlbUsername() {
        return lbUsername.getText();
    }
    public void setTimer(int seconds) {
        lbTimer.setText(String.valueOf(seconds));
    }
    public void setFlags(int flags) {
        lbFlags.setText(String.valueOf(flags));
    }
}

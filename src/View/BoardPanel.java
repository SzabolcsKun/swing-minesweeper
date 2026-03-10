package View;

import Controller.SoundEffect;
import Model.BoardLogic;
import Model.Cell;
import Model.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private final Cell[][] cells;
    private boolean firstClick = true;
    private final BoardLogic logic;
    private final HeaderPanel header;
    private int secondsElapsed = 0;
    private int flagsLeft = 40;
    private Timer gameTimer;

    // it creates the board
    public BoardPanel(HeaderPanel h) {
        setPreferredSize(new Dimension(640, 640));
        setLayout(new GridLayout(16, 16, 0, 0));

        header = h;
        // the whole technical part of the game, basically the brain of the game
        logic = new BoardLogic();

        // creates and "renders" each and every possible cell, adds the event listeners for both left and right click
        cells = new Cell[16][16];
        for (int i=0; i<16; ++i) {
            for (int j = 0; j < 16; ++j) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].updateCell();
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        Cell clickedCell = (Cell) e.getSource();

                        if (SwingUtilities.isLeftMouseButton(e)) {
                            // if it's the first left click we generate and place the positions of every bomb, update these cells, and we make sure that the surrounding 9 cells of the first click doesn't contain a bomb
                            if (firstClick) {
                                startTimer();
                                firstClick = false;
                                logic.generateBombs(clickedCell.get_x(), clickedCell.get_y());
                                logic.setNeighborCounts();

                                for (int i = 0; i < 16; ++i) {
                                    for (int j = 0; j < 16; ++j) {
                                        cells[i][j].setBomb(logic.isBombAt(i, j));
                                        cells[i][j].setNeighboringBombs(logic.getNeighborCountsAt(i, j));
                                    }
                                }
                            }

                            // depending on the states of the cell we do a certain action
                            if (!clickedCell.isRevealed() && !clickedCell.isFlagged()) {
                                clickedCell.setRevealed(true);
                                clickedCell.updateCell();

                                if (!clickedCell.isBomb()) {
                                    if (logic.getNeighborCountsAt(clickedCell.get_x(), clickedCell.get_y()) == 0)
                                        revealEmptyCellsAround(clickedCell.get_x(), clickedCell.get_y());

                                    checkWin();
                                } else {
                                    stopTimer();
                                    SoundEffect.play("explosion.wav");
                                    revealAllBombs();
                                    JOptionPane.showMessageDialog(BoardPanel.this, "BOOM! GAME OVER!");
                                }
                            }
                            // what happens when the player uses right click on a cell
                        } else if (SwingUtilities.isRightMouseButton(e)) {
                            if (!clickedCell.isRevealed()) {
                                if (!clickedCell.isFlagged() && flagsLeft > 0) {
                                    clickedCell.setFlagged(true);
                                    flagsLeft--;
                                } else if (clickedCell.isFlagged()) {
                                    clickedCell.setFlagged(false);
                                    flagsLeft++;
                                }
                                clickedCell.updateCell();
                                header.setFlags(flagsLeft);
                            }
                        }
                    }
                });

                add(cells[i][j]);
            }
        }

        setVisible(true);
    }

    // it starts the timer, which records how long the game lasts (second by second)
    private void startTimer() {
        gameTimer = new Timer(1000, e -> {
            ++secondsElapsed;
            header.setTimer(secondsElapsed);
        });
        gameTimer.start();
    }

    // stops the timer, when the game ends (despite being a victory or a loose)
    public void stopTimer() {
        if (gameTimer != null)
            gameTimer.stop();
    }

    // if you click on an empty cell, it reveals every empty cell around it (including those that contain numbered fields)
    public void revealEmptyCellsAround(int x, int y) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = x + dr;
                int nc = y + dc;

                if (nr >= 0 && nr < 16 && nc >= 0 && nc < 16) {
                    Cell neighbor = cells[nr][nc];

                    if (!neighbor.isRevealed() && !neighbor.isFlagged() && !logic.isBombAt(nr, nc)) {
                        neighbor.setRevealed(true);
                        neighbor.updateCell();

                        if (logic.getNeighborCountsAt(nr, nc) == 0)
                            revealEmptyCellsAround(nr, nc);
                    }
                }
            }
        }
    }

    // when the player accidentally click on a cell on which a bomb was located at, the game ends, and it reveals the unflagged bombs locations
    private void revealAllBombs() {
        for (int i = 0; i < 16; ++i)
            for (int j = 0; j < 16; ++j)
                if (logic.isBombAt(i, j)) {
                    cells[i][j].setRevealed(true);
                    cells[i][j].updateCell();
                }
    }

    // checks if the player revealed every cell that doesn't hide a bomb underneath
    private void checkWin() {
        int revealedCount = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (cells[i][j].isRevealed() && !cells[i][j].isBomb())
                    revealedCount++;
            }
        }

        // if one did, then the game concludes. A pop-up window appears congratulating. The timer stops and the score gets saved to a file
        if (revealedCount == 216) {
            stopTimer();

            String name = header.getlbUsername();
            new ScoreManager().saveScore(name, secondsElapsed);

            JOptionPane.showMessageDialog(this, "CONGRADULATIONS! YOU WON!\n" + "Time: " + secondsElapsed + "s" );
        }
    }
}

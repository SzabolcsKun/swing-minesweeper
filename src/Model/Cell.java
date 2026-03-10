package Model;

import javax.swing.*;
import java.awt.*;
import Utility.CellFactory;

// thw entire grid is made out of objects of this kind
public class Cell extends JButton {
    private final int x;
    private final int y;
    private Boolean isBomb;
    private Boolean isRevealed;
    private Boolean isFlagged;
    private int neighboringBombs;

    // at first, it creates a standard, simple, harmless and hidden field
    public Cell(int x, int y) {
        setPreferredSize(new Dimension(40, 40));
        setMargin(new Insets(0, 0, 0, 0));

        this.x = x;
        this.y = y;

        isBomb = isRevealed = isFlagged = false;
        neighboringBombs = 0;
    }

    // based on the state of a cell it changes it's icon
    public void updateCell() {
        if (isFlagged)
            setIcon(CellFactory.getIcon("FLAGGED"));
        else if (isRevealed) {
            if (isBomb)
                setIcon(CellFactory.getIcon("BOMB"));
            else {
                String key = (neighboringBombs == 0) ? "EMPTY" : String.valueOf(neighboringBombs);
                setIcon(CellFactory.getIcon(key));
            }
        } else
            setIcon(CellFactory.getIcon("HIDDEN"));
    }

    // getter and setter functions
    public int get_x() {
        return x;
    }
    public int get_y() {
        return y;
    }
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
    public void setNeighboringBombs(int count) {
        neighboringBombs = count;
    }
    public void setRevealed(boolean revealed) {
            isRevealed = revealed;
    }
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
    public boolean isRevealed() {
        return isRevealed;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public boolean isBomb() {
        return isBomb;
    }
}

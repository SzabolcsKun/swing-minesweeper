package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardLogic {
    private final boolean[][] bombs;
    private final int[][] neighborCounts;

    public BoardLogic() {
        bombs = new boolean[16][16];
        neighborCounts = new int[16][16];
    }

    // generates the bombs' location (excluding the first 9 cells of the first click)
    public void generateBombs(int x, int y) {
        List<Integer> numbers = new ArrayList<>();

        for (int i=0; i<256; ++i) {
            int row = i / 16;
            int col = i % 16;

            if (Math.abs(row - x) > 1 || Math.abs(col - y) > 1)
                numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int i=0; i<40; ++i) {
            int index = numbers.get(i);
            int row = index / 16;
            int col = index % 16;

            bombs[row][col] = true;
        }
    }

    // iterates through the entire grid and updates the non-explosive fields' neighboring bombs count
    public void setNeighborCounts() {
        for (int i=0; i<16; ++i) {
            for (int j=0; j<16; ++j) {
                if (bombs[i][j])
                    continue;

                int count = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = i + dr;
                        int nc = j + dc;

                        if (nr >= 0 && nr < 16 && nc >= 0 && nc < 16)
                            if (bombs[nr][nc])
                                count++;
                    }
                }
                neighborCounts[i][j] = count;
            }
        }
    }

    // getter functions
    public int getNeighborCountsAt(int x, int y) {
        return neighborCounts[x][y];
    }
    public boolean isBombAt(int x, int y) {
        return bombs[x][y];
    }
}

package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// responsible for the file write/ read related tasks
public class ScoreManager {
    private final String filename = "scores.txt";

    // every score is represented by an object of this class
    public static class ScoreEntry implements Comparable<ScoreEntry> {
        private final String username;
        private final int time;

        public ScoreEntry(String username, int time) {
            this.username = username;
            this.time = time;
        }

        @Override
        public int compareTo(ScoreEntry o) {
            return Integer.compare(this.time, o.time);
        }

        @Override
        public String toString() {
            return username + " - " + time + "s";
        }
    }

    // saves the score to a given file
    public void saveScore(String name, int time) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String output = name + ";" + time + "\n";
            bw.write(output);
            bw.close();
        } catch (IOException e) {
            System.out.println("There was an error saving the score!" + e);
        }
    }

    // loads the scores from the given file (it uses java streams)
    public List<ScoreEntry> loadScores() {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> {
                        String[] parts = line.split(";");
                        String username = parts[0];
                        int time = Integer.parseInt(parts[1]);
                        return new ScoreEntry(username, time);
                    })
                    .sorted(ScoreEntry::compareTo)
                    .limit(15)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("There was an error loading the score!" + e);
            return new ArrayList<>();
        }
    }
}

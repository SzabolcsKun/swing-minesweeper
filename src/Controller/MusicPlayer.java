package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// creates a thread that plays a background music on loop from a given path
public class MusicPlayer implements Runnable {
    private final String filePath;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);

                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else
                System.err.println("No such file found: " + filePath);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("There was an error starting the music!" + e);
        }
    }
}

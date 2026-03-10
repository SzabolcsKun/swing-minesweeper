package Controller;

import View.GameWindow;

public class Main {
    public static void main(String[] args) {
        // creates a thread just for the background music
        Thread musicThread = new Thread(new MusicPlayer("res/bg_music.wav"));
        musicThread.setDaemon(true);
        musicThread.start();

        // the main windows of the application
        new GameWindow();
    }
}
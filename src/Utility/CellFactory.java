package Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CellFactory {
    private static final Map<String, ImageIcon> icons = new HashMap<>();
    private static final int icon_size = 40;

    // this runs when the class is mentioned for the very first time in the code
    static {
        loadIcons();
    }

    // it loads every possible icon that the game could use
    private static void loadIcons() {
        String[] iconNames = {"flagged", "explosion", "bomb", "1", "2", "3", "4", "5", "6", "7", "8", "hidden", "empty"};

        // it uses a stream to locate and load every icon
        Arrays.stream(iconNames).forEach(name -> {
            try {
                String path = "res/" + name + ".png";
                BufferedImage img = ImageIO.read(new File(path));

                Image scaledImg = img.getScaledInstance(icon_size, icon_size, Image.SCALE_SMOOTH);

                icons.put(name.toUpperCase(), new ImageIcon(scaledImg));

            } catch (IOException e) {
                System.err.println("There was an error loading the icon: " + name + " -> " + e.getMessage());
                icons.put(name.toUpperCase(), new ImageIcon());
            }
        });
    }

    // a getter function for every possible icon
    public static ImageIcon getIcon(String key) {
        return icons.getOrDefault(key.toUpperCase(), new ImageIcon());
    }
}

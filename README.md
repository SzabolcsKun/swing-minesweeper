# swing-minesweeper
A classic Minesweeper clone built with Java Swing, featuring custom pixel art icons and a nostalgic desktop UI

Short description:
A desktop clone of the original Minesweeper game, built with Java Swing. This project focuses on a clean, classic experience and custom-made pixel art.

Features:
1. Custom Pixel Art: Hand-made icons for flags, mines, numbers and fields
2. Mechanics: Left-click to reveal, right-click to place flag
3. Time tracker: From the moment you first click the left mouse button a timer starts that will track your time until you successfully manage to finish the game. Every time you restart, the timer resets.
4. Leaderboard: Every time you beat the game, your username + your time will be saved and can be seen in the Scoreboard menu.
5. Multithreaded Performance: A thead for the game itself + one thread for a chill background song (C418 - Wet Hands - Minecraft Volume Alpha | https://www.youtube.com/watch?v=mukiMaOSLEs)

Built with:
Java 21 (LTS): Core game logic and grid management.
Java Swing: GUI elements and event handling.
Custom Assets: Original pixel art icons.

Preview:
![App Screenshot](preview/)

How to use it:
1. Clone the repo: git clone https://github.com/SzabolcsKun/swing-minesweeper
2. Compile the code: javac Main.java
3. java Main

package Header;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Header {
    // ...

public void printRPG() {
    int consoleWidth = 80;  // replace with your console's width

    String[] rpgAsciiLines = {
            " ____  ____    ____ ",
            "|  _ \\|  _ \\  / ___|",
            "| |_) | |_) || |  _ ",
            "|  _ <|  __/ | |_| |",
            "|_| \\_\\_|     \\____|"
    };

    for (String rpgAsciiLine : rpgAsciiLines) {
        // Calculate the number of '*' symbols to print on each side
        int numStars = (consoleWidth - rpgAsciiLine.length()) / 2;

        // Add 7 more '*' symbols to the left side and 6 to the right side
        int numStarsLeft = numStars + 7;
        int numStarsRight = numStars + 6;

        // Ensure numStars is not negative
        if (numStarsLeft < 0) {
            numStarsLeft = 0;
        }
        if (numStarsRight < 0) {
            numStarsRight = 0;
        }

        // Create the '*' symbols string
        String starsLeft = new String(new char[numStarsLeft]).replace("\0", "*");
        String starsRight = new String(new char[numStarsRight]).replace("\0", "*");

        // Print the '*' symbols, the RPG ASCII art line, and then the '*' symbols again
        System.out.println(starsLeft + rpgAsciiLine + starsRight);
    }
}

public void printBackground() {
    int consoleWidth = 80;  // replace with your console's width

    // Print 3 lines of '*' symbols before the RPG ASCII art
    for (int i = 0; i < 2; i++) {
        String stars = new String(new char[consoleWidth + 13]).replace("\0", "*");
        System.out.println(stars);
    }

    // Print the RPG ASCII art
    printRPG();

    // Print 3 lines of '*' symbols after the RPG ASCII art
    for (int i = 0; i < 2; i++) {
        String stars = new String(new char[consoleWidth + 13]).replace("\0", "*");
        System.out.println(stars);
    }
}

    // ...
}

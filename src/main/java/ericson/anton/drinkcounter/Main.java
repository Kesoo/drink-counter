package ericson.anton.drinkcounter;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final String WINDOW_TITLE = "RUSTET Drink Counter";
    private static final Integer WINDOW_WIDTH = 300;
    private static final Integer WINDOW_HEIGHT = 200;
    private static final String OPEN_FILE_BUTTON_TEXT = "Open file";
    private static final String HEADER_TEXT = "Open the DRINKS.TXT file from the SD Card.";

    public static void main(String[] args) {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JLabel headerLabel = new JLabel(HEADER_TEXT);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        JButton openFileButton = new JButton(OPEN_FILE_BUTTON_TEXT);
        panel.add(openFileButton);

        frame.getContentPane().add(headerLabel, BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

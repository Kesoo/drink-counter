package ericson.anton.drinkcounter;

import ericson.anton.drinkcounter.utils.TxtFileFilter;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private static final String WINDOW_TITLE = "RUSTET Drink Counter";
    private static final Integer WINDOW_WIDTH = 300;
    private static final Integer WINDOW_HEIGHT = 150;
    private static final String OPEN_FILE_BUTTON_TEXT = "Open file";
    private static final String HEADER_TEXT = "Open the DRINKS.TXT file from the SD Card.";

    private static final Color BACKGROUND_COLOR = new Color(24, 25, 26,255);
    private static final Color PRIMARY_TEXT_COLOR = new Color(244, 240, 240,255);
    private static final Color SECONDARY_TEXT_COLOR = new Color(176,179,184,255);
    private static final Color BUTTON_MAIN_COLOR = new Color(222, 49, 99,255);

    private final DrinkCounter drinkCounter;
    private final JTextArea statusTextArea;

    public Gui() {
        this.drinkCounter = new DrinkCounter();

        this.statusTextArea = new JTextArea();
        statusTextArea.setBackground(BACKGROUND_COLOR);
        statusTextArea.setForeground(SECONDARY_TEXT_COLOR);
        statusTextArea.setEditable(false);
        statusTextArea.setLineWrap(true);
    }

    public void setupWindow(){
        JFrame mainFrame = new JFrame(WINDOW_TITLE);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JLabel headerLabel = new JLabel(HEADER_TEXT);
        headerLabel.setForeground(PRIMARY_TEXT_COLOR);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.add(createOpenFileButton());
        mainFrame.getContentPane().add(headerLabel, BorderLayout.BEFORE_FIRST_LINE);
        mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        mainFrame.getContentPane().add(statusTextArea, BorderLayout.AFTER_LAST_LINE);
        mainFrame.getContentPane().setBackground(BACKGROUND_COLOR);
        mainFrame.setVisible(true);
    }

    private JButton createOpenFileButton() {
        JButton openFileButton = new JButton(Gui.OPEN_FILE_BUTTON_TEXT);
        openFileButton.setBackground(BUTTON_MAIN_COLOR);
        openFileButton.setBorderPainted(false);
        openFileButton.setFocusPainted(false);

        JFileChooser fileChooser = new JFileChooser();
        TxtFileFilter fileFilter = new TxtFileFilter();
        fileChooser.setFileFilter(fileFilter);

        openFileButton.addActionListener(actionEvent -> {
            int returnInt = fileChooser.showOpenDialog(null);

            if (returnInt == JFileChooser.APPROVE_OPTION){
                String outputFileResponse = drinkCounter.createDrinkListFromFile(fileChooser.getSelectedFile(), false);
                statusTextArea.setText(outputFileResponse);
            }
        });

        return openFileButton;
    }
}

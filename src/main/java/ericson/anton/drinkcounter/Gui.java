package ericson.anton.drinkcounter;

import ericson.anton.drinkcounter.utils.TxtFileFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Gui {
    private static final String WINDOW_TITLE = "RUSTET Drink Counter";
    private static final Integer WINDOW_WIDTH = 300;
    private static final Integer WINDOW_HEIGHT = 200;
    private static final String OPEN_FILE_BUTTON_TEXT = "Open file";
    private static final String HEADER_TEXT = "Open the DRINKS.TXT file from the SD Card.";

    public void setupWindow(){
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JLabel headerLabel = new JLabel(HEADER_TEXT);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.add(createOpenFileButton());

        frame.getContentPane().add(headerLabel, BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createOpenFileButton() {
        JButton openFileButton = new JButton(Gui.OPEN_FILE_BUTTON_TEXT);

        JFileChooser fileChooser = new JFileChooser();
        TxtFileFilter fileFilter = new TxtFileFilter();
        fileChooser.setFileFilter(fileFilter);

        DrinkCounter drinkCounter = new DrinkCounter();

        openFileButton.addActionListener(actionEvent -> {
            int returnInt = fileChooser.showOpenDialog(null);

            if (returnInt == JFileChooser.APPROVE_OPTION){
                drinkCounter.createDrinkListFromFile(fileChooser.getSelectedFile(), false);
            }
        });

        return openFileButton;
    }
}

package src.main.java.tracker;

import mdlaf.*;
import mdlaf.themes.*;

import javax.swing.*;

public class Tracker {

    private JPanel rootPanel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Tracker");
        frame.setContentPane(new Tracker().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

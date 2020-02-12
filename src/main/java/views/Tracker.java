package src.main.java.views;

import javax.swing.*;

import com.formdev.flatlaf.*;

public class Tracker {

    private JPanel rootPanel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Tracker");
        frame.setContentPane(new Tracker().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package src.main.java.views;

import com.formdev.flatlaf.*;

import javax.swing.*;

public class Tracker extends JFrame {

    private JPanel rootPanel;
    private JPanel systemsPanel;
    private JPanel componentsPanel;

    public Tracker() {
        add(rootPanel);
        setTitle("LARI");
        setSize(1080,720);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Tracker tracker = new Tracker();
        tracker.setVisible(true);
    }
}

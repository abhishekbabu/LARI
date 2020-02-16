package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.time.LocalDate;

public class AddComponentWindow extends JFrame {

    private Equipage equipage;
    private JPanel rootPanel;
    private JLabel systemLabel;
    private JLabel damagedLabel;
    private JLabel historyLabel;
    private JLabel locationLabel;
    private JComboBox systemComboBox;
    private JLabel startDateLabel;
    private JLabel serialNumberLabel;
    private JLabel descriptionLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JTextArea descriptionTextArea;
    private JTextField serialNumberTextField;
    private JTextField locationTextField;
    private JTextArea historyTextArea;
    private JCheckBox damagedCheckBox;
    private JLabel activeLabel;
    private JCheckBox activeCheckBox;
    private JDateChooser startDateChooser;
    private JLabel flightTimeLabel;
    private JFormattedTextField flightTimeFormattedTextField;
    private JButton cancelButton;
    private JButton addButton;

    public AddComponentWindow(Equipage equipage) {
        this.equipage = equipage;
        initializeFrame();
        initializeInputComponents();
    }

    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New Component");
        setSize(1000,  450);
        setResizable(false);
    }

    private void initializeInputComponents() {
        // TODO: set combo box to display systems from Equipage fleet
        // wingtypeComboBox.setModel(new DefaultComboBoxModel(Wingtype.values()));

        // set prompt text in name text field to say "System name"
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (nameTextField.getText().equals("System name")) {
                    nameTextField.setText("");
                    nameTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setForeground(new Color(187, 187, 187));
                    nameTextField.setText("System name");
                }
            }
        });

        // set prompt text in description text area to say "System description" and set the border of the text area
        // to look like the border of a text field
        descriptionTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (descriptionTextArea.getText().equals("System description")) {
                    descriptionTextArea.setText("");
                    descriptionTextArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (descriptionTextArea.getText().isEmpty()) {
                    descriptionTextArea.setForeground(new Color(187, 187, 187));
                    descriptionTextArea.setText("System description");
                }
            }
        });
        descriptionTextArea.setBorder(new JTextField().getBorder());
    }

    /**
     * DO NOT REMOVE: Auto-generated method to add JDateChooser to frame
     */
    private void createUIComponents() {
        // initialize date chooser with today's date and set its look and feel
        startDateChooser = new JDateChooser();
        startDateChooser.setDate(Date.valueOf(LocalDate.now()));
        startDateChooser.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        startDateChooser.setSize(100, -1);
    }
}

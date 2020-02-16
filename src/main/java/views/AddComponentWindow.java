package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    private JLabel hoursLabel;

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
        // set combo box to display systems from Equipage fleet
        Set<String> fleetNames = new HashSet<>();
        Iterator<AFSLSystem> iter = equipage.fleet.iterator();
        while (iter.hasNext()) {
            fleetNames.add(iter.next().getName());
        }
        systemComboBox.setModel(new DefaultComboBoxModel(fleetNames.toArray()));
        systemComboBox.setSelectedIndex(-1);

        // set prompt text in name text field to say "Component name"
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (nameTextField.getText().equals("Component name")) {
                    nameTextField.setText("");
                    nameTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (nameTextField.getText().isEmpty()) {
                    nameTextField.setForeground(new Color(187, 187, 187));
                    nameTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    nameTextField.setText("Component name");
                }
            }
        });

        // set prompt text in description text area to say "Component description" and set the border of the text area
        // to look like the border of a text field
        descriptionTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (descriptionTextArea.getText().equals("Component description")) {
                    descriptionTextArea.setText("");
                    descriptionTextArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (descriptionTextArea.getText().isEmpty()) {
                    descriptionTextArea.setForeground(new Color(187, 187, 187));
                    descriptionTextArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    descriptionTextArea.setText("Component description");
                }
            }
        });
        descriptionTextArea.setBorder(new JTextField().getBorder());

        // set prompt text in history text area to say "Component history" and set the border of the text area
        // to look like the border of a text field
        historyTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (historyTextArea.getText().equals("Component history")) {
                    historyTextArea.setText("");
                    historyTextArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (historyTextArea.getText().isEmpty()) {
                    historyTextArea.setForeground(new Color(187, 187, 187));
                    historyTextArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    historyTextArea.setText("Component history");
                }
            }
        });
        historyTextArea.setBorder(new JTextField().getBorder());

        // set prompt text in serial number text field to say "Component serial number"
        serialNumberTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (serialNumberTextField.getText().equals("Component serial number")) {
                    serialNumberTextField.setText("");
                    serialNumberTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (serialNumberTextField.getText().isEmpty()) {
                    serialNumberTextField.setForeground(new Color(187, 187, 187));
                    serialNumberTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    serialNumberTextField.setText("Component serial number");
                }
            }
        });

        // set prompt text in location text field to say "Component location"
        locationTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (locationTextField.getText().equals("Component location")) {
                    locationTextField.setText("");
                    locationTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (locationTextField.getText().isEmpty()) {
                    locationTextField.setForeground(new Color(187, 187, 187));
                    locationTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    locationTextField.setText("Component location");
                }
            }
        });

        // set prompt text in flight time text field to say "0.00"
        flightTimeFormattedTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (flightTimeFormattedTextField.getText().equals("0.00")) {
                    flightTimeFormattedTextField.setText("");
                    flightTimeFormattedTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (flightTimeFormattedTextField.getText().isEmpty()) {
                    flightTimeFormattedTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    flightTimeFormattedTextField.setText("0.00");
                }
            }
        });
    }

    //region Getters
    public JTextField getSerialNumberTextField() {
        return serialNumberTextField;
    }

    public JTextField getLocationTextField() {
        return locationTextField;
    }
    //endregion

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

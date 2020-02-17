package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

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
        initializeButtons();
    }

    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New Component");
        setSize(1000,  450);
        setResizable(false);
    }


    private void initializeInputComponents() {
        // set combo box to display systems from Equipage fleet
        Set<String> fleetNames = new TreeSet<>();
        fleetNames.add("--Unconnected--");
        Iterator<AFSLSystem> iter = equipage.fleet.iterator();
        while (iter.hasNext()) {
            fleetNames.add(iter.next().getName());
        }
        systemComboBox.setModel(new DefaultComboBoxModel(fleetNames.toArray()));
        systemComboBox.setSelectedItem("--Unconnected--");

        // flight time
        NumberFormat flightTimeFormat = NumberFormat.getNumberInstance();
        flightTimeFormattedTextField = new JFormattedTextField(flightTimeFormat);
        flightTimeFormattedTextField.setValue(0.0);
        flightTimeFormattedTextField.setColumns(3);

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

    private void initializeButtons() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameTextField.getText().isEmpty() || nameTextField.getText().equals("System name")) {

                } else {
                    String compName = nameTextField.getText();
                    String compDescription = descriptionTextArea.getText();
                    String compSerialNumber = serialNumberTextField.getText();
                    LocalDate compStartDate = startDateChooser.getDate().toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDate();
                    double compFlightTime = Double.valueOf(flightTimeFormattedTextField.getText());
                    String compLocation = locationTextField.getText();
                    String compHistory = historyTextArea.getText();
                    boolean compDamaged = damagedCheckBox.isSelected();
                    boolean compActive = activeCheckBox.isSelected();
                    String compSystem = systemComboBox.getSelectedItem().toString();
                    equipage.insertComponent(new Component(UUID.randomUUID().toString(), compName, compDescription,
                            compSerialNumber, compStartDate, compFlightTime, compLocation,
                            compHistory, compDamaged, compActive, compSystem));
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

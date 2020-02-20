package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Component;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * <b>AddComponentWindow</b> is the frame that allows the user to add new components to be stored
 * in the app.
 */
public class AddComponentWindow extends JFrame {

    //region Fields

    /**
     * The main tracker window that opened this add component window
     */
    private Tracker tracker;

    /**
     * The equipage whose data is coupled with this tracker
     */
    private Equipage equipage;

    /**
     * The root JPanel that holds all the components
     */
    private JPanel rootPanel;

    /**
     * The system label
     */
    private JLabel systemLabel;

    /**
     * The damaged label
     */
    private JLabel damagedLabel;

    /**
     * The history label
     */
    private JLabel historyLabel;

    /**
     * The location label
     */
    private JLabel locationLabel;

    /**
     * Combo box for system
     */
    private JComboBox systemComboBox;

    /**
     * The start date label
     */
    private JLabel startDateLabel;

    /**
     * The serial number label
     */
    private JLabel serialNumberLabel;

    /**
     * The description label
     */
    private JLabel descriptionLabel;

    /**
     * The name label
     */
    private JLabel nameLabel;

    /**
     * Text field for name
     */
    private JTextField nameTextField;

    /**
     * Text area for description
     */
    private JTextArea descriptionTextArea;

    /**
     * Text field for serial number
     */
    private JTextField serialNumberTextField;

    /**
     * Text field for location
     */
    private JTextField locationTextField;

    /**
     * Text field for history
     */
    private JTextArea historyTextArea;

    /**
     * Check box for whether the component is damaged
     */
    private JCheckBox damagedCheckBox;

    /**
     * The active label
     */
    private JLabel activeLabel;

    /**
     * Check box for whether the component is active
     */
    private JCheckBox activeCheckBox;

    /**
     * Date chooser for start date
     */
    private JDateChooser startDateChooser;

    /**
     * The flight time label
     */
    private JLabel flightTimeLabel;

    /**
     * Formatted text field for flight time
     */
    private JFormattedTextField flightTimeFormattedTextField;

    /**
     * The cancel button
     */
    private JButton cancelButton;

    /**
     * The add component button
     */
    private JButton addButton;

    /**
     * The hours label
     */
    private JLabel hoursLabel;

    //endregion

    //region Constructors

    /**
     * Constructs an add component window
     *
     * @param equipage the equipage whose data is used with this tracker
     * @spec.effects constructs a new add component window with default settings
     */
    public AddComponentWindow(Equipage equipage, Tracker tracker) {
        this.tracker = tracker;
        this.equipage = equipage;
        initializeFrame();
        initializeInputComponents();
        initializeButtons();
    }

    //endregion

    //region Private Methods

    /**
     * Initializes the add component frame
     *
     * @spec.effects sets the content of the frame to be the root panel and gives the frame
     * a title and an unchangeable size
     */
    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New Component");
        setSize(1000,  450);
        setResizable(false);
    }


    /**
     * Initializes the components that take user input (text fields/areas, combo boxes)
     *
     * @spec.effects sets default values and prompt text for appropriate input components
     */
    private void initializeInputComponents() {
        initializeSerialNumberTextField();
        initializeLocationTextField();
        initializeSystemComboBox();
        initializeFlightTimeFormattedTextField();
        initializeNameTextField();
        initializeDescriptionTextArea();
        initializeHistoryTextArea();
    }

    /**
     * Initializes the serial number text field
     *
     * @spec.effects sets the prompt text in the field to say "Component serial number" and be greyed out;
     * also clears the field and makes the field's text change color to black when the user clicks it
     */
    private void initializeSerialNumberTextField() {
        serialNumberTextField.setForeground(new Color(187, 187, 187));
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
    }

    /**
     * Initializes the location text field
     *
     * @spec.effects sets the prompt text in the field to say "Component location" and be greyed out;
     * also clears the field and makes the field's text change color to black when the user clicks it
     */
    private void initializeLocationTextField() {
        locationTextField.setForeground(new Color(187, 187, 187));
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
    }

    /**
     * Initializes the system combo box
     *
     * @spec.effects sets the combo box to display all available systems and an option for a component
     * to not be connected to any system
     */
    private void initializeSystemComboBox() {
        Set<String> fleetNames = new TreeSet<>();
        fleetNames.add("--Unconnected--");
        Iterator<AFSLSystem> iter = equipage.fleet.iterator();
        while (iter.hasNext()) {
            fleetNames.add(iter.next().getName());
        }
        systemComboBox.setModel(new DefaultComboBoxModel(fleetNames.toArray()));
        systemComboBox.setSelectedItem("--Unconnected--");
    }

    /**
     * Initializes the flight time formatted text field
     *
     * @spec.effects sets the prompt text in the field to say "0" and be greyed out; also clears the
     * field and makes the field's text change color to black when the user clicks it; also ensures that
     * only numbers are accepted using the DecimalFormat
     */
    private void initializeFlightTimeFormattedTextField() {
        DecimalFormat df = new DecimalFormat();
        NumberFormatter dnff = new NumberFormatter(df);
        flightTimeFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(dnff));
        flightTimeFormattedTextField.setValue(0);
        flightTimeFormattedTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (flightTimeFormattedTextField.getValue().equals(0)) {
                    flightTimeFormattedTextField.setText("");
                    flightTimeFormattedTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (flightTimeFormattedTextField.getText().isEmpty()) {
                    flightTimeFormattedTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    flightTimeFormattedTextField.setValue(0);
                }
            }
        });
    }

    /**
     * Initializes the name text field
     *
     * @spec.effects sets the prompt text in the field to say "Component name" and be greyed out;
     * also clears the field and makes the field's text change color to black when the user clicks it
     */
    private void initializeNameTextField() {
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
    }

    /**
     * Initializes the description text area
     *
     * @spec.effects sets the prompt text in the area to say "Component description" and be greyed out;
     * also clears the area and makes the area's text change color to black when the user clicks it
     */
    private void initializeDescriptionTextArea() {
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
    }

    /**
     * Initializes the history text area
     *
     * @spec.effects sets the prompt text in the area to say "Component history" and be greyed out;
     * also clears the area and makes the area's text change color to black when the user clicks it
     */
    private void initializeHistoryTextArea() {
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
    }

    /**
     * Initializes the buttons on the frame
     *
     * @spec.effects adds action listeners to each button on the window
     */
    private void initializeButtons() {
        initializeAddButton();
        initializeCancelButton();
    }

    /**
     * Initializes the add button
     *
     * @spec.effects sets action listener for add button to show dialog if input is invalid, or add component,
     * update the tracker table and close if input is valid
     */
    private void initializeAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Class flightTimeClass = flightTimeFormattedTextField.getValue().getClass();
                if (nameTextField.getText().isEmpty() || nameTextField.getText().equals("Component name")) {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                } else if (flightTimeFormattedTextField.getValue() == null) { // not a number
                    JOptionPane.showMessageDialog(null, "Flight time format is invalid.\n" +
                            "Please enter a number.");
                } else if (flightTimeClass.equals(Integer.class) &&
                        (Integer) flightTimeFormattedTextField.getValue() < 0) { // integer less than 0
                    JOptionPane.showMessageDialog(null, "Flight time cannot be less than 0.");
                } else if (flightTimeClass.equals(Double.class) &&
                        (Double) flightTimeFormattedTextField.getValue() < 0) { // double less than 0
                    JOptionPane.showMessageDialog(null, "Flight time cannot be less than 0.");
                } else if (flightTimeClass.equals(Long.class) &&
                        (Long) flightTimeFormattedTextField.getValue() < 0) { // long less than 0
                    JOptionPane.showMessageDialog(null, "Flight time cannot be less than 0.");
                } else { // all inputs are valid
                    String compName = nameTextField.getText();

                    String compDescription = descriptionTextArea.getText();
                    if (compDescription.equals("Component description")) {
                        compDescription = "";
                    }

                    String compSerialNumber = serialNumberTextField.getText();
                    if (compSerialNumber.equals("Component serial number")) {
                        compSerialNumber = "";
                    }

                    LocalDate compStartDate = startDateChooser.getDate().toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDate();

                    double compFlightTime;
                    if (flightTimeClass.equals(Integer.class)) {
                        compFlightTime = (Integer) flightTimeFormattedTextField.getValue();
                    } else if (flightTimeClass.equals(Double.class)) {
                        compFlightTime = (Double) flightTimeFormattedTextField.getValue();
                    } else {
                        compFlightTime = (Long) flightTimeFormattedTextField.getValue();
                    }

                    String compLocation = locationTextField.getText();
                    if (compLocation.equals("Component location")) {
                        compLocation = "";
                    }

                    String compHistory = historyTextArea.getText();
                    if (compHistory.equals("Component history")) {
                        compHistory.equals("");
                    }

                    boolean compDamaged = damagedCheckBox.isSelected();
                    boolean compActive = activeCheckBox.isSelected();

                    String compSystem = systemComboBox.getSelectedItem().toString();
                    if (compSystem.equals("--Unconnected--")) {
                        compSystem = "";
                    }

                    equipage.insertComponent(new Component(compName, compDescription,
                            compSerialNumber, compStartDate, compFlightTime, compLocation,
                            compHistory, compDamaged, compActive, compSystem));
                    tracker.initializeComponentsTable();

                    setVisible(false);
                    dispose();
                }
            }
        });
    }

    /**
     * Initializes the cancel button
     *
     * @spec.effects sets action listener for cancel button to show confirmation dialog and close if
     * response is yes
     */
    private void initializeCancelButton() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close the window?\nYour data will be lost.");
                if (response == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                }
            }
        });
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

    //endregion

}

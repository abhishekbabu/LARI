package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Wingtype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * <b>AddSystemWindow</b> is the frame that allows the user to add new systems to be stored
 * in the app.
 */
public class AddSystemWindow extends JFrame {

    //region Fields

    /**
     * The main tracker window that opened this add system window
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
     * Text area for description
     */
    private JTextArea descriptionTextArea;

    /**
     * Text field for name
     */
    private JTextField nameTextField;

    /**
     * Combo box for wing type
     */
    private JComboBox wingtypeComboBox;

    /**
     * Date chooser for start date
     */
    private JDateChooser startDateChooser;

    /**
     * The name label
     */
    private JLabel nameLabel;

    /**
     * The description label
     */
    private JLabel descriptionLabel;

    /**
     * The wing type label
     */
    private JLabel wingtypeLabel;

    /**
     * The start date label
     */
    private JLabel startDateLabel;

    /**
     * The add system button
     */
    private JButton addButton;

    /**
     * The cancel button
     */
    private JButton cancelButton;

    //endregion

    //region Constructors

    /**
     * Constructs an add system window
     *
     * @param equipage the equipage whose data is used with this tracker
     * @spec.effects constructs a new add system window with default settings
     */
    public AddSystemWindow(Equipage equipage, Tracker tracker) {
        this.tracker = tracker;
        this.equipage = equipage;
        initializeFrame();
        initializeInputComponents();
        initializeButtons();
    }

    //endregion

    //region Private Methods

    /**
     * Initializes the add system frame
     *
     * @spec.effects sets the content of the frame to be the root panel and gives the frame
     * a title and an unchangeable size
     */
    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New System");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tracker.toggleCanOpenNewWindow();
                setVisible(false);
                dispose();
            }
        });
    }

    /**
     * Initializes the components that take user input (text fields/areas, combo boxes)
     *
     * @spec.effects sets default values and prompt text for appropriate input components
     */
    private void initializeInputComponents() {
        initializeWingtypeComboBox();
        initializeNameTextField();
        initializeDescriptionTextArea();
    }

    /**
     * Initializes the wingtype combo box
     *
     * @spec.effects sets the combo box to display types from Wingtype enum
     */
    private void initializeWingtypeComboBox() {
        wingtypeComboBox.setModel(new DefaultComboBoxModel(Wingtype.values()));
    }

    /**
     * Initializes the name text field
     *
     * @spec.effects sets the prompt text in the field to say "System name" and be greyed out;
     * also clears the field and makes the field's text change color to black when the user clicks it
     */
    private void initializeNameTextField() {
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
                    nameTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    nameTextField.setText("System name");
                }
            }
        });
    }

    /**
     * Initializes the description text area
     *
     * @spec.effects sets the prompt text in the text area to say "System description" and be greyed
     * out; also clears the area and makes the area's text change color to black when the user clicks it
     */
    private void initializeDescriptionTextArea() {
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
     * @spec.effects sets action listener for add button to show dialog if input is invalid, or add system,
     * update the tracker table and close if input is valid
     */
    private void initializeAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameTextField.getText().isEmpty() || nameTextField.getText().equals("System name")) {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                } else {
                    String sysName = nameTextField.getText();
                    String sysDescription = descriptionTextArea.getText();
                    Wingtype sysWingtype = Wingtype.valueOf(wingtypeComboBox.getSelectedItem().toString());
                    LocalDate sysStartDate = startDateChooser.getDate().toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDate();
                    AFSLSystem newSys = new AFSLSystem(sysName, sysDescription, sysWingtype, sysStartDate);
                    if (equipage.fleet.contains(newSys)) {
                        JOptionPane.showMessageDialog(null, "System with the given name " +
                                "already exists.");
                    } else {
                        equipage.insertSystem(newSys);
                        tracker.initializeSystemsTable();

                        tracker.toggleCanOpenNewWindow();
                        setVisible(false);
                        dispose();
                    }
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
                    tracker.toggleCanOpenNewWindow();
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

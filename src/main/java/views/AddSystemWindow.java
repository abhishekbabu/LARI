package src.main.java.views;

import com.toedter.calendar.JDateChooser;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Wingtype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class AddSystemWindow extends JFrame {

    private Equipage equipage;
    private JPanel rootPanel;
    private JTextArea descriptionTextArea;
    private JTextField nameTextField;
    private JComboBox wingtypeComboBox;
    private JDateChooser startDateChooser;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel wingtypeLabel;
    private JLabel startDateLabel;
    private JButton addButton;
    private JButton cancelButton;

    public AddSystemWindow(Equipage equipage) {
        this.equipage = equipage;
        initializeFrame();
        initializeInputComponents();
        initializeButtons();
    }

    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New System");
        setSize(600, 400);
        setResizable(false);
    }

    private void initializeInputComponents() {
        // set combo box to display wingtypes from Wingtype enum
        wingtypeComboBox.setModel(new DefaultComboBoxModel(Wingtype.values()));

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
                    nameTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
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

    private void initializeButtons() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameTextField.getText().isEmpty() || nameTextField.getText().equals("System name")) {

                } else {
                    String sysName = nameTextField.getText();
                    String sysDescription = descriptionTextArea.getText();
                    Wingtype sysWingtype = Wingtype.valueOf(wingtypeComboBox.getSelectedItem().toString());
                    LocalDate sysStartDate = startDateChooser.getDate().toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDate();
                    equipage.insertSystem(new AFSLSystem(sysName, sysDescription, sysWingtype, sysStartDate));
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
}

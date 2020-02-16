package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.Wingtype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddSystemWindow extends JFrame {

    private Equipage equipage;
    private JPanel rootPanel;
    private JPanel labelsPanel;
    private JPanel inputPanel;
    private JPanel buttonsPanel;
    private JLabel nameLabel;
    private JLabel descriptionPanel;
    private JLabel wingtypeLabel;
    private JLabel startDateLabel;
    private JTextField nameTextField;
    private JTextArea descriptionTextArea;
    private JComboBox wingtypeComboBox;

    public AddSystemWindow(Equipage equipage) {
        this.equipage = equipage;
        initializeFrame();
        initializeInputComponents();
    }

    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New System");
        setSize(500, 300);
    }

    private void initializeInputComponents() {
        wingtypeComboBox.setModel(new DefaultComboBoxModel(Wingtype.values()));
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
    }

}

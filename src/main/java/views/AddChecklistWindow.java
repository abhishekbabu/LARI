package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * <b>AddChecklistWindow</b> is the frame that allows the user
 * to add new checklists to the app.
 */
public class AddChecklistWindow extends JFrame {

    // region Fields
    /**
     * The main tracker window that opened this add checklist window
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
     * Combo box for system
     */
    private JComboBox systemComboBox;

    /**
     * The add checklist button
     */
    private JButton addButton;

    // TODO: Required fields in the populated checklist

    // endregion

    // region Constructors

    /**
     * Constructs an add checklist window
     *
     * @param equipage the equipage whose data is used with this tracker
     * @spec.effects constructs a new add checklist window with default settings
     */
    public AddChecklistWindow(Equipage equipage, Tracker tracker) {
        this.tracker = tracker;
        this.equipage = equipage;
        initializeFrame();
        initializeInputChecklist();
        initializeButtons();
    }

    // endregion

    // region Private Methods

    /**
     * Initializes the add checklist frame
     *
     * @spec.effects sets the content of the frame to be the root panel and gives
     * a title and an unchangeable size
     */
    private void initializeFrame() {
        add(rootPanel);
        setTitle("Add New Checklist");
        setSize(1000, 450);
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
     * Initializes the checklists that take user input (text fields/areas, combo boxes)
     *
     * @spec.effects sets default values and prompt text for appropriate input
     */
    private void initializeInputChecklist() {
        initializeSystemComboBox();
        //TODO: initialize the required fields taken from user input
    }

    /**
     * Initialize the system combo box
     *
     * @spec.effects sets the combo box to display all available systems and
     * an option for a component to not be connected to any system
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

    // TODO: private methods for the initialization of the fields

    /**
     * Initializes the buttons on the frame
     *
     * @spec.effects add action listeners to each button on the window
     */
    private void initializeButtons() {
        initializeAddButton();
    }

    /**
     * Initializes the add button
     *
     * @spec.effects sets action listener for add button to show dialog if input is invalid,
     * or add checklist, update the tracker table and close if input is valid
     */
    private void initializeAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO: required fields inputted by users for the addButton
            }
        });
    }
}

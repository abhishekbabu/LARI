package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Checklist {
    private Checklist checklist;
    private JPanel rootPanel;
    private JLabel checklistLabel;
    private JComboBox systemsComboBox;
    private JButton generateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JScrollPane checklistScrollPane;
    private JTable checklistTable;
    private JLabel systemLabel;
    private Equipage equipage;

    public Checklist(Equipage equipage) {
        this.checklist = this;
        this.equipage = equipage;
        initializeInputComponents();
        initializeChecklistTable();
        initializeButtons();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void initializeInputComponents() {
        initializeSystemsComboBox();
    }

    private void initializeChecklistTable() {
        // add column headers and make table non editable by user
        DefaultTableModel checklistTableModel = new DefaultTableModel(
                new String[] {"Date", "Location", "System Name", "Completed"}, 0
        ) {
            Class[] types = new Class[] {String.class, String.class, String.class, Boolean.class};

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // TODO: add all checklists in fleet to systems table

        // set checklist table to use this model
        checklistTable.setModel(checklistTableModel);
    }

    private void initializeButtons() {

    }

    private void initializeSystemsComboBox() {
        Set<String> fleetNames = new TreeSet<>();
        Iterator<AFSLSystem> iter = equipage.fleet.iterator();
        while (iter.hasNext()) {
            fleetNames.add(iter.next().getName());
        }
        systemsComboBox.setModel(new DefaultComboBoxModel(fleetNames.toArray()));
    }
}

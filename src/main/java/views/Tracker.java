package src.main.java.views;

import com.formdev.flatlaf.FlatIntelliJLaf;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Wingtype;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * <b>Tracker</b> is the root window of LARI i.e. the startup window. It houses two tables containing the Systems and
 * Components stored.
 */
public class Tracker extends JFrame {

    //region Fields

    /**
     * Reference to this instance of the tracker
     */
    private Tracker tracker;

    /**
     * The root JPanel that holds all items in the tracker
     */
    private JPanel rootPanel;

    /**
     * The JPanel that houses the systems data
     */
    private JPanel systemsPanel;

    /**
     * The JPanel that houses the components data
     */
    private JPanel componentsPanel;

    /**
     * The systems label
     */
    private JLabel systemsLabel;

    /**
     * The add system button
     */
    private JButton addSystemButton;

    /**
     * The edit system button
     */
    private JButton editSystemButton;

    /**
     * The delete system button
     */
    private JButton deleteSystemButton;

    /**
     * The components label
     */
    private JLabel componentsLabel;

    /**
     * The add component button
     */
    private JButton addComponentButton;

    /**
     * The edit component button
     */
    private JButton editComponentButton;

    /**
     * The delete component button
     */
    private JButton deleteComponentButton;

    /**
     * The scroll pane for the systems table
     */
    private JScrollPane systemsTableScrollPane;

    /**
     * The scroll pane for the components table
     */
    private JScrollPane componentsTableScrollPane;

    /**
     * The systems table
     */
    private JTable systemsTable;

    /**
     * The components table
     */
    private JTable componentsTable;

    /**
     * The equipage whose data is coupled with this tracker
     */
    private Equipage equipage;

    //endregion

    //region Constructors

    /**
     * Constructs a tracker window
     *
     * @param equipage the equipage whose data is used to construct this tracker
     * @spec.effects constructs a tracker window with the data in the given equipage
     */
    public Tracker(Equipage equipage) {
        this.tracker = this;
        this.equipage = equipage;
        initializeFrame();
        initializeSystemsTable();
        initializeComponentsTable();
        initializeButtons();
    }

    //endregion

    //region Private Methods

    /**
     * Initializes the tracker frame
     *
     * @spec.effects sets the content of the frame to be the content of the root panel and gives the frame a title
     * and initial size
     */
    private void initializeFrame() {
        // split the root panel into two horizontal sections for Systems and Components
        // and add it to the frame
        rootPanel.setLayout(new GridLayout(2, 1));
        add(rootPanel);

        // set the title and startup size of app window
        setTitle("LARI - Laboratory Reconciliation and Information System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 500));
    }

    /**
     * Initializes the buttons
     *
     * @spec.effects sets action listeners for every button on the tracker
     */
    private void initializeButtons() {
        initializeAddSystemButton();
        initializeEditSystemButton();
        initializeAddComponentButton();
    }

    /**
     * Initializes add system button
     *
     * @spec.effects sets action listener for add system button to open new add system window
     */
    private void initializeAddSystemButton() {
        addSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSystemWindow addNewSys = new AddSystemWindow(equipage, tracker);
                addNewSys.setVisible(true);
            }
        });
    }

    /**
     * Initializes edit system button
     *
     * @spec.effects sets action listener for edit system button to open new edit system window
     * with settings from the system that is being edited
     */
    private void initializeEditSystemButton() {
        editSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (systemsTable.getSelectionModel().isSelectionEmpty()) {
                    System.out.println("No system selected");
                } else {
                    int row = systemsTable.getSelectedRow();
                    String sysName = systemsTable.getValueAt(row, 0).toString();
                    EditSystemWindow editSys = new EditSystemWindow(equipage, sysName);
                    editSys.setVisible(true);
                }
            }
        });
    }

    /**
     * Initializes add component button
     *
     * @spec.effects sets action listener for add component button to open new add component window
     */
    private void initializeAddComponentButton() {
        addComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddComponentWindow addNewComp = new AddComponentWindow(equipage, tracker);
                addNewComp.setVisible(true);
            }
        });
    }

    //endregion

    //region Public Methods

    /**
     * Initializes data in the systems table
     *
     * @spec.effects makes the systems table non-editable and populates it with the system data from the equipage;
     * also sets look and feel of the table
     */
    public void initializeSystemsTable() {

        // add column headers and make table non editable by user
        DefaultTableModel systemsTableModel = new DefaultTableModel(
                new String[] {"Name", "Description", "Wing Type", "Start Date"}, 0
        ) {
            Class[] types = new Class[] {String.class, String.class, Wingtype.class, String.class};

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // add all systems in fleet to systems table
        for (AFSLSystem sys : equipage.fleet) {
            systemsTableModel.addRow(new Object[] {sys.getName(), sys.getDescription(), sys.getWingtype(),
                    sys.getStartDate()});
        }

        // set systems table to use this model
        systemsTable.setModel(systemsTableModel);

        // alternate non-selected row colors between white and purple
        systemsTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i,
                                                           int i1) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
                if (!b) {
                    c.setBackground(i % 2 == 0 ? Color.WHITE : new Color(216, 191, 216));
                }
                return c;
            }
        });

        // set color of selected row to blue i.e. Chrome's default selection color
        systemsTable.setSelectionBackground(new Color(50, 151, 253));

        // set table header font to Helvetica Neue
        systemsTable.getTableHeader().setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
    }

    /**
     * Initializes data in the components table
     *
     * @spec.effects makes the components table non-editable and populates it with the component data from the
     * equipage; also sets look and feel of the table
     */
    public void initializeComponentsTable() {

        // add column headers and make table non editable by user
        DefaultTableModel componentsTableModel = new DefaultTableModel(
                new String[] {"ID", "Name", "Description", "Serial Number", "Start Date", "Flight Time (hrs)",
                        "Location", "History", "Damaged", "Active", "System"}, 0
        ) {
            Class[] types = new Class[] {String.class, String.class, String.class, String.class, String.class,
                    double.class, String.class, String.class, String.class, boolean.class, String.class};

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // add all components connected to systems to components table
        for (AFSLSystem sys : equipage.fleet) {
            for (src.main.java.datatypes.Component c : sys.getComponents()) {
                String damaged = c.isDamaged() ? "✓" : "";
                String active = c.isActive() ? "✓" : "";
                componentsTableModel.addRow(new Object[] {c.getId(), c.getName(), c.getDescription(), c.getSerialNum(),
                        c.getStartDate(), c.getFlightTime(), c.getLocation(), c.getHistory(), damaged, active,
                        c.getSystem()});
            }
        }

        // add all components not connected to a system to components table
        for (src.main.java.datatypes.Component c : equipage.unconnected) {
            String damaged = c.isDamaged() ? "✓" : "";
            String active = c.isActive() ? "✓" : "";
            componentsTableModel.addRow(new Object[] {c.getId(), c.getName(), c.getDescription(), c.getSerialNum(),
                    c.getStartDate(), c.getFlightTime(), c.getLocation(), c.getHistory(), damaged, active,
                    c.getSystem()});
        }

        // set components table to use this model
        componentsTable.setModel(componentsTableModel);

        // hide ID column from components table so that the program can still access its data, but the user cannot
        // see it
        componentsTable.removeColumn(componentsTable.getColumnModel().getColumn(0));

        // alternate non-selected row colors between white and gold
        componentsTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i,
                                                           int i1) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
                if (!b) {
                    c.setBackground(i % 2 == 0 ? Color.WHITE : new Color(238, 232, 170));
                }
                return c;
            }
        });

        // set color of selected row to blue i.e. Chrome's default selection color
        componentsTable.setSelectionBackground(new Color(50, 151, 253));

        // set table header font to Helvetica Neue
        componentsTable.getTableHeader().setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
    }

    //endregion

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Equipage equipage = new Equipage(false); // flag indicates whether or not to erase database
        Tracker tracker = new Tracker(equipage);
        tracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tracker.setVisible(true);
    }

}

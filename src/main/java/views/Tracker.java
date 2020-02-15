package src.main.java.views;

import com.formdev.flatlaf.*;
import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Wingtype;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Tracker extends JFrame {

    private JPanel rootPanel;
    private JPanel systemsPanel;
    private JPanel componentsPanel;
    private JButton deleteSystemButton;
    private JButton addSystemButton;
    private JButton editSystemButton;
    private JLabel systemsLabel;
    private JTable systemsTable;
    private JScrollPane systemsTableScrollPane;

    private Equipage equipage;

    public Tracker(Equipage equipage) {
        this.equipage = equipage;
        add(rootPanel);
        setTitle("LARI");
        setSize(800,500);
        initializeSystemsTable();
    }

    private void initializeSystemsTable() {
        DefaultTableModel systemsTableModel = new DefaultTableModel(
                new String[] {"Name", "Description", "Wing Type", "Start Date"}, 0
        ) {
            Class[] types = new Class[] {
                    String.class,
                    String.class,
                    Wingtype.class,
                    String.class,
            };
        };
        for (AFSLSystem sys : equipage.fleet) {
            systemsTableModel.addRow(new Object[] {sys.getName(), sys.getDescription(), sys.getWingtype(),
                    sys.getStartDate()});
        }
        systemsTable.setModel(systemsTableModel);
        systemsTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(jTable, o, b, b1, i, i1);
                if (i % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
                return c;
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Tracker tracker = new Tracker(new Equipage());
        tracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tracker.pack();
        tracker.setVisible(true);
    }
}

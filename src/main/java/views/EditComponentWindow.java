package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Component;

import java.awt.*;
import java.util.Set;


public class EditComponentWindow extends AddComponentWindow {

    private Component editComponent;

    /**
     * Constructs an add component window
     *
     * @param equipage the equipage whose data is used with this tracker
     * @param tracker
     * @spec.effects constructs a new add component window with default settings
     */
    public EditComponentWindow(Equipage equipage, Tracker tracker, String sysName, String compID) {
        super(equipage, tracker);
        findComponent(equipage, sysName, compID);
        setTitle("Edit Component");
        initializeInputComponents();
    }

    /**
     * Initializes the components that take user input (text fields/areas, combo boxes)
     *
     * @spec.effects sets default values and prompt text for appropriate input components
     */
    private void initializeInputComponents() {
        serialNumberTextField.setText(editComponent.getSerialNum());
        serialNumberTextField.setForeground(Color.BLACK);
        locationTextField.setText(editComponent.getLocation());
        locationTextField.setForeground(Color.BLACK);
        systemComboBox.setSelectedItem(editComponent.getSystem());
        flightTimeFormattedTextField.setText(editComponent.getFlightTime() + "");
        flightTimeFormattedTextField.setForeground(Color.BLACK);
        nameTextField.setText(editComponent.getName());
        nameTextField.setForeground(Color.BLACK);
        descriptionTextArea.setText(editComponent.getDescription());
        descriptionTextArea.setForeground(Color.BLACK);
        historyTextArea.setText(editComponent.getHistory());
        historyTextArea.setForeground(Color.BLACK);
        activeCheckBox.setSelected(editComponent.isActive());
        damagedCheckBox.setSelected(editComponent.isDamaged());
        
    }

    /**
     * Finds the component of the given ID
     *
     * @param equipage
     * @param sysName
     * @param compID
     */
    private void findComponent(Equipage equipage, String sysName, String compID) {
        Set<Component> sysComponents = null;

        if (sysName.isEmpty()) {
            sysComponents = equipage.unconnected;
        } else {
            AFSLSystem editSystem = null;
            for (AFSLSystem system : equipage.fleet) {
                if (system.getName().equals(sysName)) {
                    editSystem = system;
                }
            }
            if (editSystem == null) {
                throw new IllegalArgumentException("The given system name is not contained in Equipage");
            }
            sysComponents = editSystem.getComponents();
        }

        for (Component comp : sysComponents) {
            if (comp.getId().equals(compID)) {
                editComponent = comp;
            }
        }
        if (editComponent == null) {
            throw new IllegalArgumentException("The given component ID is not contained in the system " + sysName);
        }
    }
}

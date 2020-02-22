package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;
import src.main.java.datatypes.Component;


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

    }

    /**
     * Finds the component of the given ID
     *
     * @param equipage
     * @param sysName
     * @param compID
     */
    private void findComponent(Equipage equipage, String sysName, String compID) {
        AFSLSystem editSystem = null;
        for (AFSLSystem system : equipage.fleet) {
            if (system.getName().equals(sysName)) {
                editSystem = system;
            }
        }
        if (editSystem == null) {
            throw new IllegalArgumentException("The given system name is not contained in Equipage");
        }

        for (Component comp : editSystem.getComponents()) {
            if (comp.getId().equals(compID)) {
                editComponent = comp;
            }
        }
        if (editComponent == null) {
            throw new IllegalArgumentException("The given component ID is not contained in the system " + sysName);
        }
    }
}

package src.main.java.views;

import src.main.java.Equipage;
import src.main.java.datatypes.AFSLSystem;

import javax.swing.*;
import java.util.Iterator;

public class EditSystemWindow extends JFrame {

    private AFSLSystem editSystem;

    public EditSystemWindow(Equipage equipage, String sysName) {
        Iterator<AFSLSystem> iter = equipage.fleet.iterator();
        while (iter.hasNext()) {
            AFSLSystem system = iter.next();
            if (system.getName().equals(sysName)) {
                editSystem = system;
            }
        }
        if (editSystem == null) {
            throw new IllegalArgumentException("The given system name is not contained in Equipage");
        }
    }

}

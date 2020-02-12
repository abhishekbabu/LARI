package src.main.java.datatypes;

import java.util.*;

public class AFSLSystem {

    //region Fields

    private String name;
    private String description;
    private List<Component> components;
    private Wingtype wingtype;

    //endregion

    public AFSLSystem(String name, String description, Wingtype wingtype) {
        this.name = name;
        this.description = description;
        this.wingtype = wingtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wingtype getWingtype() {
        return wingtype;
    }

    public void setWingtype(Wingtype wingtype) {
        this.wingtype = wingtype;
    }
}

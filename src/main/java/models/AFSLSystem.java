package src.main.java.models;

import java.util.*;

public class AFSLSystem {

    //region Fields

    private String name;
    private String description;
    private Wingtype wingtype;
    private Date startDate;
    private List<Component> components;

    //endregion

    //region Constructors

    public AFSLSystem(String name, String description, Wingtype wingtype, Date startDate) {
        this.name = name;
        this.description = description;
        this.wingtype = wingtype;
        this.startDate = startDate;
        this.components = new ArrayList<Component>();
    }

    //endregion

    //region Observers

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Wingtype getWingtype() {
        return wingtype;
    }

    public Date getStartDate() {
        return startDate;
    }

    //endregion

    //region Mutators

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWingtype(Wingtype wingtype) {
        this.wingtype = wingtype;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //endregion

    //region Public Methods

    public void addComponent(Component c) {
        components.add(c.deepCopy());
    }

    public void removeComponent(Component c) {
        components.remove(c);
    }

    //endregion
}

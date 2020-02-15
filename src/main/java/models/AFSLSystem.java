package src.main.java.models;

import java.time.LocalDate;
import java.util.*;

/**
 * <b>AFSLSystem</b> represents a system at AFSL that the application keeps
 * track of
 */
public class AFSLSystem {

    //region Fields

    /**
     * The unique name of this system
     */
    private String name;

    /**
     * The description of this system
     */
    private String description;

    /**
     * The wingtype of this system
     */
    private Wingtype wingtype;

    /**
     * The start date of this system's use
     */
    private LocalDate startDate;

    /**
     * The set of components attached to this system
     */
    private Set<Component> components;

    //endregion

    //region Constructors

    /**
     * Constructs an AFSLSystem with the given parameters
     *
     * @spec.effects Constructs an AFSLSystem with the given parameters
     * @param name the name of the system to be created
     * @param description the description of the system to be created
     * @param wingtype the wingtype of the system to be created
     * @param startDate the starting date of the system to be created
     */
    public AFSLSystem(String name, String description, Wingtype wingtype, LocalDate startDate) {
        this.name = name;
        this.description = description;
        this.wingtype = wingtype;
        this.startDate = startDate;
        this.components = new HashSet<Component>();
    }

    /**
     * FOR TESTING/DEBUGGING PURPOSES ONLY: Constructs a default AFSLSystem
     *
     * @spec.effects Constructs a default AFSLSystem with a randomly generated name,
     * no description, no wingtype, and today's date as the start date
     */
    public AFSLSystem() {
        this(UUID.randomUUID().toString(), "", Wingtype.None, LocalDate.now());
    }

    //endregion

    //region Observers

    /**
     * Returns the name of this system
     *
     * @return the name of this system
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of this system
     *
     * @return the description of this system
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the wingtype of this system
     *
     * @return the wingtype of this system
     */
    public Wingtype getWingtype() {
        return wingtype;
    }

    /**
     * Returns the starting date of this system
     *
     * @return the starting date of this system
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the set of components attached to this system
     *
     * @return the set of components attached to this system
     */
    public Set<Component> getComponents() {
        return Collections.unmodifiableSet(components);
    }

    //endregion

    //region Mutators

    /**
     * Sets the name of the system
     *
     * @param name the name of the system
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the system
     *
     * @param description the description of the system
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the wingtype of the system
     *
     * @param wingtype the wingtype of the system
     */
    public void setWingtype(Wingtype wingtype) {
        this.wingtype = wingtype;
    }

    /**
     * Sets the starting date of the system
     *
     * @param startDate the starting date of the system
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the set of components attached to the system
     *
     * @param components the set of components attached to the system
     */
    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    //endregion

    //region Public Methods

    /**
     * Adds the given component to the set of components attached to the
     * system
     *
     * @param c the component to be attached to this system
     */
    public void addComponent(Component c) {
        components.add(c);
    }

    /**
     * Removes the component with the given id from the set of components
     * attached to the system
     *
     * @param cid the id of the component to be removed from this system
     */
    public void removeComponent(String cid) {
        Iterator<Component> iter = components.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(cid)) {
                iter.remove();
            }
        }
    }

    /**
     * Returns true if and only if the component with the given id is
     * attached to the system, false otherwise
     *
     * @param cid the id of the system to be checked
     * @return true if and only if the component with the given id is
     * contained in the set of components attached to the system, false
     * otherwise
     */
    public boolean containsComponent(String cid) {
        for (Component c : components) {
            if (c.getId().equals(cid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if and only if the specified system is equal to this
     * system, false otherwise
     *
     * @param o the object to compare to this system
     * @return true if and only if the specified system has the same name
     * as this system, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AFSLSystem that = (AFSLSystem) o;
        return name.equals(that.name);
    }

    /**
     * Returns the hash code value of this system
     *
     * @return the hash code value for this system
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //endregion
}
